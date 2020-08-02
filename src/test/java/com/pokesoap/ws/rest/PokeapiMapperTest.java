package com.pokesoap.ws.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokesoap.wsdl.model.*;

class PokeapiMapperTest {

	private static final Logger log = LoggerFactory.getLogger(PokeapiMapperTest.class);
	private static String URL = "https://pokeapi.co/api/v2";
	static ObjectMapper mapper=new ObjectMapper();
	static RestTemplate rt;
	static HttpEntity<String> entity;


	@BeforeAll
	public static void setUp() {
		 rt = new RestTemplate();

		HttpHeaders hds = new HttpHeaders();
		hds.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		hds.add("user-agent", "SOAP Client");
		entity = new HttpEntity<String>("parameters", hds);
	}
	
	@Test
	void parseAbilitiesTest() throws JsonProcessingException {
		log.info("test request by query to API https://pokeapi.co/");
		String pathParam="/pokemon/pikachu";

		ResponseEntity<String> rsp = rt.exchange(URL + pathParam , HttpMethod.GET, entity, String.class);

		assertNotNull(rsp);
		
		JsonNode node=mapper.readTree(rsp.getBody());
		JsonNode abilitiesNode=node.get("abilities");
		String abilitiesStr=mapper.writeValueAsString(abilitiesNode);
		//mapper.readValue(abilitiesNode.asText(), new TypeReference<List<Ab>>(){});
		List<Abilities> list = mapper.readValue(abilitiesStr,new TypeReference<List<Abilities>>(){});
		assertNotNull(list);
		assertTrue(list.size()>0);
		assertEquals(rsp.getStatusCodeValue(), 200);
	}

	@Test	
	void parseHealdItemsTest() throws JsonProcessingException {
		log.info("test request by query to API https://pokeapi.co/");
		String pathParam="/pokemon/pikachu";

		ResponseEntity<String> rsp = rt.exchange(URL + pathParam , HttpMethod.GET, entity, String.class);

		assertNotNull(rsp);
		
		JsonNode node=mapper.readTree(rsp.getBody());
		JsonNode heldItemsNode=node.get("held_items");
		String heldItemsStr=mapper.writeValueAsString(heldItemsNode);
		log.info(heldItemsStr);
		List<HeldItems> list = mapper.readValue(heldItemsStr,new TypeReference<List<HeldItems>>(){});
		assertNotNull(list);
		assertTrue(list.size()>0);
		assertEquals(rsp.getStatusCodeValue(), 200);
	}

}
