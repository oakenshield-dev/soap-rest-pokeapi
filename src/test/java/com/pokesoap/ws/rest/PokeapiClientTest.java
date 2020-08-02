package com.pokesoap.ws.rest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.pokesoap.wsdl.model.Abilities;
import com.pokesoap.wsdl.model.HeldItems;


class PokeapiClientTest {
	private static final Logger log = LoggerFactory.getLogger(PokeapiClientTest.class);
	private static PokeapiClient rest;

	@BeforeAll
	static void setUp() {
		rest=new PokeapiClient();
	}
	
	@Test
	public void fidDataByPokemon() throws JsonMappingException, JsonProcessingException, NullPointerException {
		List<Abilities> abilities=null;
		List<HeldItems> heldItems=null;
		
		abilities=rest.findAbilitiesByPokemon("pikachu");
		
		assertNotNull(abilities);
		
		heldItems=rest.findHeldItemsByPokemon("pikachu");
		
		assertNotNull(heldItems);
		
		Integer be=rest.findBaseExperienceByPokemon("pikachu");
		
		assertNotNull(be);
		
		Integer id=rest.findIdByPokemon("pikachu");
		assertNotNull(id);
		
		String loc=rest.findLocationAreaEncountersByPokemon("pikachu");
		log.info("location: "+loc);
		assertNotNull(loc);

		String name=rest.findNameByPokemon("pikachu");
		assertNotNull(name);
		
	}

}
