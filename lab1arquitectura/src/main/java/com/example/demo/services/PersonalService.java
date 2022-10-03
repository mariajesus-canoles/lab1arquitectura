package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonalService {
    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PagoRepository pagoRepository;

    @Autowired
    RelojRepository relojRepository;

    @Autowired
    JustificativoRepository justificativoRepository;

    @Autowired
    HoraExtraService horaExtraService;

    @Autowired
    RelojService relojService;

    @Autowired
    JustificativoService justificativoService;

    public ArrayList<PersonalEntity> obtenerPersonales(){
        return (ArrayList<PersonalEntity>) personalRepository.findAll();
    }

    public PersonalEntity guardarPersonal(PersonalEntity personal){

        return personalRepository.save(personal);
    }

    public Optional<PersonalEntity> obtenerPersonalPorId(Long id){
        return personalRepository.findById(id);
    }

    public boolean eliminarPersonal(Long id) {
        try{
            personalRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public ArrayList<PlanillaEntity> obtenerPlanillaPersonales(){
        ArrayList<PersonalEntity> listaPersonal = this.obtenerPersonales();
        ArrayList<PlanillaEntity> listaPlanilla = new ArrayList<PlanillaEntity>();

        for (PersonalEntity personal: listaPersonal) {
            PlanillaEntity planilla = new PlanillaEntity();
            //Rut
            planilla.setRut(personal.getRut());
            //Nombre empleado
            planilla.setNombre_empleado(personal.getNombres() + " " + personal.getApellidos());
            //Categoria
            String categoria = categoriaRepository.buscarCategoria(personal.getId());
            planilla.setCategoria(categoria);
            //Años de servicio empresa
            Integer anosServicioEmpresa = this.calcularAnosServicios(personal.getFecha_ingreso());
            planilla.setAnos_servicio_empresa(anosServicioEmpresa);
            //Sueldo fijo mensual
            Integer sueldoFijo = pagoRepository.buscarSueldoFijoMensual(personal.getId());
            planilla.setSueldo_fijo_mensual(sueldoFijo);
            //Bonificación horas extras
            Integer bonificacionHorasExtras = horaExtraService.calcularBonificacionHorasExtras(personal.getId());
            planilla.setBonificacion_horas_extras(bonificacionHorasExtras);
            //Bonificación tiempo de servicio
            Integer bonificacionTiempoServicio = this.calcularBonificacionTiempoServicio(anosServicioEmpresa, sueldoFijo);
            planilla.setBonificacion_tiempo_servicio(bonificacionTiempoServicio);
            //Bonificación por puntualidad
            Integer bonificacionPuntualidad = relojService.calcularBonificacionPuntualidad(personal.getId(), sueldoFijo);
            planilla.setBonificacion_puntualidad(bonificacionPuntualidad);

            //información
            ArrayList<String> listaFechasDelMes = relojRepository.buscarFechasDelMes();
            ArrayList<RelojEntity> relojesPersonal = relojRepository.buscarRelojesDePersonal(personal.getId());
            ArrayList<JustificativoEntity> justificativosPersonal = justificativoRepository.buscarJustificativosDePersonal(personal.getId());

            //Descuentos
            //Descuentos por tardanzas en el ingreso
            System.out.println("Calcular descuento tardanza");
            DescuentoContext context = new DescuentoContext();
            context.setDescuentoMethod(new DescuentoTardanza());
            Integer descuentoTardanza = context.aplicarDescuento(personal.getId(), sueldoFijo, listaFechasDelMes, relojesPersonal, justificativosPersonal);
            System.out.println(descuentoTardanza);
            planilla.setDescuento_tardanza(descuentoTardanza);
            //Descuentos por retiros antes de la hora de salida
            System.out.println("Calcular descuento retiro");
            DescuentoContext context2 = new DescuentoContext();
            context.setDescuentoMethod(new DescuentoRetiro());
            Integer descuentoRetiro = context.aplicarDescuento(personal.getId(), sueldoFijo, listaFechasDelMes, relojesPersonal, justificativosPersonal);
            System.out.println(descuentoRetiro);
            planilla.setDescuento_retiro(descuentoRetiro);

            //Sueldo bruto
            Integer sueldoBruto = sueldoFijo + bonificacionHorasExtras + bonificacionTiempoServicio + bonificacionPuntualidad - (descuentoTardanza + descuentoRetiro);
            planilla.setSueldo_bruto(sueldoBruto);
            ArrayList<Integer> cotizaciones= this.calcularCotizacion(this.personalRepository.buscarFechaIngreso(personal.getId()), sueldoBruto);
            //Cotización Previsional
            planilla.setCotizacion_previsional(cotizaciones.get(0));
            //Cotización salud
            planilla.setCotizacion_plan_salud(cotizaciones.get(1));
            //Monto sueldo final
            Integer sueldoFinal = sueldoBruto - (cotizaciones.get(0) + cotizaciones.get(1));
            planilla.setMonto_suelto_final(sueldoFinal);
            listaPlanilla.add(planilla);
        }
        return listaPlanilla;

    }

    public Integer calcularAnosServicios(LocalDate fechaIngreso){
        LocalDate fechaActual = LocalDate.now();
        Integer diferenciaAnos = fechaActual.getYear() - fechaIngreso.getYear();
        return diferenciaAnos;
    }

    public Integer calcularBonificacionTiempoServicio(Integer anosServicioEmpresa, Integer sueldoFijo){
        Double bonificacionTiempoServicio;
        if (anosServicioEmpresa<5){
            return 0;
        }
        else if (anosServicioEmpresa>=5 && anosServicioEmpresa<10) {
            bonificacionTiempoServicio = sueldoFijo*0.05;
        }
        else if (anosServicioEmpresa>=10 && anosServicioEmpresa<15) {
            bonificacionTiempoServicio = sueldoFijo*0.08;
        }
        else if(anosServicioEmpresa>=15 && anosServicioEmpresa<20) {
            bonificacionTiempoServicio = sueldoFijo*0.11;
        }
        else if(anosServicioEmpresa>=20){
            bonificacionTiempoServicio = sueldoFijo*0.14;
        }
        else {
            bonificacionTiempoServicio = sueldoFijo*0.17;
        }
        Integer bonificacionTiempoServicioInteger = bonificacionTiempoServicio.intValue();
        return bonificacionTiempoServicioInteger;
    }

    ArrayList<Integer> calcularCotizacion(LocalDate fecha_ingreso, Integer sueldoBruto){
        Integer ano_ingreso = fecha_ingreso.getYear();
        Double cotizacionPrevisional = 0.0;
        Double cotizacionPlanSalud = 0.0;
        ArrayList<Integer> listaCotizacion = new ArrayList<Integer>();
        System.out.println("Año ingreso");
        System.out.println(ano_ingreso);
        if (ano_ingreso < 1980){
            cotizacionPrevisional = sueldoBruto * 0.07;
            cotizacionPlanSalud = sueldoBruto * 0.07;
        } else if (ano_ingreso >= 1980 && ano_ingreso < 2000) {
            cotizacionPrevisional = sueldoBruto * 0.09;
            cotizacionPlanSalud = sueldoBruto * 0.08;
        }
        else {
            cotizacionPrevisional = sueldoBruto * 0.1;
            cotizacionPlanSalud = sueldoBruto * 0.08;
        }
        listaCotizacion.add(cotizacionPrevisional.intValue());
        listaCotizacion.add(cotizacionPlanSalud.intValue());
        return listaCotizacion;
    }

}
