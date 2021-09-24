package org.marketingsms.repository;


import java.util.List;

import org.marketingsms.model.Journal;
import org.marketingsms.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Journalrepository extends JpaRepository<Journal, Long>  {
	
	List<Journal> findByShopjou(Shop shop);
	Journal save(Journal journal);
	Journal findByid(long id);
	Page<Journal> findByIdquestion(long Idquestion,Pageable pageable);//,PageRequest p
	
	
	@Query("select  p FROM Journal p where p.idquestion =:id  ")
	@Transactional
	List<Journal> findByIdquestionlist(@Param("id")long Idquestion);
	
	
	
	@Query("select  p FROM Journal p where p.idquestion =:id and p.reponse like :mc ")
	@Transactional
	Page<Journal> recherche(@Param("id")long Idquestion,@Param("mc")String mc,Pageable pageable);//,PageRequest p
	
	@Query("select  p FROM Journal p where p.idquestion =:id and p.reponse like :mc ")
	@Transactional
	List<Journal> listrecherche(@Param("id")long Idquestion,@Param("mc")String mc);
	
	
	@Query("SELECT COUNT(p) FROM Journal p where p.idquestion =:id and p.reponse like :mc ")
	@Transactional
	int countbyid(@Param("id")long Idquestion,@Param("mc")String mc);
	
	@Query("SELECT COUNT(p) FROM Journal p where p.shopjou =:shop  ")
	@Transactional
	int countclient(@Param("shop")Shop shop);
	
	@Query("select  p FROM Journal p where p.shopjou =:shop and p.nquestion =:nques ")
	@Transactional
	Journal findbyshopquestion(@Param("shop")Shop shop,@Param("nques")int nques);

}
