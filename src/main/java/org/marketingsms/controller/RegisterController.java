package org.marketingsms.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.marketingsms.model.User;
import org.marketingsms.service.EmailService;
import org.marketingsms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
	private EmailService emailService;

	@Autowired
	public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService,
			EmailService emailService) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userService = userService;
		this.emailService = emailService;
	}

	// Return registration form template
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request) {

		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());

		System.out.println(userExists);

		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage",
					"Il y a déjà un utilisateur enregistré avec l'email fourni.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else { // new user so we create user and send confirmation e-mail

			// Disable user until they click on confirmation link in email
			user.setEnabled(false);

			// role
			user.setRole("USER");
			// Generate random 36-character string token for confirmation link
			user.setConfirmationToken(UUID.randomUUID().toString());

			userService.saveUser(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("confirmation d'enregistrement");
			registrationEmail.setText("Pour confirmer votre adresse e-mail, cliquez sur le lien ci-dessous:\n" + appUrl
					+ "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("sebbaghnassim@gmail.com");

			emailService.sendEmail(registrationEmail);

			modelAndView.addObject("confirmationMessage",
					"Un e-mail de confirmation a été envoyé à " + user.getEmail());
			modelAndView.setViewName("confimess");
		}

		return modelAndView;
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {

		User user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", " Ceci est un lien de confirmation invalide.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}

		modelAndView.setViewName("confirm");
		return modelAndView;
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

		modelAndView.setViewName("confirm");

		Zxcvbn passwordCheck = new Zxcvbn();

		Strength strength = passwordCheck.measure(requestParams.get("password"));

		if (strength.getScore() < 3) {
			// modelAndView.addObject("errorMessage", "Your password is too weak. Choose a
			// stronger one.");
			bindingResult.reject("password");

			redir.addFlashAttribute("errorMessage", "Votre mot de passe est trop faible. Choisissez un plus fort.");

			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			System.out.println(requestParams.get("token"));
			return modelAndView;
		}

		// Find the user associated with the reset token
		User user = userService.findByConfirmationToken(requestParams.get("token"));

		// Set new password
		user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

		// Set user to enabled
		user.setEnabled(true);

		// Save user
		userService.saveUser(user);

		modelAndView.addObject("successMessage", "Votre mot de passe a été défini!");
		// modelAndView.addObject("mail", user.getEmail());
		// modelAndView.addObject("password",bCryptPasswordEncoder.encode((requestParams.get("password"))
		// ));
//	modelAndView.setViewName("login");
		return modelAndView;
	}

	// Return registration form template
	@RequestMapping(value = "/compteoubliee", method = RequestMethod.GET)
	public ModelAndView compteoublieePage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("compteoubliee");
		return modelAndView;
	}

	@RequestMapping(value = "/compteoubliee", method = RequestMethod.POST)
	public ModelAndView processcompteoubliee(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult,
			HttpServletRequest request) {

		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());

		System.out.println(userExists);

		if (userExists != null) {

			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Réinitialiser votre mot de passe");
			registrationEmail.setText("Pour Réinitialiser votre mot de passe, cliquez sur le lien ci-dessous:\n"
					+ appUrl + "/confirm?token=" + userExists.getConfirmationToken());
			registrationEmail.setFrom("sebbaghnassim@gmail.com");

			emailService.sendEmail(registrationEmail);

			modelAndView.addObject("confirmationMessage", "Un e-mail a été envoyé à " + user.getEmail());
			modelAndView.setViewName("confimess");

		}

		if (userExists == null) {
			modelAndView.addObject("alreadyRegisteredMessage",
					"Votre recherche ne donne aucun résultat. Veuillez réessayer avec d’autres termes..");
			modelAndView.setViewName("compteoubliee");
			bindingResult.reject("email");
		}
		return modelAndView;

	}
}