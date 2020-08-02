package com.pokesoap.ws.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class RequestLog {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private Date requestDate;
	
	@Column(nullable = false)
	private String method;
	
	@Column(nullable = false)
	private String ipAddress;
	
	public RequestLog(String method, String ip,Date date) {
		this.requestDate=date;
		this.ipAddress=ip;
		this.method=method;
	}
	
}
