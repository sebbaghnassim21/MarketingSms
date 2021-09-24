package org.marketingsms.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.marketingsms.model.Client;
import org.marketingsms.model.Journal;
import org.marketingsms.model.Question;
import org.marketingsms.model.Shop;

import org.marketingsms.service.QuestionService;
import org.marketingsms.service.Serviceclient;
import org.marketingsms.service.Servicejournal;

import org.marketingsms.service.ShopServiceinter;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

@RestController

public class Resttwilio {

	@Autowired
	private QuestionService questionservice;
	@Autowired
	private ShopServiceinter shopservice;
	@Autowired
	private Serviceclient serviceclient;
	@Autowired
	private Servicejournal servicejournal;

	// public static final String ACCOUNT_SID="AC82aa4abf1f1924c032bcca6144e8891a";

	// public static final String AUTH_TOKEN="32fddfd759183c089e84761039bd2e18";

//   public static final String FROM_PHONE_NUMBER = "+18624041052";

	public void sendsms(String mess, String ACCOUNT_SID, String AUTH_TOKEN, String FROM_PHONE_NUMBER, String TO_PHONE) {
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

	@RequestMapping("/greeting")
	public String greeting(HttpServletRequest request, HttpServletResponse response) {
		String Body = request.getParameter("Body");
		String From = request.getParameter("From");
		String To = request.getParameter("To");
		String re = null;
		/* form existe */
		Shop shop = shopservice.findBytwilio(To);
		Client client = serviceclient.findbygreeting(From, To, shop);

		String ACCOUNT = shop.getAccountsid();
		String TOKEN = shop.getAuthtoken();
		try {

			if (Body.toLowerCase().trim().equals("*stop*")) { // si le body == stop
				if (client != null) {
					serviceclient.Stopclient(client.getId()); // Delete client
					re = "Stop";
				}
			} else { //// si le body !== stop

				if (client == null) {
					String codesms = shop.getCodesms().toLowerCase().trim();
					if (Body.toLowerCase().trim().equals(codesms)) {
						int a = 1;
						Question question = questionservice.findbyshopques(shop, a);
						Client ajouclient = new Client();
						ajouclient.setFromn(From);
						ajouclient.setTon(To);
						ajouclient.setNquestion(1);
						ajouclient.setRedirect(false);
						ajouclient.setNreponse(0);
						ajouclient.setNdefois(1);
						ajouclient.setShopc(shop);
						ajouclient.setDate(new Date());
						ajouclient.setTypequestion(question.getType());
						serviceclient.save(ajouclient);

						sendsms(question.getText(), ACCOUNT, TOKEN, To, From); /* question.getText() */
						/* re=question.getText(); */
					}
				} else { //// client not null

					//////////////////////////////////////////// .................
					/////////////////////////////// body = code sms
					String codesms = shop.getCodesms().toLowerCase().trim();

					if (Body.toLowerCase().trim().equals(codesms)) {
						int a = 1;
						int b = client.getNdefois();
						Question question = questionservice.findbyshopques(shop, a);

						sendsms(question.getText(), ACCOUNT, TOKEN, To, From);
						/* re=question.getText(); */

						serviceclient.metajour(new Date(), b + 1, a, question.getType(), client.getId());
					} else { ///////////////// body != code sms

						int nquestion = client.getNquestion();
						String typequestion = client.getTypequestion();

						if (!"questionanswer".equals(typequestion)) {
							int nques;
							if (shop.getNquestion() > nquestion) {
								nques = nquestion + 1;
							} else {

								nques = nquestion;
							}

							//// recuperer n de question avant le dernier
							Question questionavant = questionservice.findbyshopques(shop, nquestion);
							Question question = questionservice.findbyshopques(shop, nques);

							serviceclient.metajourclient(nques, question.getType(), client.getId());
							sendsms(question.getText(), ACCOUNT, TOKEN, To, From);

							/* re=question.getText(); */

							Journal journal = new Journal();
							journal.setDate(new Date());
							journal.setFromnbr(From);
							journal.setNquestion(nquestion);
							journal.setIdquestion(questionavant.getId());
							journal.setQuestion(questionavant.getText());
							journal.setReponse(Body);
							journal.setShopjou(shop);
							journal.setTonbr(To);
							servicejournal.save(journal);

							/* if question = oui et non */
						}

						if ("questionanswer".equals(typequestion)) {

							if (client.isRedirect()) {

								int nques = client.getNquestion();
								Question question = questionservice.findbyshopques(shop, nques);
								int n = client.getNdques();

								// ///////////////////////////////////////////////
								if (n == 1) {
									int ndques = n + 1;
									serviceclient.metajourdq(ndques, client.getId());
									if (!"".equals(question.getQuestionb())) {
										sendsms(question.getQuestionb(), ACCOUNT, TOKEN, To, From);

										/* re=question.getQuestionb(); */

									}
									Journal journal = new Journal();
									journal.setDate(new Date());
									journal.setFromnbr(From);
									journal.setNquestion(nques);
									journal.setQuestion(question.getQuestiona());
									journal.setIdquestion(question.getId());
									journal.setReponse(Body);
									journal.setShopjou(shop);
									journal.setTonbr(To);
									servicejournal.save(journal);

								} //////////////////////////////////////////////////////

								if (n == 2) {
									int ndques = client.getNdques() + 1;
									serviceclient.metajourdq(ndques, client.getId());
									if (!"".equals(question.getQuestionc())) {
										sendsms(question.getQuestionc(), ACCOUNT, TOKEN, To, From);

										/* re=question.getQuestionc(); */
									}
									Journal journal = new Journal();
									journal.setDate(new Date());
									journal.setFromnbr(From);
									journal.setNquestion(nques);
									journal.setQuestion(question.getQuestionb());
									journal.setIdquestion(question.getId());
									journal.setReponse(Body);
									journal.setShopjou(shop);
									journal.setTonbr(To);
									servicejournal.save(journal);

								} //////////////////////////////////////////////////////

								if (n == 3) {
									int ndques = client.getNdques() + 1;
									serviceclient.metajourdq(ndques, client.getId());
									if (!"".equals(question.getQuestiond())) {
										sendsms(question.getQuestiond(), ACCOUNT, TOKEN, To, From);

										/* re=question.getQuestiond(); */
									}
									Journal journal = new Journal();
									journal.setDate(new Date());
									journal.setFromnbr(From);
									journal.setNquestion(nques);
									journal.setQuestion(question.getQuestionc());
									journal.setIdquestion(question.getId());
									journal.setReponse(Body);
									journal.setShopjou(shop);
									journal.setTonbr(To);
									servicejournal.save(journal);

								} //////////////////////////////////////////////////////

								if (n == 4) {
									int ndques = client.getNdques() + 1;
									serviceclient.metajourdq(ndques, client.getId());
									if (!"".equals(question.getQuestione())) {
										sendsms(question.getQuestione(), ACCOUNT, TOKEN, To, From);
										/* re=question.getQuestione(); */
									}
									Journal journal = new Journal();
									journal.setDate(new Date());
									journal.setFromnbr(From);
									journal.setNquestion(nques);
									journal.setQuestion(question.getQuestiond());
									journal.setIdquestion(question.getId());
									journal.setReponse(Body);
									journal.setShopjou(shop);
									journal.setTonbr(To);
									servicejournal.save(journal);

								} //////////////////////////////////////////////////////

							} else {
								if ("1".equals(Body.trim())) {
									int nquest = client.getNquestion();
									int nques = 0;

									if (shop.getNquestion() > nquest) {
										nques = nquest + 1;
									}

									if (nques != 0) {

										Question question = questionservice.findbyshopques(shop, nques);
										serviceclient.metajourclient(nques, question.getType(), client.getId());
										sendsms(question.getText(), ACCOUNT, TOKEN, To, From);
										/* re=question.getText(); */
										Question q = questionservice.findbyshopques(shop, nques - 1);
										Journal journal = new Journal();
										journal.setDate(new Date());
										journal.setFromnbr(From);
										journal.setQuestion(q.getText());
										journal.setIdquestion(q.getId());
										journal.setNquestion(nquestion);
										journal.setReponse(Body);
										journal.setShopjou(shop);
										journal.setTonbr(To);
										servicejournal.save(journal);

									}
									/*
									 * else { //////////////////////////////////retourn a 0 question
									 * 
									 * 
									 * Question question=questionservice.findbyshopques(shop,1);
									 * serviceclient.metajourclient(1,question.getType() ,client.getId());
									 * 
									 * }///////////////////////////////////////////////////////////////////////////
									 */
								}
								if ("2".equals(Body.trim())) {
									int nques = client.getNquestion();
									Question question = questionservice.findbyshopques(shop, nques);
									int ndques = client.getNdques() + 1;
									serviceclient.metajourdq(ndques, client.getId());
									sendsms(question.getQuestiona(), ACCOUNT, TOKEN, To, From);

									/* re=question.getQuestiona(); */

									Journal journal = new Journal();
									journal.setDate(new Date());
									journal.setFromnbr(From);
									journal.setNquestion(nques);
									journal.setQuestion(question.getText());
									journal.setIdquestion(question.getId());
									journal.setReponse(Body);
									journal.setShopjou(shop);
									journal.setTonbr(To);
									servicejournal.save(journal);
								}
								{
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		/* return "greeting"; */
		return "";
	}

	/*
	 * @RequestMapping(value="/sendmessage", method =
	 * RequestMethod.POST,produces=MediaType.APPLICATION_XML_VALUE)
	 * 
	 * public ResponseEntity<Object> sendmessage(){
	 * 
	 * 
	 * Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	 * 
	 * Message message = Message.creator(new PhoneNumber("+213553246869"), new
	 * PhoneNumber(FROM_PHONE_NUMBER), "salut nassim").create();
	 * 
	 * return null;
	 * 
	 * }
	 */

}
