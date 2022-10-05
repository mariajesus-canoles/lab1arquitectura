package com.example.demo.repositories;

import com.example.demo.entities.JustificativoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;

@Repository
public interface JustificativoRepository extends CrudRepository<JustificativoEntity, Long> {
    @Query(value = "SELECT * FROM justificativo WHERE justificativo.id_personal=:id", nativeQuery = true)
    ArrayList<JustificativoEntity> buscarJustificativosDePersonal(@Param("id") Long id);

    @Modifying
    @Query(value = "INSERT INTO public.justificativo (fecha, id_personal) VALUES (:fecha, :id_personal)",
            nativeQuery = true)
    @Transactional
    void ingresarQuery(@Param("fecha") LocalDate fecha,
                       @Param("id_personal") Long id_personal);
}
