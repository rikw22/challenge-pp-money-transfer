package org.example.ppmoneytransfer.repository;

import org.example.ppmoneytransfer.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}