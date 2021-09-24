package org.marketingsms.service;

import java.util.List;

import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.marketingsms.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("shopService")
public class ShopServiceinter implements ShopService {
 @Autowired
private ShopRepository shoprepository;
	


/*	@Override
	public List<Shop> findByuser(User iduser) {
		// TODO Auto-generated method stub
		return shoprepository.findByuser(iduser);
	}*/

/*	@Override
	public Page<Shop> pagefindByuser(User iduser) {
		// TODO Auto-generated method stub
		return shoprepository.pagefindByuser(iduser);
	}*/

	@Override
	public Shop Save(Shop shop) {
		// TODO Auto-generated method stub
		return shoprepository.save(shop);
	}

	@Override
	public Shop delete(int id) {
		// TODO Auto-generated method stub
		return shoprepository.delete(id);
	}

	@Override
	public Shop findBycodemag(int idus) {
		// TODO Auto-generated method stub
		return shoprepository.findBycodemag(idus);
	}

	@Override
	public void metajournquestion(int nquestion, int codemag) {
		shoprepository.metajournquestion(nquestion, codemag);
	
	}

	@Override
	public Shop findBytwilio(String twilio) {
		
		return shoprepository.findBytwilio(twilio);
	}

	@Override
	public List<Shop> findByuser(User user, String typepiece) {
		// TODO Auto-generated method stub
		return shoprepository.findByuser(user, typepiece);
	}

	@Override
	public Shop findByidshop(int codemag) {
		// TODO Auto-generated method stub
		return shoprepository.findByidshop(codemag);
	}

	@Override
	public List<Shop> findByusertacher(User user) {
		// TODO Auto-generated method stub
		return shoprepository.findByusertacher(user);
	}

	

	



	

}
