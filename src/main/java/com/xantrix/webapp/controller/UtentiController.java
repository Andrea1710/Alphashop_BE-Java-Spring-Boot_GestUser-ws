package com.xantrix.webapp.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.xantrix.webapp.model.Utenti;
 
import com.xantrix.webapp.service.UtentiService;
 
@RestController
public class UtentiController
{
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	UtentiService utentiService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<Utenti> getAllUser()
	{
		LOG.info("Otteniamo tutti gli utenti");

		return utentiService.SelTutti();
	}
	
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public Utenti getUtente(@PathVariable("userId") String UserId) 
	{
		LOG.info("Otteniamo l'utente " + UserId);
		
		Utenti retVal = utentiService.SelUser(UserId);
		
		return retVal;
	}
	
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public ResponseEntity<Utenti> addNewUser(@RequestBody Utenti utente)
	{
		LOG.info("Inserimento Nuovo Utente");
		
		String encodedPassword = passwordEncoder.encode("Andrax74");
		utente.setPassword(encodedPassword);
		
		
		List<String> Ruoli = new ArrayList<String>();
		Ruoli.add("USER");
		Ruoli.add("ADMIN");
		
		utente.setUserId("Nicola");
		
		utente.setRuoli(Ruoli);
		
		utentiService.Save(utente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(utente.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
}
