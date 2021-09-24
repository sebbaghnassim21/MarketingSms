package org.marketingsms.controller;

import org.marketingsms.model.User;
import org.marketingsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ProfileController {
	
	private UserService userService ;
	
	
	
	@Autowired
	public ProfileController(UserService userService) {
		
		this.userService = userService;
	}
	

@RequestMapping(name="/accompt",method = RequestMethod.GET)
	public String edit(Model model,String mail){
		
		User profile=userService.findByEmail(mail);
		if (profile!=null){
		model.addAttribute("p", profile);
		}
		else{
			model.addAttribute("p", new User());
			}
		return "accompt";
		
		
	}
	
	@RequestMapping(name="/saveuser",method = RequestMethod.POST)
	public String saveuser(Model model,User user,String mail){
		
		User profile=userService.findByEmail(mail);
		user.setEmail(profile.getEmail());
		user.setId(profile.getId());
		user.setConfirmationToken(profile.getConfirmationToken());
		user.setEnabled(true);
		user.setPassword(profile.getPassword());
		user.setRole("USER");
		userService.saveUser(user);
		
		return "home";
		
		
	}
	

	
}
