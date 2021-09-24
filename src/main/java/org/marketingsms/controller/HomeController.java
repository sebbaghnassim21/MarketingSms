package org.marketingsms.controller;


import java.security.Principal;

import org.marketingsms.model.User;
import org.marketingsms.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class HomeController {
	
	private UserService userService ;
	
	/**
	 * 
	 * @param userService 
	 */
	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}
/**
 * 
 * @param modelAndView
 * @param model
 * @param principal
 * @return
 */

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView showhome(ModelAndView modelAndView,Model model,Principal principal){
		User couruser=userService.findByEmail(principal.getName());
		String name=couruser.getFirstName();
		String prenom=couruser.getLastName();
		String titre=couruser.getTitre();
		model.addAttribute("name",name);
		model.addAttribute("prenom",prenom);
		model.addAttribute("titre",titre);
		modelAndView.setViewName("home");
	return modelAndView;
	}
	

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView showlogin(ModelAndView modelAndView){
		
	/*	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	     
	      
	      if (name!="anonymousUser"){
	modelAndView.addObject("username", name)	;
	      }else 
	      {
	    	  
	   modelAndView.addObject("username", "")	;  
	      }*/
	modelAndView.setViewName("login");
	return modelAndView;
	}

	
	
	@RequestMapping(value="/")
   public ModelAndView home(ModelAndView modelAndView){    /// String -----ModelAndView
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName(); //get logged in username
	     
	      
	      if (name!="anonymousUser"){
	    	  modelAndView.setViewName("home");
	    	
	    	modelAndView.setViewName("redirect:/index"); 
	      }else 
	      {
	    	  
	    	  modelAndView.setViewName("index"); 
	      }
		
		
		
	     // return "redirect:/index";
		
	
		return modelAndView;
	}
	
	//  @RequestMapping("/")                      ///// reste controller
//	  void handleFoo(HttpServletResponse response) throws IOException {
//	    response.sendRedirect("/index");
//	  }
	
	@RequestMapping(value="/login?logout")
	public String logout(){
		
	return "redirect:/login";
	}
	
	@RequestMapping(value="/styles")
	public String styles(){
		
	return "styles";
	}
	

}
