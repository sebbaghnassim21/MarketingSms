package org.marketingsms.service;

import org.marketingsms.model.Messagee;

import org.marketingsms.repository.Messageerepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("Servicemessagee")
public class Servicemessagee implements Intermessagee {
	@Autowired
	private Messageerepo messageerepo;
	
	
	@Override
	public Messagee save(Messagee messagee) {
		
		return messageerepo.save(messagee);
	}

}
