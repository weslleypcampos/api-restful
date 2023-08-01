package br.com.restful.unittests.services;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.restful.exceptions.RequiredObjectException;
import br.com.restful.repository.PersonRepository;
import br.com.restful.services.PersonServices;
import br.com.restful.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = LENIENT)
class PersonServicesTest {

	MockPerson             input;

	@InjectMocks
	private PersonServices service;

	@Mock
	PersonRepository       repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockPerson();

		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		var list = input.mockEntityList();

		when(repository.findAll()).thenReturn(list);

		var result = service.findAll();

		assertNotNull(result);
		assertEquals(14, result.size());

		var first = result.get(1);

		assertNotNull(first);
		assertNotNull(first.getPersonId());
		assertNotNull(first.getLinks());
		assertTrue(first.toString().contains("rel=\"self\""));
		assertEquals("Address Test1", first.getAddress());
		assertEquals("First Name Test1", first.getFirstName());
		assertEquals("Last Name Test1", first.getLastName());
		assertEquals("F", first.getGender());

		var fouth = result.get(4);

		assertNotNull(fouth);
		assertNotNull(fouth.getPersonId());
		assertNotNull(fouth.getLinks());
		assertTrue(fouth.toString().contains("rel=\"self\""));
		assertEquals("Address Test4", fouth.getAddress());
		assertEquals("First Name Test4", fouth.getFirstName());
		assertEquals("Last Name Test4", fouth.getLastName());
		assertEquals("M", fouth.getGender());

		var thirteenth = result.get(13);

		assertNotNull(thirteenth);
		assertNotNull(thirteenth.getPersonId());
		assertNotNull(thirteenth.getLinks());
		assertTrue(thirteenth.toString().contains("rel=\"self\""));
		assertEquals("Address Test13", thirteenth.getAddress());
		assertEquals("First Name Test13", thirteenth.getFirstName());
		assertEquals("Last Name Test13", thirteenth.getLastName());
		assertEquals("F", thirteenth.getGender());
	}

	@Test
	void testFindById() {
		var person = input.mockEntity(1);

		when(repository.findById(1L)).thenReturn(of(person));

		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("rel=\"self\""));
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("F", result.getGender());
	}

	@Test
	void testCreate() {
		var entity = input.mockEntity(1);
		var vo = input.mockVO(1);

		when(repository.save(entity)).thenReturn(entity);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("rel=\"self\""));
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("F", result.getGender());
	}

	@Test
	void testCreateNull() {

		Exception ex = assertThrows(RequiredObjectException.class, () -> {
			service.create(null);
		});

		String expected = "It is not allowed to persist a null object";
		String actual = ex.getMessage();

		assertTrue(actual.contains(expected));
	}

	@Test
	void testUpdate() {
		var entity = input.mockEntity(1);
		var vo = input.mockVO(1);

		when(repository.findById(1L)).thenReturn(of(entity));
		when(repository.save(entity)).thenReturn(entity);

		var result = service.update(vo);

		assertNotNull(result);
		assertNotNull(result.getPersonId());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("rel=\"self\""));
		assertEquals("Address Test1", result.getAddress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("F", result.getGender());
	}

	@Test
	void testUpdateNull() {

		Exception ex = assertThrows(RequiredObjectException.class, () -> {
			service.update(null);
		});

		String expected = "It is not allowed to persist a null object";
		String actual = ex.getMessage();

		assertTrue(actual.contains(expected));
	}

	@Test
	void testDelete() {
		var person = input.mockEntity(1);

		when(repository.findById(1L)).thenReturn(of(person));

		service.delete(1L);
	}

}
