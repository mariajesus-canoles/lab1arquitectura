package com.example.demo.repositories;

import com.example.demo.entities.HoraExtraEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface HoraExtraRepository extends CrudRepository<HoraExtraEntity, Long> {
    @Query(value = "SELECT * FROM hora_extra WHERE hora_extra.id_personal=:id", nativeQuery = true)
    ArrayList<HoraExtraEntity> buscarHorasExtrasPorIdPersonal(@Param("id") Long id);
}
