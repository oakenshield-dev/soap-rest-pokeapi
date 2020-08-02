package com.pokesoap.ws.rest;


import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokesoap.wsdl.model.Abilities;
import com.pokesoap.wsdl.model.HeldItems;

@Component
public class PokeapiClient {
	private static final Logger log = LoggerFactory.getLogger(PokeapiClient.class);

	private static final String URL = "https://pokeapi.co/api/v2";
	private static final String SEARCH_PATH = "/pokemon/";
	


	private static RestTemplate rt;
	private static HttpEntity<String> entity;
	
	private ObjectMapper mapper = new ObjectMapper();

	static {
		rt = new RestTemplate();

		HttpHeaders hds = new HttpHeaders();
		hds.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		hds.add("user-agent", "SOAP Client");
		entity = new HttpEntity<String>("parameters", hds);

	}
	
	public List<Abilities> findAbilitiesByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("abilities");
		String nodeStr=mapper.writeValueAsString(node);
		
		List<Abilities> list = mapper.readValue(nodeStr,new TypeReference<List<Abilities>>(){});
		
		return list;
	}

	

	public List<HeldItems> findHeldItemsByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("held_items");
		String nodeStr=mapper.writeValueAsString(node);
		
		List<HeldItems> list = mapper.readValue(nodeStr,new TypeReference<List<HeldItems>>(){});
		
		return list;
	}

	public String findNameByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("name");
		
		return node.asText();
	}
	
	public Integer findIdByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("id");
		
		return node.asInt();
	}
	
	
	public Integer findBaseExperienceByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("base_experience");
		
		return node.asInt();
	}

	public String findLocationAreaEncountersByPokemon(String pokemon) throws JsonMappingException, JsonProcessingException, NullPointerException{
		

		ResponseEntity<String> rsp = rt.exchange(URL + SEARCH_PATH + pokemon , HttpMethod.GET, entity, String.class);

		JsonNode tree=mapper.readTree(rsp.getBody());
		JsonNode node=tree.get("location_area_encounters");
		
		return node.asText();
	}

	

}
