package org.marketingsms.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.marketingsms.model.Client;
import org.marketingsms.model.Journal;
import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.marketingsms.model.Tacher;
import org.marketingsms.model.User;
import org.marketingsms.service.QuestionService;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.Servicejournal;
import org.marketingsms.service.ShopServiceinter;
import org.marketingsms.service.TacherService;
import org.marketingsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MacCont {
	
private UserService userService ;

private ShopServiceinter shopservice;
@Autowired
private Serviceclient clientservice;

@Autowired
private QuestionService questionservice;

@Autowired
private Servicejournal servicejournal;


@Autowired
private TacherService tacherservice;
 
	@Autowired
	public MacCont(UserService userService,ShopServiceinter shopservice) {
		
		this.userService = userService;
		this.shopservice = shopservice;
		
	}

	 @RequestMapping(value="shopmag", method = RequestMethod.GET)
		public String ajoutershopmag(Model modelmag ,String email){
		   /* User couruser=userService.findByEmail(email);
		    Shop shopmag=shopservice.findByuser(couruser);   
		    /// find by mail 
		    */
		
		   Shop shoptable=new Shop();
		    modelmag.addAttribute("shop",shoptable);
			return "shopmag";
			
			}
	 
	 @RequestMapping(value="/editmag", method = RequestMethod.GET)
		public ModelAndView editshopmag(ModelAndView modelAndView,Model modeledit ,int id){
		    /*User couruser=userService.findByEmail(principal.getName());*/
		    Shop shopmag=shopservice.findBycodemag(id); 
		    /// find by mail 
	
		
		    modeledit.addAttribute("shopm",shopmag);
		    modelAndView.setViewName("editmag");
			return modelAndView;
			
			}
	 
	 
	 @RequestMapping(value="savemag", method = RequestMethod.POST)
		public String saveshopmag(Model savemag,Shop shop,String email ){
		    User couruser=userService.findByEmail(email);
		   /* Shop shopmag=shopservice.findByuser(couruser);   
		    /// find by mail 
		    */
		   
		/*    savemag.addAttribute("shop",shoptable);*/
		    
		/*    Shop shoptabl=new Shop();
		    shoptabl.setNommag(shop.getNommag());
		    shoptabl.setCodesms(shop.getCodesms());
		    shoptabl.setTwilio(shop.getTwilio());
		    shoptabl.setAccountsid(shop.getAccountsid());
		    shoptabl.setAuthtoken(shop.getAuthtoken());*/
		    shop.setUser(couruser);
		    shop.setTypepiece("Coupon");
		    shopservice.Save(shop);
			return "redirect:/listemag";
			
			}
	 
	 @RequestMapping(value="/listemag", method = RequestMethod.GET)
	 public ModelAndView listemag(ModelAndView modelAndView,Model modellist ,String email,Principal principal){
		
		 User couruser=userService.findByEmail(principal.getName());
		   List<Shop> listshopmag=shopservice.findByuser(couruser,"Coupon");
		
		   modellist.addAttribute("listshop",listshopmag);
		 
		   modelAndView.setViewName("listemag");
		 return modelAndView;
		 
		 
		 
	 }
	 @RequestMapping(value="delete",method = RequestMethod.GET)
	  public String delete(int id,String email){
	try{
		 shopservice.delete(id) ;
	}
	catch (Exception e) {
		return "redirect:/listemag?erreur=verifier ";
	}
		
		return "redirect:/listemag";
		  
		  
	  }
	///////////////////////////////////////////////////////////
	 /////////////////////////////////////////////////////////
	 //////////////////////////////////////////////////////
	 /////////////////////////////////////////////////////
	//////////////////////////////////////////////////campagne
	 
	 
	 @RequestMapping(value="/campagne", method = RequestMethod.GET)
	 public ModelAndView campagne(ModelAndView modelAndView,Model modellist ,Principal principal){
		
		 User couruser=userService.findByEmail(principal.getName());
		   List<Shop> listshopmag=shopservice.findByuser(couruser,"Campagne");
		
		   modellist.addAttribute("listshop",listshopmag);
		 
		   modelAndView.setViewName("campagne");
		 return modelAndView;
		 
		 
		 
	 }
	 
	 
	 @RequestMapping(value="shopcamp", method = RequestMethod.GET)
		public String ajoutershopcamp(Model modelmag ,String email){
		   /* User couruser=userService.findByEmail(email);
		    Shop shopmag=shopservice.findByuser(couruser);   
		    /// find by mail 
		    */
		
		   Shop shoptable=new Shop();
		    modelmag.addAttribute("shop",shoptable);
			return "shopcamp";
			
			}
	 
	 @RequestMapping(value="savecamp", method = RequestMethod.POST)
		public String saveshopcamp(Model savemag,Shop shop,String email ){
		    User couruser=userService.findByEmail(email);
		
		    shop.setUser(couruser);
		    shop.setTypepiece("Campagne");
		    shopservice.Save(shop);
			return "redirect:/campagne";
			
			}
	
	 @RequestMapping(value="suivant", method = RequestMethod.POST)
		public ModelAndView suivant(Model savemag,ModelAndView modelAndView,Model model,Shop shop,String email,Principal principal ){
		 
		 User couruser=userService.findByEmail(principal.getName());
		
		    shop.setUser(couruser);
		    shop.setTypepiece("Campagne");
		    shopservice.Save(shop);
		    QuestionController.setCode(shop.getCodemag());
	
			
			List<Question> question=questionservice.findByshop(shop);
			model.addAttribute("question", question);
			model.addAttribute("id", shop.getCodemag());
			model.addAttribute("type","1");
			
	
			
	        modelAndView.setViewName("listequestion");
			
	        return modelAndView;
		    
		    
		    
			
			} 
	 
	 
	 @RequestMapping(value="filtre", method = RequestMethod.POST)
		public ModelAndView filtre(ModelAndView modelAndView,Model model,int type,String email,long id ){
		  
		 if (type == 1){
		 User couruser=userService.findByEmail(email);
		    List<Shop> listshopmag=shopservice.findByusertacher(couruser);
	
		
	
			
			model.addAttribute("shop", listshopmag);
			model.addAttribute("idcamp", id);
	
			
	       modelAndView.setViewName("filtre"); 
	        
		 }else{
			modelAndView.setViewName("redirect:/listemag?email="+email);  
		 }
		 
		 return modelAndView;
	 }
	 
	 
	 
	 @RequestMapping(value="ajouterdejournal", method = RequestMethod.GET)
	 public ModelAndView ajouterdejournal(ModelAndView modelAndView,Model model,int id,
			@RequestParam(name="code",defaultValue="")int code,Principal principal,String mc){
	if(mc==""){
		
		List<Journal> journal =servicejournal.findByIdquestionlist(code);
		 Shop shopcamp= shopservice.findBycodemag(id)	;
		 
		 if (journal!=null){	 
			 for(int i=0; i<journal.size(); i++) {
				 Journal current = journal.get(i);
				 
			   	 ///////////////////////ajouter client //////////////////
				    Client ajouclient=new Client();
						 ajouclient.setFromn(current.getFromnbr());
						 ajouclient.setTon(shopcamp.getTwilio());
						 ajouclient.setNquestion(0);
						 ajouclient.setRedirect(false);
						 ajouclient.setNreponse(0);
						 ajouclient.setNdefois(0);
						 ajouclient.setShopc(shopcamp);
						 ajouclient.setDate(new Date());
						 
						 clientservice.save(ajouclient);
						 ///////////////////////////////////////
				 
				 
			 }
			  model.addAttribute("idcamp", id);
			  modelAndView.setViewName("succesfiltre");    
		 }	
		
		
	} // mc == null
	
	if(mc!=""){
		 List<Journal> journal =servicejournal.listrecherche(code, mc);
		 
		 Shop shopcamp= shopservice.findBycodemag(id)	;
		 
	 if (journal!=null){	 
		 for(int i=0; i<journal.size(); i++) {
			 Journal current = journal.get(i);
			 
		   	 ///////////////////////ajouter client //////////////////
			    Client ajouclient=new Client();
					 ajouclient.setFromn(current.getFromnbr());
					 ajouclient.setTon(shopcamp.getTwilio());
					 ajouclient.setNquestion(0);
					 ajouclient.setRedirect(false);
					 ajouclient.setNreponse(0);
					 ajouclient.setNdefois(0);
					 ajouclient.setShopc(shopcamp);
					 ajouclient.setDate(new Date());
					 
					 clientservice.save(ajouclient);
					 ///////////////////////////////////////
			 
			 
		 }
		  model.addAttribute("idcamp", id);
		  modelAndView.setViewName("succesfiltre");    
	 }
	}// mc!= null
		    return modelAndView;
		 }
	 
	 
	 
	 
	 @RequestMapping(value="questionfiltre", method = RequestMethod.GET)
	 public ModelAndView questionfiltre(ModelAndView modelAndView,Model model,@RequestParam String action,int id,
			@RequestParam(name="code",defaultValue="")int code,Principal principal){
	
		 Shop shopcamp= shopservice.findBycodemag(id)	;
		 
		    if( action.equals("filtr") ){
		        //handle save
		    
		Shop shop =	shopservice.findBycodemag(code)	;
		 List<Question> questions =questionservice.findByshop(shop);
		 model.addAttribute("questions", questions);
		 model.addAttribute("shop", shop);
		 model.addAttribute("idcamp", id);
		 modelAndView.setViewName("filtrequestion");
		
		    }
		     else if( action.equals("ajouter") ){
		    	 
		    	 Shop shop =	shopservice.findBycodemag(code)	;
		    	
		    	  List<Client> client = clientservice.findByshopc(shop);
		    	 
		    	
		    	  if (client!=null){	 
		    		 for(int i=0; i<client.size(); i++) {
			    	 Client current = client.get(i);
			    	
			    	 ///////////////////////ajouter client //////////////////
			    Client ajouclient=new Client();
					 ajouclient.setFromn(current.getFromn());
					 ajouclient.setTon(shopcamp.getTwilio());
					 ajouclient.setNquestion(0);
					 ajouclient.setRedirect(false);
					 ajouclient.setNreponse(0);
					 ajouclient.setNdefois(0);
					 ajouclient.setShopc(shopcamp);
					 ajouclient.setDate(new Date());
					 
					 clientservice.save(ajouclient);
					 ///////////////////////////////////////
			    	 
		    	 }
		    	  }
		    	  
		    	  
		    	  
		    	/*  ("filtre"+"?email="+principal.getName()+"&id="+id);*/   
		    	  model.addAttribute("idcamp", id);
 modelAndView.setViewName("succesfiltre");   
		     }
		    	 
		       
		    	 
		    
	 if( action.equals("suivant") ){
		    
    	 Tacher tacher =new Tacher();
    	 
      model.addAttribute("tacherr", tacher);	 
   	  model.addAttribute("idcamp", id);
   	 modelAndView.setViewName("tacher");
	 
	 }
		    return modelAndView;
	 }
	 @RequestMapping(value="savetacher", method = RequestMethod.POST)
	 public ModelAndView savetacher(ModelAndView modelAndView,Model model,int id,Tacher tacher,Principal principal){
		tacher.setCodemag(id);	
		tacher.setCamp("camp");
		tacher.setType("camp");
		tacher.setStatut("Attente");
		 tacherservice.save(tacher);
		 modelAndView.setViewName("succestacher");
		 return modelAndView;
		 
		 
		 
		 
		 
	 }
	
		 
		 
		 
		 
	 }
	 
	 
	 

