package com.example.demo.repositories;

import com.example.demo.entities.RelojEntity;
import net.bytebuddy.asm.Advice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}
