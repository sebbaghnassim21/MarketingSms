package org.marketingsms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer>{
	
	/*List<Shop> findByuser(User iduser);*/
	@Query("select  p FROM Shop p where p.user =:user and p.typepiece =:typepiece ")
	@Transactional
	List<Shop> findByuser(@Param("user")User user,@Param("typepiece")String typepiece);
	
	
	@Query("select  p FROM Shop p where p.user =:user  ")
	@Transactional
	List<Shop> findByusertacher(@Param("user")User user);
	
	@Query("select  p FROM Shop p where  p.codemag =:codemag ")
	@Transactional
	Shop findByidshop(@Param("codemag")int codemag);
	
	
	Shop save(Shop shop);
	Shop delete(int id);
	Shop findBycodemag(int idus);
	Shop findBytwilio(String twilio);
    
	@Query("update Shop p set p.nquestion= :nquestion where p.codemag =:codemag")
	@Modifying
	@Transactional
	public void metajournquestion (@Param("nquestion")int nquestion,@Param("codemag")int codemag);

}
