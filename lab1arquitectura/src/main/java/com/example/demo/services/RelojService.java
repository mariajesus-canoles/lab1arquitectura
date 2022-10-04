package com.example.demo.services;

import com.example.demo.entities.PersonalEntity;
import com.example.demo.entities.RelojEntity;
import com.example.demo.repositories.PagoRepository;
import com.example.demo.repositories.PersonalRepository;
import com.example.demo.repositories.RelojRepository;
import com.sun.jdi.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RelojService {
    @Autowired
    RelojRepository relojRepository;

    @Autowired
    PersonalRepository personalRepository;


    public ArrayList<RelojEntity> obtenerRelojes(){

        return (ArrayList<RelojEntity>) relojRepository.findAll();
    }

    public RelojEntity guardarReloj(RelojEntity reloj){

        return relojRepository.save(reloj);
    }

    public Optional<RelojEntity> obtenerRelojPorId(Long id){

        return relojRepository.findById(id);
    }

    public boolean eliminarReloj(Long id) {
        try{
            relojRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public ArrayList<Integer> calcularPorcentajePuntualidad(Long idPersonal){
        Integer horaEntrada = 8;
        Integer horaSalida = 18;
        Integer numDiasEntradaPuntual = 0;
        Integer numDiasSalidaPuntual = 0;
        Integer numDiasDelMes = relojRepository.buscarNumDiasDelMes();
        ArrayList<RelojEntity> listaRelojes= relojRepository.buscarRelojesDePersonal(idPersonal);
        for (RelojEntity reloj: listaRelojes){
            System.out.println(reloj.getHora_entrada().getHour());
            System.out.println(reloj.getHora_salida().getHour());
            if (reloj.getHora_entrada().getHour()<horaEntrada ||
                    reloj.getHora_entrada().getHour()==horaEntrada && reloj.getHora_entrada().getMinute()==0){
                numDiasEntradaPuntual++;
            }
            if (reloj.getHora_salida().getHour()>=horaSalida){
                numDiasSalidaPuntual++;
            }
        }
        Double porcentajeDiasEntradaPuntual = numDiasEntradaPuntual.doubleValue() * 100 / numDiasDelMes.doubleValue();
        Double porcentajeDiasSalidaPuntual = numDiasSalidaPuntual.doubleValue() * 100 / numDiasDelMes.doubleValue();
        ArrayList<Integer> porcentajesPuntualidad = new ArrayList<Integer>();
        porcentajesPuntualidad.add(porcentajeDiasEntradaPuntual.intValue());
        porcentajesPuntualidad.add(porcentajeDiasSalidaPuntual.intValue());
        return porcentajesPuntualidad;
    }

    public Integer calcularBonificacionPuntualidad(Long idPersonal, Integer sueldoFijoMensual){
        Double bonificacionPuntualidad = 0.0;
        ArrayList<Integer> porcentajesPuntualidad = this.calcularPorcentajePuntualidad(idPersonal);
        System.out.println("Nueva prueba");
        System.out.println(idPersonal);
        System.out.println(porcentajesPuntualidad.get(0));
        System.out.println(porcentajesPuntualidad.get(1));
        System.out.println("Fin prueba");
        if (porcentajesPuntualidad.get(0)>80.0 && porcentajesPuntualidad.get(1)>80.0 &&
                porcentajesPuntualidad.get(0)<=90.0 && porcentajesPuntualidad.get(1)<=90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.05;
        }
        if (porcentajesPuntualidad.get(0)>90.0 && porcentajesPuntualidad.get(1)>90.0){
            bonificacionPuntualidad = sueldoFijoMensual * 0.08;
        }
        return bonificacionPuntualidad.intValue();
    }

    public ArrayList<RelojEntity> cargarMarcaHoraria(MultipartFile archivo) {
        ArrayList<ArrayList<String>> matrizArchivo = new ArrayList<ArrayList<String>>();
        if (!archivo.isEmpty()){
            try{
                String archivoString = new String(archivo.getBytes(), StandardCharsets.UTF_8);

                Factory factory = new Factory();
                Lectura lectura = factory.crearLectura(archivo.getContentType());
                matrizArchivo = lectura.LecturaArchivo(archivoString);

            } catch (IOException e){
                e.printStackTrace();

            }
        }
        ArrayList<RelojEntity> listaRelojes = new ArrayList<RelojEntity>();
        for (ArrayList<String> arrayArchivo: matrizArchivo){
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate fecha = LocalDate.parse(arrayArchivo.get(0), df);
            LocalTime hora = LocalTime.parse(arrayArchivo.get(1));
            String rut = arrayArchivo.get(2);
            ArrayList<RelojEntity> reloj = relojRepository.buscarRelojPorRutYFecha(rut, fecha);
            //Long idPersonal = relojRepository.buscarIdPersonalPorRut(rut);
            //ArrayList<RelojEntity> reloj = relojRepository.buscarRelojDePersonalPorId(Long.valueOf(idPersonal), fecha);

            if (reloj.isEmpty()){ //no existe reloj

                PersonalEntity personal = personalRepository.buscarPersonalPorRut("11.234.123-6");
                System.out.println("hola 3");
                Long idPersonal = personal.getId();
                System.out.println("id personal");
                System.out.println(idPersonal);
                // inicio buscar ultimo id
                Long ultimoId;
                Long aux = Long.valueOf(1);
                ArrayList<Long> ultimoIdAux = relojRepository.buscarUltimoId();
                if (ultimoIdAux.isEmpty()){
                    ultimoId = aux;
                }
                else{
                    ultimoId = Long.sum(ultimoIdAux.get(0), aux);
                }
                System.out.println(ultimoId);
                System.out.println("fin");
                // fin buscar ultimo id
                RelojEntity nuevoReloj = new RelojEntity(ultimoId, fecha, hora, null, personal.getId());
                this.guardarReloj(nuevoReloj);
                listaRelojes.add(nuevoReloj);
            } else { //si existe reloj
                reloj.get(0).setHora_salida(hora);
                this.guardarReloj(reloj.get(0));
                listaRelojes.add(reloj.get(0));
            }
        }
        return listaRelojes;
    }
}
