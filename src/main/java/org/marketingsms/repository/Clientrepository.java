package org.marketingsms.repository;

import java.util.Date;
import java.util.List;

import org.marketingsms.model.Client;

import org.marketingsms.model.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;


@RepositoryRestResource
public interface Clientrepository extends JpaRepository<Client, Long> {
	Page<Client> findByshopc(Shop shop,Pageable pageable);
	 List<Client> findByshopc(Shop shop);
	Client save(Client client);
	Client findByid(long id);
	
    @RestResource
    List<Client> findAll();
	Client findByfromn(String fromn);
	@Query("select  p FROM Client p where p.fromn =:fromn and p.ton =:ton ")
	@Transactional
	Client findbyreq(@Param("fromn")String fromn,@Param("ton")String ton);
	
	@Query("select  p FROM Client p where p.fromn =:fromn and p.ton =:ton and p.shopc =:shopc ")
	@Transactional
	Client findbygreeting(@Param("fromn")String fromn,@Param("ton")String ton,@Param("shopc")Shop shopc);
	
	
	@Query("select  p.fromn FROM Client p where p.shopc =:shopc group by p.fromn  ")
	@Transactional
	List<Client> findbyshopgroup(@Param("shopc")Shop shopc);
	
	
	@Query("update Client p set p.nquestion= :nquestion , p.typequestion= :typequestion where p.id =:id")
	@Modifying
	@Transactional
	public void metajourclient (@Param("nquestion")int nquestion,@Param("typequestion") String typequestion ,@Param("id")long id);
	
	@Query("update Client p set p.date= :date,p.ndefois= :ndefois ,p.nquestion= :nquestion ,p.redirect=false,p.ndques=0 ,p.typequestion= :typequestion where p.id =:id")
	@Modifying
	@Transactional
	public void metajour (@Param("date")Date date,@Param("ndefois")int ndefois,@Param("nquestion")int nquestion,@Param("typequestion") String typequestion ,@Param("id")long id);
	
	
	@Query("update Client p set p.ndques= :ndques,redirect=true  where p.id =:id")
	@Modifying
	@Transactional
	public void metajourdq (@Param("ndques")int ndques ,@Param("id")long id);
	
	
	@Query("SELECT COUNT(p) FROM Client p where p.shopc =:shop  ")
	@Transactional
	int couclient(@Param("shop")Shop shop);
	
	@Query("select  p FROM Client p where p.shopc =:shop and p.fromn like :mc ")
	
	public Page<Client> chercher(@Param("mc")String mc,@Param("shop")Shop shop,Pageable pageable);
    

	
	Client delete(long id);
	
}
