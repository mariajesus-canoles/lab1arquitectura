package com.example.demo.repositories;

import com.example.demo.entities.JustificativoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificativoRepository extends CrudRepository<JustificativoEntity, Long> {
}
