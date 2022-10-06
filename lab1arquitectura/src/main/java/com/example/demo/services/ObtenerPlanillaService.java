package com.example.demo.services;

import com.example.demo.entities.JustificativoEntity;
import com.example.demo.entities.PersonalEntity;
import com.example.demo.entities.PlanillaEntity;
import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ObtenerPlanillaService {
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

    @Autowired
    CotizacionFacade cotizacionFacade;

    @Autowired
    AnosDeServicioService anosDeServicioService;

    @Autowired
    PagoService pagoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    PersonalService personalService;

    public ArrayList<PlanillaEntity> calcularPlanillaPersonales(){
        ArrayList<PersonalEntity> listaPersonal = personalService.obtenerPersonales();
        ArrayList<PlanillaEntity> listaPlanilla = new ArrayList<PlanillaEntity>();

        for (PersonalEntity personal: listaPersonal) {
            PlanillaEntity planilla = new PlanillaEntity();
            //Rut
            planilla.setRut(personal.getRut());
            //Nombre empleado
            planilla.setNombre_empleado(personal.getNombres() + " " + personal.getApellidos());
            //Categoria
            String categoria = categoriaService.categoriaRepository.buscarCategoria(personal.getId());
            planilla.setCategoria(categoria);
            //Años de servicio empresa
            Integer anosServicioEmpresa = anosDeServicioService.calcularAnosServicios(personal.getFecha_ingreso());
            //Integer anosServicioEmpresa = this.calcularAnosServicios(personal.getFecha_ingreso());
            planilla.setAnos_servicio_empresa(anosServicioEmpresa);
            //Sueldo fijo mensual
            //Integer sueldoFijo = pagoRepository.buscarSueldoFijoMensual(personal.getId());
            Integer sueldoFijo = pagoService.pagoRepository.buscarSueldoFijoMensual(personal.getId());
            planilla.setSueldo_fijo_mensual(sueldoFijo);
            //Bonificación horas extras
            Integer bonificacionHorasExtras = horaExtraService.calcularBonificacionHorasExtras(personal.getId());
            planilla.setBonificacion_horas_extras(bonificacionHorasExtras);
            //Bonificación tiempo de servicio
            Integer bonificacionTiempoServicio = anosDeServicioService.calcularBonificacionTiempoServicio(anosServicioEmpresa, sueldoFijo);
            //Integer bonificacionTiempoServicio = this.calcularBonificacionTiempoServicio(anosServicioEmpresa, sueldoFijo);
            planilla.setBonificacion_tiempo_servicio(bonificacionTiempoServicio);

            //Bonificación por puntualidad
            Integer bonificacionPuntualidad = relojService.calcularBonificacionPuntualidad(personal.getId(), sueldoFijo);
            planilla.setBonificacion_puntualidad(bonificacionPuntualidad);
            //información

            ArrayList<String> listaFechasDelMes = relojService.relojRepository.buscarFechasDelMes();
            ArrayList<RelojEntity> relojesPersonal = relojService.relojRepository.buscarRelojesDePersonal(personal.getId());
            ArrayList<JustificativoEntity> justificativosPersonal = justificativoService.justificativoRepository.buscarJustificativosDePersonal(personal.getId());
            //ArrayList<JustificativoEntity> justificativosPersonal = justificativoRepository.buscarJustificativosDePersonal(personal.getId());

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
            ArrayList<Integer> cotizaciones = cotizacionFacade.calcularCotizaciones(this.personalRepository.buscarFechaIngreso(personal.getId()), sueldoBruto);
            //ArrayList<Integer> cotizaciones= this.calcularCotizacion(this.personalRepository.buscarFechaIngreso(personal.getId()), sueldoBruto);
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
}
