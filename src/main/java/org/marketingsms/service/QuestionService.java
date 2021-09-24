package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.marketingsms.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("questionservice")
public class QuestionService implements InterQuestionservice {
    @Autowired
	private QuestionRepository questionrepository;
	@Override
	public List<Question> findByshop(Shop id) {
		
		return questionrepository.findByshop(id);
	}

	@Override
	public Question Save(Question question) {
	
		return questionrepository.save(question);
	}

	@Override
	public Question delete(long id) {
		
		return questionrepository.delete(id);
	}

	@Override
	public Question findByid(long id) {
	
		return questionrepository.findByid(id);
	}

	@Override
	public Question findbyshopques(Shop shop, int nques) {
		
		return questionrepository.findbyshopques(shop, nques);
	}

	


}
