package br.com.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.restful.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}