package com.example.demo.repositories;

import com.example.demo.entities.PagoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PagoRepository extends CrudRepository<PagoEntity, Long> {
    @Query(value = "SELECT pago.sueldo_fijo FROM personal, pago WHERE pago.id_categoria=personal.id_categoria AND pago.id_area=personal.id_area AND personal.id=:id", nativeQuery=true)
    Integer buscarSueldoFijoMensual(@Param("id") Long id);

    @Query(value = "SELECT pago.valor_hora_extra FROM personal, pago WHERE pago.id_categoria=personal.id_categoria AND pago.id_area=personal.id_area AND personal.id=:id", nativeQuery=true)
    Integer buscarValorHoraExtra(@Param("id") Long id);

}
