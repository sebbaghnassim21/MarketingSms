package org.marketingsms.service;

import java.util.Date;
import java.util.List;

import org.marketingsms.model.Client;
import org.marketingsms.model.Shop;
import org.springframework.data.domain.Page;



public interface Interclient {
	public Page<Client> findByshopc(Shop shop,int page,int size);
	public List<Client> findByshopc(Shop shop);
	public Client save(Client client);
	public Client findByid(long id);
	public List<Client> FindAll();
	public Client findByfromn(String fromn);
	public Client findbyre(String fromn,String ton);
	public Client findbygreeting(String fromn,String ton,Shop shopc);
	public void metajourclient (int nquestion,String typequestion,long id);
	public void metajour (Date date,int ndefois,int nquestion,String typequestion,long id);
	public void metajourdq (int ndques ,long id);
	int couclient(Shop shop);
	public Page<Client> chercher(String mc,Shop shop,int page,int size);
	public void Stopclient(long id);
	public List<Client> findbyshopgroup(Shop shopc);
	
}
