package org.marketingsms.controller;

import org.marketingsms.service.Serviceclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.marketingsms.model.Aenvoyer;
import org.marketingsms.model.Client;
import org.marketingsms.repository.AenvoyerRepository;
import org.marketingsms.repository.Clientrepository;

@RestController
public class ClientRest {
	@Autowired
	private AenvoyerRepository aenvoyerrepository;

	@RequestMapping(value = "/mesclients", method = RequestMethod.GET)
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	public ResponseEntity<List<Aenvoyer>> Aenvoyer() {

		List<Aenvoyer> result = aenvoyerrepository.findAll();
		return ResponseEntity.ok().body(result);

	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public Aenvoyer save(String ila) {
		Aenvoyer a = new Aenvoyer();

		a.setEnvoyer(1);
		a.setIla(ila);
		return aenvoyerrepository.save(a);

	}

}
