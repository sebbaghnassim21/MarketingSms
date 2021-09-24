package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.springframework.data.repository.query.Param;


public interface ShopService {
 /*   public List<Shop> findByuser(User iduser);*/
	public List<Shop> findByuser(User user,String typepiece);
	public List<Shop> findByusertacher(User user);
    public Shop Save(Shop shop);
    public Shop delete(int id);
    public Shop findBycodemag(int idus);
    public Shop findBytwilio(String twilio);
    public void metajournquestion(int nquestion,int codemag);
    Shop findByidshop(int codemag);
    
/*    public Page<Shop> pagefindByuser(User iduser);*/
   
    

}
