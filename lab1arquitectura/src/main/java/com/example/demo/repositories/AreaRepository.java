package com.example.demo.repositories;

import com.example.demo.entities.AreaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends CrudRepository<AreaEntity, Long> {
}
