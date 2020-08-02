package com.pokesoap.ws;

import java.util.Calendar;

import javax.management.ServiceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pokesoap.ws.data.RequestLog;
import com.pokesoap.ws.data.RequestLogDataAccess;
import com.pokesoap.ws.rest.PokeapiClient;

import com.pokesoap.wsdl.model.GetAbilitiesRequest;
import com.pokesoap.wsdl.model.GetAbilitiesResponse;
import com.pokesoap.wsdl.model.GetBaseExperienceRequest;
import com.pokesoap.wsdl.model.GetBaseExperienceResponse;
import com.pokesoap.wsdl.model.GetHeldItemsRequest;
import com.pokesoap.wsdl.model.GetHeldItemsResponse;
import com.pokesoap.wsdl.model.GetIdRequest;
import com.pokesoap.wsdl.model.GetIdResponse;
import com.pokesoap.wsdl.model.GetLocationAreaEncountersRequest;
import com.pokesoap.wsdl.model.GetLocationAreaEncountersResponse;
import com.pokesoap.wsdl.model.GetNameRequest;
import com.pokesoap.wsdl.model.GetNameResponse;



@Endpoint
public class PokemonEndpoint {
  
	private static final Logger log = LoggerFactory.getLogger(PokemonEndpoint.class);

	private static final String NAMESPACE_URI = "http://www.pokesoap.com/wsdl/model";

	private PokeapiClient srv;
	private WebUtils web;
	private RequestLogDataAccess dao;

	@Autowired
	public PokemonEndpoint(PokeapiClient pokeapiClient,WebUtils web,RequestLogDataAccess dao) {
		this.srv = pokeapiClient;
		this.web=web;
		this.dao=dao;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
	@ResponsePayload
	public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest rq) throws ServiceNotFoundException {
		GetAbilitiesResponse rsp = new GetAbilitiesResponse();

		try {
			rsp.getAbilities().addAll(srv.findAbilitiesByPokemon(rq.getName()));
			
			dao.save(new RequestLog("getAbilitiesRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeldItemsRequest")
	@ResponsePayload
	public GetHeldItemsResponse getHeldItems(@RequestPayload GetHeldItemsRequest rq) throws ServiceNotFoundException {
		GetHeldItemsResponse rsp = new GetHeldItemsResponse();
		try {
			rsp.getHeldItems().addAll(srv.findHeldItemsByPokemon(rq.getName()));
			dao.save(new RequestLog("getHeldItemsRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBaseExperienceRequest")
	@ResponsePayload
	public GetBaseExperienceResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest rq) throws ServiceNotFoundException {
		GetBaseExperienceResponse rsp = new GetBaseExperienceResponse();
		try {
			rsp.setBaseExperience(srv.findBaseExperienceByPokemon(rq.getName()));
			dao.save(new RequestLog("getBaseExperienceRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
			
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
	@ResponsePayload
	public GetIdResponse getIdRequest(@RequestPayload GetIdRequest rq) throws ServiceNotFoundException {
		GetIdResponse rsp = new GetIdResponse();
		try {
			rsp.setId(srv.findIdByPokemon(rq.getName()));
			dao.save(new RequestLog("getIdRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
	@ResponsePayload
	public GetNameResponse getName(@RequestPayload GetNameRequest rq) throws ServiceNotFoundException {
		GetNameResponse rsp = new GetNameResponse();
		try {
			rsp.setName(srv.findNameByPokemon(rq.getName()));
			dao.save(new RequestLog("getNameRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationAreaEncountersRequest")
	@ResponsePayload
	public GetLocationAreaEncountersResponse getLocationAreaEncounters(
			@RequestPayload GetLocationAreaEncountersRequest rq) throws ServiceNotFoundException {
		GetLocationAreaEncountersResponse rsp = new GetLocationAreaEncountersResponse();
		try {
			
			rsp.setLocationAreaEncounters(srv.findLocationAreaEncountersByPokemon(rq.getName()));
			dao.save(new RequestLog("getLocationAreaEncountersRequest", web.getClientIp(),Calendar.getInstance().getTime()));
			return rsp;
		} catch (JsonProcessingException | NullPointerException e) {
			throw new ServiceNotFoundException();
		}
	}

}
