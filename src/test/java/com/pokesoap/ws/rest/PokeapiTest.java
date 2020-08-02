package com.pokesoap.ws.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


class PokeapiTest {

	private static final Logger log = LoggerFactory.getLogger(PokeapiTest.class);
	private static String URL = "https://pokeapi.co/api/v2";
	static ObjectMapper mapper=new ObjectMapper();
	static RestTemplate rt;
	static HttpEntity<String> entity;
	static String full_path= "/pokemon/ditto";

	@BeforeAll
	public static void setUp() {
		 rt = new RestTemplate();

		HttpHeaders hds = new HttpHeaders();
		hds.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		hds.add("user-agent", "SOAP Client");
		entity = new HttpEntity<String>("parameters", hds);
	}

	@Test
	public void basicRequestPokeapi() throws JsonProcessingException {
		log.info("test request to API https://pokeapi.co/");

		ResponseEntity<Pokemon> rsp = rt.exchange(URL + full_path, HttpMethod.GET, entity,Pokemon.class);

		assertNotNull(rsp);
		//log.info(rsp.getBody().toString());
		log.info(mapper.writeValueAsString(rsp.getBody()));
		assertEquals(rsp.getStatusCodeValue(), 200);
	}
	
	@Test
	public void basicSearchPokeapi() throws JsonProcessingException {
		log.info("test request by query to API https://pokeapi.co/");
		String pathParam="/pokemon/pikachu";

		ResponseEntity<Pokemon> rsp = rt.exchange(URL + pathParam , HttpMethod.GET, entity, Pokemon.class);

		assertNotNull(rsp);
		//log.info(rsp.getBody().toString());
		log.info(mapper.writeValueAsString(rsp.getBody()));
		assertEquals(rsp.getStatusCodeValue(), 200);
	}
	
	@Test
	public void nofoundSearchPokeapi() throws JsonProcessingException {
		log.info("test request by query to API https://pokeapi.co/");
		String pathParam="/pokemon/hulk";
		
		try {
			ResponseEntity<Pokemon> rsp = rt.exchange(URL + pathParam , HttpMethod.GET, entity, Pokemon.class);
			assertNull(rsp);
		} catch (HttpClientErrorException e) {
			
			log.info(e.getMessage());
			assertEquals(e.getRawStatusCode(), 404);
		}
		
	}
	
	
	
	

}
