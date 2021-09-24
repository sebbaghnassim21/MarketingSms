package org.marketingsms.service;


import java.util.Date;
import java.util.List;

import org.marketingsms.model.Tacher;
import org.marketingsms.repository.TacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("TacherService")
public class TacherService implements TacherInterface{
   
	
	@Autowired
	private TacherRepository tacherrepository;
	
	@Override
	public Tacher save(Tacher tacher) {
		// TODO Auto-generated method stub
		return tacherrepository.save(tacher);
	}

	@Override
	public List<Tacher> findBydatetime(Date datede, String timede) {
		// TODO Auto-generated method stub
		return tacherrepository.findBydatetime(datede, timede);
	}

	@Override
	public void metajourtacher(long id) {
		tacherrepository.metajourtacher(id);
		
	}

	@Override
	public List<Tacher> findBystatut() {
		// TODO Auto-generated method stub
		return tacherrepository.findBystatut();
	}

	@Override
	public void MetajourTacherTerminer(long id) {
		tacherrepository.MetajourTacherTerminer(id);
		
	}
	

}
