package org.marketingsms.service;



import java.util.Date;
import java.util.List;

import org.marketingsms.model.Client;
import org.marketingsms.model.Shop;
import org.marketingsms.repository.Clientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service("Serviceclient")
public class Serviceclient implements Interclient {
	@Autowired
	private Clientrepository clientrepository;



	@Override
	public Client save(Client client) {
		
		return clientrepository.save(client);
	}

	@Override
	public Client findByid(long id) {
		
		return clientrepository.findByid(id);
	}

	@Override
	public Client findbyre(String fromn, String ton) {
		
		return clientrepository.findbyreq(fromn, ton);
	}

	@Override
	public Client findByfromn(String fromn) {
		
		return clientrepository.findByfromn(fromn);
				}

	@Override
	public void metajourclient(int nquestion,String typequestion, long id) {
		clientrepository.metajourclient(nquestion, typequestion, id);
		
	}

	@Override
	public void metajourdq(int ndques, long id) {
		clientrepository.metajourdq(ndques, id);
		
	}

	@Override
	public int couclient(Shop shop) {
		
		return clientrepository.couclient(shop);
	}

	@Override
	public Page<Client> findByshopc(Shop shop, int page, int size) {
		// TODO Auto-generated method stub
		return clientrepository.findByshopc(shop, new PageRequest(page, size));
	}

	@Override
	public List<Client> findByshopc(Shop shop) {
		
		return clientrepository.findByshopc(shop);
	}

	@Override
	public Page<Client> chercher(String mc, Shop shop, int page, int size) {
		
		return  clientrepository.chercher("%"+mc+"%", shop, new PageRequest(page, size));
	}

	@Override
	public void metajour(Date date,int ndefois,int nquestion, String typequestion, long id) {
		clientrepository.metajour(date,ndefois,nquestion, typequestion, id);
		
	}

	@Override
	public void Stopclient(long id) {
		clientrepository.delete(id);
		
	}

	@Override
	public List<Client> findbyshopgroup(Shop shopc) {
		// TODO Auto-generated method stub
		return clientrepository.findbyshopgroup(shopc);
	}

	@Override
	public Client findbygreeting(String fromn, String ton, Shop shopc) {
		// TODO Auto-generated method stub
		return clientrepository.findbygreeting(fromn, ton, shopc);
	}

	@Override
	public List<Client> FindAll() {
		// TODO Auto-generated method stub
		return clientrepository.findAll();
	}

}
