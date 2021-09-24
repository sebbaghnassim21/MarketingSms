package org.marketingsms.repository;



import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;


import org.marketingsms.model.Tacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TacherRepository extends JpaRepository<Tacher, Integer> {

	@Query("select  p FROM Tacher p where p.datede =:datede and p.timede =:timede and p.statut='Attente' ")
	@Transactional
	List<Tacher> findBydatetime(@Param("datede")Date datede,@Param("timede")String timede);
	
	
	Tacher save(Tacher tacher);
	
	
	@Query("update Tacher p set p.statut= 'execution' where p.id =:id")
	@Modifying
	@Transactional
	public void metajourtacher(@Param("id")long id);
	
	@Query("update Tacher p set p.statut= 'Terminer' where p.id =:id")
	@Modifying
	@Transactional
	public void MetajourTacherTerminer(@Param("id")long id);
	
	@Query("select  p FROM Tacher p where  p.statut='execution' ")
	@Transactional
	List<Tacher> findBystatut();
}
