package org.marketingsms.repository;

import java.util.List;

import org.marketingsms.model.Magasin;
import org.marketingsms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasinRepository extends JpaRepository<Magasin, Integer> {
	
	
	List<Magasin> findByusermag(User iduser);
	Magasin findBycodemagasin(int id);
	Magasin delete(int id);

}
