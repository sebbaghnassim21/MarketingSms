package org.marketingsms.repository;

import java.util.List;

import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
	List<Question> findByshop(Shop shop);
	@SuppressWarnings("unchecked")
	Question save(Question question);
	
	
	Question delete(long id);
	Question findByid(long idq);
	@Query("select  p FROM Question p where p.shop =:shop and p.nques =:nques ")
	@Transactional
	Question findbyshopques(@Param("shop")Shop shop,@Param("nques")int nques);
}
