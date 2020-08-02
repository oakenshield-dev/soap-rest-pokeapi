package com.pokesoap.ws.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.pokesoap.wsdl.model.Abilities;
import com.pokesoap.wsdl.model.Abilities.Ability;

@Component
public class PokemonService {
	
	List<Abilities>  abilities=new ArrayList<>();
	
	Abilities abs = null;
	
	@PostConstruct
	public void initialize() {
		 abs = new Abilities();
		
		Ability ab = new Ability();
		ab.setName("habilidad 1");
		ab.setUrl("URL 1");
		
		abs.setAbility(ab);
		abs.setIs_hidden(true);
		abs.setSlot(Short.valueOf("1"));

		
		//primer elemento
		abilities.add(abs);
		
		abs = new Abilities();
		
		ab = new Ability();
		ab.setName("habilidad 2");
		ab.setUrl("URL 2");
		
		abs.setAbility(ab);
		abs.setIs_hidden(true);
		abs.setSlot(Short.valueOf("2"));

		
		//Segundo elemento
		abilities.add(abs);
		
	}
	
	public List<Abilities> findAbilities() {
		return abilities;
	}

}
