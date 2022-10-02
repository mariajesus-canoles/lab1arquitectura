package com.example.demo.repositories;

import com.example.demo.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaEntity, Long> {

    @Query(value = "SELECT categoria.nombre FROM personal, categoria WHERE personal.id_categoria=categoria.id AND personal.id=:id", nativeQuery = true)
    String buscarCategoria(@Param("id") Long id);
}
