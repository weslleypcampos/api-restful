package br.com.restful.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.restful.data.vo.v1.PersonVO;
import br.com.restful.mapper.ObjectMapper;
import br.com.restful.model.Person;
import br.com.restful.unittests.mapper.mocks.MockPerson;

public class ObjectConverterTest {

	MockPerson inputObject;

	@BeforeEach
	public void setUp() {
		inputObject = new MockPerson();
	}

	@Test
	public void parseEntityToVOTest() {
		PersonVO output = ObjectMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
		assertEquals(Long.valueOf(0L), output.getPersonId());
		assertEquals("First Name Test0", output.getFirstName());
		assertEquals("Last Name Test0", output.getLastName());
		assertEquals("Address Test0", output.getAddress());
		assertEquals("M", output.getGender());
	}

	@Test
	public void parseEntityListToVOListTest() {
		List<PersonVO> outputList = ObjectMapper.parseListObject(inputObject.mockEntityList(), PersonVO.class);
		PersonVO outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getPersonId());
		assertEquals("First Name Test0", outputZero.getFirstName());
		assertEquals("Last Name Test0", outputZero.getLastName());
		assertEquals("Address Test0", outputZero.getAddress());
		assertEquals("M", outputZero.getGender());

		PersonVO outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getPersonId());
		assertEquals("First Name Test7", outputSeven.getFirstName());
		assertEquals("Last Name Test7", outputSeven.getLastName());
		assertEquals("Address Test7", outputSeven.getAddress());
		assertEquals("F", outputSeven.getGender());

		PersonVO outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getPersonId());
		assertEquals("First Name Test12", outputTwelve.getFirstName());
		assertEquals("Last Name Test12", outputTwelve.getLastName());
		assertEquals("Address Test12", outputTwelve.getAddress());
		assertEquals("M", outputTwelve.getGender());
	}

	@Test
	public void parseVOToEntityTest() {
		Person output = ObjectMapper.parseObject(inputObject.mockVO(), Person.class);
		assertEquals(Long.valueOf(0L), output.getId());
		assertEquals("First Name Test0", output.getFirstName());
		assertEquals("Last Name Test0", output.getLastName());
		assertEquals("Address Test0", output.getAddress());
		assertEquals("M", output.getGender());
	}

	@Test
	public void parserVOListToEntityListTest() {
		List<Person> outputList = ObjectMapper.parseListObject(inputObject.mockVOList(), Person.class);
		Person outputZero = outputList.get(0);

		assertEquals(Long.valueOf(0L), outputZero.getId());
		assertEquals("First Name Test0", outputZero.getFirstName());
		assertEquals("Last Name Test0", outputZero.getLastName());
		assertEquals("Address Test0", outputZero.getAddress());
		assertEquals("M", outputZero.getGender());

		Person outputSeven = outputList.get(7);

		assertEquals(Long.valueOf(7L), outputSeven.getId());
		assertEquals("First Name Test7", outputSeven.getFirstName());
		assertEquals("Last Name Test7", outputSeven.getLastName());
		assertEquals("Address Test7", outputSeven.getAddress());
		assertEquals("F", outputSeven.getGender());

		Person outputTwelve = outputList.get(12);

		assertEquals(Long.valueOf(12L), outputTwelve.getId());
		assertEquals("First Name Test12", outputTwelve.getFirstName());
		assertEquals("Last Name Test12", outputTwelve.getLastName());
		assertEquals("Address Test12", outputTwelve.getAddress());
		assertEquals("M", outputTwelve.getGender());
	}
}