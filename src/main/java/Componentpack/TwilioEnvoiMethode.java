package Componentpack;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioEnvoiMethode {

	

	public void sendtext(String mess,String ACCOUNT_SID,String AUTH_TOKEN,String FROM_PHONE_NUMBER,String TO_PHONE){
		 try {
			 TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		
		        // Build a filter for the MessageList
		        List<NameValuePair> params = new ArrayList<NameValuePair>();
		        params.add(new BasicNameValuePair("Body", mess));


		        params.add(new BasicNameValuePair("To", TO_PHONE)); //Add real number here
		        params.add(new BasicNameValuePair("From", FROM_PHONE_NUMBER));
		 
		        MessageFactory messageFactory = client.getAccount().getMessageFactory();
		        Message message = messageFactory.create(params);
		        System.out.println(message.getSid());
		    } 
		    catch (TwilioRestException e) {
		        System.out.println(e.getErrorMessage());
		    }
		   
	 }
}
