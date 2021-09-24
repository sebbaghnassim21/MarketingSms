package org.marketingsms.repository;

import java.util.List;

import org.marketingsms.model.Aenvoyer;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(collectionResourceRel = "envoyer", path = "envoyer")
public interface AenvoyerRepository extends CrudRepository<Aenvoyer, Long> {
	
	List<Aenvoyer> findAll();

}
