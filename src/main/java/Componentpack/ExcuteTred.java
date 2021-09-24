package Componentpack;

import java.util.List;

import org.marketingsms.model.Client;
import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.marketingsms.service.QuestionService;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
public class ExcuteTred {
	
	@Autowired
	private ShopService shopService;
	@Autowired
	private QuestionService questionservice;
	@Autowired
	private Serviceclient serviceclient;
	
	
	public void tacheexe(int a){
		
		
	    
		Shop shop=shopService.findByidshop(a);
		Question question=questionservice.findbyshopques(shop, 1);
		List<Client> client=serviceclient.findByshopc(shop);
		
		///   
		if (client!=null){	
			 for(int i=0; i<client.size(); i++) {
				 
				 Client  currentst=client.get(i);
				 
		TwilioEnvoiMethode envoyermethode=new TwilioEnvoiMethode();
		envoyermethode.sendtext(question.getText(), shop.getAccountsid(), shop.getAuthtoken(), shop.getTwilio(), currentst.getFromn());
		
		
		
		
			 }// fin for 
			 
		}// 
	}

}
