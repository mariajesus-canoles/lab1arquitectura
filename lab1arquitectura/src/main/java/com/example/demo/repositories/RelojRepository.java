package com.example.demo.repositories;

import com.example.demo.entities.RelojEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;


@Repository
public interface RelojRepository extends CrudRepository<RelojEntity, Long>{
    @Query(value = "SELECT reloj.hora_salida FROM reloj WHERE reloj.id_personal=:id_personal AND reloj.fecha=:fecha", nativeQuery = true)
    LocalTime buscarHoraSalida(@Param("id_personal") Long id_personal, @Param("fecha") LocalDate fecha);

    @Query(value = "SELECT COUNT (DISTINCT reloj.fecha) FROM reloj ", nativeQuery = true)
    Integer buscarNumDiasDelMes();

    @Query(value = "SELECT * FROM reloj WHERE reloj.id_personal=:id_personal", nativeQuery = true)
    ArrayList<RelojEntity> buscarRelojesDePersonal(@Param("id_personal") Long id_personal);

    @Query(value = "SELECT DISTINCT reloj.fecha FROM reloj", nativeQuery = true)
    ArrayList<String> buscarFechasDelMes();

    @Query(value = "SELECT * FROM reloj WHERE reloj.id_personal=:id_personal AND reloj.fecha=:fecha", nativeQuery = true)
    RelojEntity buscarRelojDePersonal(@Param("id_personal") Long id_personal, @Param("fecha") LocalDate fecha);

    @Query(value = "SELECT personal.id FROM personal WHERE personal.rut=:rut", nativeQuery = true)
    Long buscarIdPersonalPorRut(@Param("rut") String rut);

    @Query(value = "SELECT * FROM reloj WHERE reloj.id_personal=:id AND reloj.fecha=:fecha", nativeQuery = true)
    ArrayList<RelojEntity> buscarRelojDePersonalPorId(@Param("id") Long id, @Param("fecha") LocalDate fecha);

    @Query(value = "SELECT reloj.id FROM personal INNER JOIN reloj ON personal.rut=:rut AND personal.id=reloj.id_personal AND reloj.fecha=:fecha", nativeQuery = true)
    ArrayList<Long> buscarRelojPorRutYFecha(@Param("rut") String rut, @Param("fecha") LocalDate fecha);

    @Query(value = "SELECT MAX(reloj.id) FROM reloj", nativeQuery = true)
    ArrayList<Long> buscarUltimoId();
    @Modifying
    @Query(value = "INSERT INTO public.reloj (fecha, hora_entrada, hora_salida, id_personal) VALUES (:fecha, :hora_entrada, :hora_salida, :id_personal)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("fecha") LocalDate fecha,
                       @Param("hora_entrada") LocalTime hora_entrada,
                       @Param("hora_salida") LocalTime hora_salida,
                       @Param("id_personal") Long id_personal);

    @Modifying
    @Query(value = "UPDATE public.reloj SET hora_salida=:hora_salida WHERE reloj.id=:id",
            nativeQuery = true)
    @Transactional
    void actualizarQuery(@Param("id") Long id,
                         @Param("hora_salida") LocalTime hora_salida);




}
