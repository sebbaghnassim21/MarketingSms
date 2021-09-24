package org.marketingsms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.marketingsms.model.Client;
import org.marketingsms.model.Messagee;
import org.marketingsms.model.Shop;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.Servicemessagee;
import org.marketingsms.service.ShopServiceinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

@Controller
public class Envoitext {

	@Autowired
	private ShopServiceinter shopservice;
	@Autowired
	private Serviceclient serviceclient;

	@Autowired
	private Servicemessagee servicemessage;

	public void sendtext(String mess, String ACCOUNT_SID, String AUTH_TOKEN, String FROM_PHONE_NUMBER,
			String TO_PHONE) {
		try {
			TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

			// Build a filter for the MessageList
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Body", mess));

			params.add(new BasicNameValuePair("To", TO_PHONE)); // Add real number here
			params.add(new BasicNameValuePair("From", FROM_PHONE_NUMBER));

			MessageFactory messageFactory = client.getAccount().getMessageFactory();
			Message message = messageFactory.create(params);
			System.out.println(message.getSid());
		} catch (TwilioRestException e) {
			System.out.println(e.getErrorMessage());
		}

	}

	@RequestMapping(value = "messbrod", method = RequestMethod.POST)
	public ModelAndView messbrod(ModelAndView modelAndView, Model modelmessage, int id) {
		Shop shop = shopservice.findBycodemag(id);
		Messagee message = new Messagee();

		modelmessage.addAttribute("message", message);
		modelmessage.addAttribute("idme", id);

		modelAndView.setViewName("messbrod");
		return modelAndView;
	}

	@RequestMapping(value = "envoiemessge", method = RequestMethod.POST)
	public ModelAndView envoiemessge(ModelAndView modelAndView, Model modelmessage, Messagee mess, int idme) {
		Shop shop = shopservice.findBycodemag(idme);

		String From = shop.getTwilio();

		String ACCOUNT = shop.getAccountsid();
		String TOKEN = shop.getAuthtoken();

		mess.setShopmess(shop);
		servicemessage.save(mess);

		List<Client> client = serviceclient.findByshopc(shop);

		for (int i = 0; i < client.size(); i++) {
			Client current = client.get(i);
			sendtext(mess.getText(), ACCOUNT, TOKEN, From, current.getFromn());
		}

		modelAndView.setViewName("messbrod");
		return modelAndView;

	}

	@PostMapping(value = "envoiyerone")
	public String envoiyerone(HttpServletRequest request, Model modelmessage, Messagee mess, int idme) {
		Shop shop = shopservice.findBycodemag(idme);

		String From = shop.getTwilio();

		String ACCOUNT = shop.getAccountsid();
		String TOKEN = shop.getAuthtoken();

		mess.setShopmess(shop);
		servicemessage.save(mess);

		/*
		 * List<Client> client=serviceclient.findByshopc(shop);
		 * 
		 * envoyer for(int i=0; i<client.size(); i++) { Client current = client.get(i);
		 * sendtext(mess.getText(), ACCOUNT,TOKEN,From,current.getFromn()); }
		 */

		if (request.getParameterValues("envoyer") != null) {

			List<Client> client = serviceclient.findByshopc(shop);

			for (int i = 0; i < client.size(); i++) {
				Client current = client.get(i);
				sendtext(mess.getText(), ACCOUNT, TOKEN, mess.getCompany(), current.getFromn());

			}

			/* if (!request.getParameterValues("envoyer").toString().equals("true")) */

		} else {

			if (request.getParameterValues("fromn") != null) {
				for (String idrateStr : request.getParameterValues("fromn")) {
					// int idrate = Integer.parseInt(idrateStr);
					// rateRepository.deleteRate(idrate);
					sendtext(mess.getText(), ACCOUNT, TOKEN, mess.getCompany(), idrateStr);
				}
			}

		}
		/* } */

		return "redirect:/contact?id=" + idme;

	}

}
