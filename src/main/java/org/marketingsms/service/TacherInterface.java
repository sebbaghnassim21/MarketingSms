package org.marketingsms.service;


import java.util.Date;
import java.util.List;

import org.marketingsms.model.Tacher;
import org.springframework.data.repository.query.Param;



public interface TacherInterface {
	
	
	Tacher save(Tacher tacher);
	List<Tacher> findBydatetime(Date datede,String timede);
	public void metajourtacher(long id);
	public void MetajourTacherTerminer(long id);
	List<Tacher> findBystatut(); //cherche les tacher de statut= 'excution'
	
}
