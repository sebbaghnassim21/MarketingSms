package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Journal;
import org.marketingsms.model.Shop;
import org.marketingsms.repository.Journalrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service("Servicejournal")
public class Servicejournal implements InterJournal {

	@Autowired
	private Journalrepository journalrepository;
	
	@Override
	public List<Journal> findByShopjou(Shop shop) {
		
		return journalrepository.findByShopjou(shop);
	}

	@Override
	public Journal save(Journal journal) {
		
		return journalrepository.save(journal);
	}

	@Override
	public Journal findByid(long id) {
		
		return journalrepository.findByid(id);
	}

	@Override
	public Journal findbyshopquestion(Shop shop, int nques) {
		
		return journalrepository.findbyshopquestion(shop, nques);
	}

	@Override
	public Page<Journal> findByIdquestion(long Idquestion,int page,int size) {
	
		return journalrepository.findByIdquestion(Idquestion,new PageRequest(page, size) );
	}

	@Override
	public Page<Journal> recherche(long Idquestion, String mc, int page, int size) {
		// TODO Auto-generated method stub
		return journalrepository.recherche(Idquestion, mc, new PageRequest(page, size));
	}

	@Override
	public int countbyid(long Idquestion, String mc) {
		
		return journalrepository.countbyid(Idquestion, mc);
	}

	@Override
	public int countclient(Shop shop) {
		// TODO Auto-generated method stub
		return journalrepository.countclient(shop);
	}

	@Override
	public List<Journal> listrecherche(long Idquestion, String mc) {
		
		return journalrepository.listrecherche(Idquestion, mc);
	}

	@Override
	public List<Journal> findByIdquestionlist(long Idquestion) {
		// TODO Auto-generated method stub
		return journalrepository.findByIdquestionlist(Idquestion);
	}

}
