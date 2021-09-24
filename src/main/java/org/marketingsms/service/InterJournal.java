package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Journal;
import org.marketingsms.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;


public interface InterJournal {
	public List<Journal> findByShopjou(Shop shop);
	public Journal save(Journal journal);
	public Journal findByid(long id);
	public Page<Journal> findByIdquestion(long Idquestion,int page,int size);
	Page<Journal> recherche(long Idquestion,String mc,int page,int size);//,PageRequest p
	
	public int countbyid(long Idquestion,String mc);
	
	int countclient(@Param("shop")Shop shop);
	
	public Journal findbyshopquestion(Shop shop,int nques);
	
	List<Journal> listrecherche(long Idquestion,String mc);
	List<Journal> findByIdquestionlist(long Idquestion);

}
