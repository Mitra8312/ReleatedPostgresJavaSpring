package com.example.daosism.Repositories;

import com.example.daosism.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
    Person getPersonById(Integer id);
}
