package br.com.restful.services;

import static br.com.restful.mapper.ObjectMapper.parseListObject;
import static br.com.restful.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.restful.controller.PersonController;
import br.com.restful.data.vo.v1.PersonVO;
import br.com.restful.exceptions.RequiredObjectException;
import br.com.restful.exceptions.ResourceNotFoundException;
import br.com.restful.model.Person;
import br.com.restful.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	private Logger   logger = Logger.getLogger(this.getClass().getName());

	public List<PersonVO> findAll() {

		logger.info("Finding all people!");

		var people = parseListObject(repository.findAll(), PersonVO.class);

		people.forEach(person -> {
			person.add(linkTo(methodOn(PersonController.class).findById(person.getPersonId())).withSelfRel());
		});

		return people;
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));

		var vo = parseObject(entity, PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return vo;
	}

	public PersonVO create(PersonVO person) {
		if (person == null) {
			throw new RequiredObjectException();
		}

		logger.info("Creating one person!");

		var entity = parseObject(person, Person.class);

		var vo = parseObject(repository.save(entity), PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPersonId())).withSelfRel());

		return vo;
	}

	public PersonVO update(PersonVO person) {
		if (person == null) {
			throw new RequiredObjectException();
		}

		logger.info("Updating one person!");

		var entity = repository.findById(person.getPersonId())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());

		var vo = parseObject(repository.save(entity), PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getPersonId())).withSelfRel());

		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found by this ID!"));

		repository.delete(entity);
	}
}