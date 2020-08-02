package com.pokesoap.ws.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestLogDataAccess extends CrudRepository<RequestLog,Integer>{
    
     
	public RequestLog save(RequestLog entity);
    
}
