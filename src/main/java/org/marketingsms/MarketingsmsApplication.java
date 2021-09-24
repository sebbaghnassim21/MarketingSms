package org.marketingsms;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.marketingsms.model.Client;
import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.marketingsms.model.Tacher;
import org.marketingsms.service.QuestionService;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.ShopService;
import org.marketingsms.service.TacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import Componentpack.TacheComponent;







@SpringBootApplication

@EnableScheduling
public class MarketingsmsApplication {

	@Autowired
	private TacherService tacherservice;
	@Autowired
	private ShopService shopService;
	@Autowired
	private QuestionService questionservice;
	@Autowired
	private Serviceclient serviceclient;
	
	public static void main(String[] args) {
		SpringApplication.run(MarketingsmsApplication.class, args);
		
		
	}
	

	
	@Scheduled(fixedRate = 19000)
    public void tesk() {
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat tf =new SimpleDateFormat("HH:mm");
		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);
		String repo = tf.format(today);
		System.out.println("Report Date: " + reportDate);
          
		 List<Tacher> tacher=  tacherservice.findBydatetime(today, repo);
		
		 if (tacher!=null){	 
    		 for(int i=0; i<tacher.size(); i++) {
    			 Tacher current = tacher.get(i);
    			 System.out.println("Tacher: " + current.getId()+" Statut="+current.getStatut()); 
    		     tacherservice.metajourtacher(current.getId());
    		     System.out.println("Tacher: " + current.getId()+" Statut="+current.getStatut());
    		 }  /////      fermeteur de for 
		 }//fermeteur  de if
	}
	
	@Scheduled(fixedRate = 25000)
	public void excution(){
		
		List<Tacher> tacherstatut=tacherservice.findBystatut();
		
		if (tacherstatut!=null){	
		 for(int i=0; i<tacherstatut.size(); i++) {
			 
		    Tacher  currentst=tacherstatut.get(i);
		   
		    Shop shop=shopService.findByidshop(currentst.getCodemag());
			Question question=questionservice.findbyshopques(shop, 1);
			List<Client> client=serviceclient.findbyshopgroup(shop);	  
			
		 TacheComponent tache=new TacheComponent();
		 
		tache.setQuestion(question.getText());
		tache.setClient(client);
		tache.setAccu(shop.getAccountsid());
		tache.setTocken(shop.getAuthtoken());
		tache.setTwilio(shop.getTwilio());
		
		Thread MonThread = new Thread (tache);  //creation du thread
		MonThread.start (); //Lancement du thread
		System.out.println("Tacher: " + currentst.getId());
		tacherservice.MetajourTacherTerminer(currentst.getId());
		 }          //// fin de for 
		}       ///fin de if (tacherstatut!=null) 
	}
	
}
