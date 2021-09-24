package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;


public interface InterQuestionservice {
	public List<Question> findByshop (Shop shop);
	public Question Save (Question question);
	
	public Question delete (long id);
	public Question findByid (long id);
	public Question findbyshopques( Shop shop,int nques);
	


}
