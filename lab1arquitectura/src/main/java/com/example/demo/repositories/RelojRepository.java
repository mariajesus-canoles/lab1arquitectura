package com.example.demo.repositories;

import com.example.demo.entities.RelojEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelojRepository extends CrudRepository<RelojEntity, Long>{
}
