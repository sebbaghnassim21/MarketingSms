package org.marketingsms.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.marketingsms.model.Magasin;
import org.marketingsms.model.Shop;
import org.marketingsms.model.User;
import org.marketingsms.service.MagServiceInter;
import org.marketingsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class Magasincont {

	private UserService userService ;

	
	private MagServiceInter magservices;
	
	
	
	@Autowired
	public Magasincont(UserService userService,MagServiceInter magservices) {
		
		this.userService = userService;
		this.magservices=magservices;
	}

	 @RequestMapping(value="/magasin", method = RequestMethod.GET)
	 public ModelAndView magasin(ModelAndView modelAndView,Model modelmagasin ,String email,Principal principal){
		
		 User couruser=userService.findByEmail(principal.getName());
		   List<Magasin> listmagasin=magservices.findByusermag(couruser);
		
		   modelmagasin.addAttribute("listmagasin",listmagasin);
		 
		   modelAndView.setViewName("listemagasin");
		 return modelAndView;
		 
		 
	 }
	 
	 
	 @RequestMapping(value="/noumag", method = RequestMethod.GET)
	 public ModelAndView payment(ModelAndView modelAndView,Model modelmagasin){
		
		 
		 
		   modelAndView.setViewName("noumag");
		 return modelAndView;
		 
		 
	 }

	 @RequestMapping(value="savenoumag", method = RequestMethod.POST)
		public String saveshopmag(Model savenoumag,Magasin magasin,String email  ){
		  
		/* if (bindingResult.hasErrors()) { 
			
			 return "redirect:/magasin";	
			} else {
		 */
		 User couruser=userService.findByEmail(email);
		    magasin.setForfait("Gratuit");
		    magasin.setUsermag(couruser);
		 
		    magservices.Save(magasin);
			return "redirect:/magasin";
		   /* modelAndView.setViewName("listemagasin");*/
			
			
		 
	 }
	 
	 @RequestMapping(value="/editmagsin", method = RequestMethod.GET)
		public ModelAndView editshopmagsin(ModelAndView modelAndView,Model modelmoumag ,int id){

	        Magasin magasin= magservices.findBycodemagasin(id);
		
	        modelmoumag.addAttribute("mmagasin",magasin);
		    modelAndView.setViewName("moumag");
			return modelAndView;
			
			}
	 @RequestMapping(value="deletemag",method = RequestMethod.GET)
	  public String deletemag(int id,String email){

		 magservices.delete(id) ;
		return "redirect:/magasin";

	  }
	
}
