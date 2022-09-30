package com.example.demo.repositories;


import com.example.demo.entities.PersonalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRepository extends CrudRepository<PersonalEntity, Long> {
}

