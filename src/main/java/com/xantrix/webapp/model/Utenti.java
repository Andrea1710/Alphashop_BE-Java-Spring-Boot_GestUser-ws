package com.xantrix.webapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "utenti")
@Data
public class Utenti
{
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String userId;
	private String password;
	private String attivo;
	
	private List<String> ruoli;
	
}
