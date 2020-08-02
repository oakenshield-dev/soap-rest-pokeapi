package com.pokesoap.ws.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "abilities", "base_experience", "held_items", "id", "location_area_encounters", "name" })
public class Pokemon {

	@JsonProperty("abilities")
	private List<Object> abilities = null;

	@JsonProperty("base_experience")	
	private int baseExperience = 0;

	@JsonProperty("held_items")	
	private List<Object> heldItems = null;

	@JsonProperty("id")
	private int id = 0;

	@JsonProperty("location_area_encounters")
	@JsonPropertyDescription("An explanation about the purpose of this instance.")
	private String locationAreaEncounters = "";

	@JsonProperty("name")
	@JsonPropertyDescription("An explanation about the purpose of this instance.")
	private String name = "";

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}
