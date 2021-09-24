package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Magasin;
import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.marketingsms.repository.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("MagService")
public class MagServiceInter implements MagService {
    @Autowired
	private MagasinRepository magasinrepository;
	
	@Override
	public List<Magasin> findByusermag(User iduser) {
		
		return magasinrepository.findByusermag(iduser);
	}

	@Override
	public Magasin Save(Magasin magasin) {
		// TODO Auto-generated method stub
		return magasinrepository.save(magasin);
	}

	@Override
	public Magasin findBycodemagasin(int id) {
		// TODO Auto-generated method stub
		return magasinrepository.findBycodemagasin(id);
	}

	@Override
	public Magasin delete(int id) {
		// TODO Auto-generated method stub
		return magasinrepository.delete(id);
	}

}
