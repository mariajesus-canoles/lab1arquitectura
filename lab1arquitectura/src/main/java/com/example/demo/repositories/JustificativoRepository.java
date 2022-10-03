package com.example.demo.repositories;

import com.example.demo.entities.JustificativoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface JustificativoRepository extends CrudRepository<JustificativoEntity, Long> {
    @Query(value = "SELECT * FROM justificativo WHERE justificativo.id_personal=:id", nativeQuery = true)
    ArrayList<JustificativoEntity> buscarJustificativosDePersonal(@Param("id") Long id);
}
