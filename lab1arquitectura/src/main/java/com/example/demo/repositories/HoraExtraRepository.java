package com.example.demo.repositories;

import com.example.demo.entities.HoraExtraEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoraExtraRepository extends CrudRepository<HoraExtraEntity, Long> {
}
