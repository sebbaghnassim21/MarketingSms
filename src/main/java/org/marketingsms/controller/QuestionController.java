package org.marketingsms.controller;

import java.util.List;

import org.marketingsms.model.Client;
import org.marketingsms.model.Journal;
import org.marketingsms.model.Messagee;
import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.marketingsms.service.QuestionService;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.Servicejournal;
import org.marketingsms.service.ShopServiceinter;
import org.marketingsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionservice;
	@Autowired
	private ShopServiceinter shopservice;
	@Autowired
	private Servicejournal servicejournal;
	
	@Autowired
	private Serviceclient serviceclient;
	
	private UserService userService ;
	
	private static int code;
    
	
	@RequestMapping(value="listequestion", method = RequestMethod.GET)
	public ModelAndView listequestion(ModelAndView modelAndView,Model model , int id,String type){
		
		Shop shop=shopservice.findBycodemag(id);
		List<Question> question=questionservice.findByshop(shop);
		model.addAttribute("question", question);
		model.addAttribute("id", id);
		model.addAttribute("type",type);
setCode(id);
		
        modelAndView.setViewName("listequestion");
		return modelAndView;
		
	}
	
	@RequestMapping(value="newquestion",method = RequestMethod.POST)
	public ModelAndView question(ModelAndView modelAndView,Model model,int id,String type){
		
		Question question =new Question();
		model.addAttribute("question", question);
      model.addAttribute("idmag", id);
      model.addAttribute("type", type);
       modelAndView.setViewName("newquestion");
		return modelAndView;
 
	}
	
	@RequestMapping(value="questionoui",method = RequestMethod.POST)
	public String questionoui(Model model,int id,String type){
		Question question =new Question();
		model.addAttribute("question", question);
		model.addAttribute("idmag", id);
		model.addAttribute("type", type);
		
		return "questionoui";
 
	}
	
	@RequestMapping(value="message",method = RequestMethod.POST)
	public String message(Model model,int id,String type){
		Question question =new Question();
		model.addAttribute("question", question);
		model.addAttribute("idmag", id);
		model.addAttribute("type", type);
		
		return "message";
 
	}
	
	
	@RequestMapping(value="savequestion",method = RequestMethod.POST)
	public ModelAndView savequestion(ModelAndView modelAndView,Model model,Question ques,String type,String t,int idmag){
		Shop shop=shopservice.findBycodemag(idmag);
		int n = shop.getNquestion();
		int newn=n+1;
		shopservice.metajournquestion(newn, idmag);
		long b =ques.getId();
		ques.setId(b);
		ques.setShop(shop);
		ques.setType(type);
		ques.setNques(newn);
	
	
		questionservice.Save(ques);
		modelAndView.setViewName("redirect:/listequestion?id="+code+"&type="+t);
		return modelAndView;
		
		
	}
	
	@RequestMapping(value="savequestionedit",method = RequestMethod.POST)
	public String savequestionedit(Model model,Question ques,String type,int idm,String t){
		Shop shop=shopservice.findBycodemag(idm);
		ques.setShop(shop);
		ques.setType(type);
	
	 
		questionservice.Save(ques);
		
		return "redirect:/listequestion?id="+code+"&type="+t;
		
		
	}
	

	

	 @RequestMapping(value="deleteq",method = RequestMethod.GET)
	  public String deleteq(long id){
		Question question =questionservice.findByid(id);
		 Shop shop=question.getShop();
		 
		 int n = shop.getNquestion();
			int newn=n-1;
			shopservice.metajournquestion(newn,shop.getCodemag());
		
		 questionservice.delete(id);
		  
		return "redirect:/listequestion?id="+code;
		  
		  
	  }

	 @RequestMapping(value="editquestion", method = RequestMethod.GET)
		public String editquestion(Model modeleque ,String type,int id,String t,int idm ){
		    
		
		 /*   Shop shopmag=shopservice.findBycodemag(id); 
		    modeledit.addAttribute("shopm",shopmag);*/
			Question question =questionservice.findByid(id);
		
		    modeleque.addAttribute("question", question);
		    modeleque.addAttribute("idm", idm);
		    modeleque.addAttribute("t", t);
			return type;
			
			}
	 @RequestMapping(value="journalquestion", method = RequestMethod.GET)
	 public ModelAndView journalquestion(ModelAndView modelAndView,Model modeljournal,long id,
			 @RequestParam(name="page",defaultValue="0") int p,
			 @RequestParam(name="size",defaultValue="10") int s,
			 @RequestParam(name="mc",defaultValue="")String mc){
			 
	/*	 Question question=questionservice.findByid(id);
		
		 if ("questionanswer".equals(question.getType())){
		Page<Journal> jour=servicejournal.recherche(id,"%"+"1"+"%", p, s); 
		String type=question.getType();
		modeljournal.addAttribute("type", type);
		
		int yes=jour.getNumber();
		 modeljournal.addAttribute("yes", yes);
		 Page<Journal> journo=servicejournal.recherche(id,"%"+"2"+"%", p, s); 
		 int no=journo.getSize();
		 modeljournal.addAttribute("no", no);
		 }*/
		 Question question =questionservice.findByid(id);
		 
		 int countclient =serviceclient.couclient(question.getShop());
		 int count =servicejournal.countbyid(id, "%"+mc+"%");
		 Page<Journal> journal=servicejournal.recherche(id,"%"+mc+"%", p, s);
		 modeljournal.addAttribute("journal", journal.getContent());
		 int[] pages=new int[journal.getTotalPages()];
		 modeljournal.addAttribute("pages", pages);
		 modeljournal.addAttribute("idq", id);
		 modeljournal.addAttribute("pagecourant", p);
		 modeljournal.addAttribute("size", s);
		 modeljournal.addAttribute("motcle", mc);
		 modeljournal.addAttribute("count", count);
		 modeljournal.addAttribute("countclient", countclient);
		
		 modelAndView.setViewName("journalpage");
		return modelAndView;
		 
		 
	 }
	 
	 
	 @RequestMapping(value="contact", method = RequestMethod.GET)
	 public ModelAndView contact(ModelAndView modelAndView,Model modelcontact,int id,
			 @RequestParam(name="mc",defaultValue="")String mc,
			 @RequestParam(name="page",defaultValue="0") int p,
			 @RequestParam(name="size",defaultValue="1000000000") int s){
	    Shop shop=shopservice.findBycodemag(id);
	   Page<Client> client =serviceclient.chercher(mc, shop, p, s);
	   modelcontact.addAttribute("contact", client);

	   int[] pages=new int[client.getTotalPages()];
	   modelcontact.addAttribute("pages", pages);
	   modelcontact.addAttribute("idq", id);
	   modelcontact.addAttribute("pagecourant", p);
	   modelcontact.addAttribute("size", s);
	   modelcontact.addAttribute("mc", mc) ;
	   
		Messagee message =new Messagee();
		
		modelcontact.addAttribute("message", message);
		modelcontact.addAttribute("idme", id);
	   
	   modelAndView.setViewName("contact");
		return modelAndView;
		 
		 
	 }

	 
	
	 
	 
	 
	 

	public int getCode() {
		return code;
	}

	public static  void setCode(int code) {
		QuestionController.code=code;
	}

	

}
