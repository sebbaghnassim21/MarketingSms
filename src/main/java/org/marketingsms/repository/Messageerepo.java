package org.marketingsms.repository;


import org.marketingsms.model.Messagee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Messageerepo extends JpaRepository<Messagee, Long> {
	


	Messagee save(Messagee messagee);

}
