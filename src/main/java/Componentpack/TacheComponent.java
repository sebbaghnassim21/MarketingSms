package Componentpack;

import java.util.List;

import org.marketingsms.model.Client;



public class TacheComponent implements Runnable {
	
	public String question;
	public String Tocken;
	public String Accu;
	public String twilio;
    public List client;


	







	public String getQuestion() {
		return question;
	}










	public void setQuestion(String question) {
		this.question = question;
	}










	public String getTocken() {
		return Tocken;
	}










	public void setTocken(String tocken) {
		Tocken = tocken;
	}










	public String getAccu() {
		return Accu;
	}










	public void setAccu(String accu) {
		Accu = accu;
	}










	public String getTwilio() {
		return twilio;
	}










	public void setTwilio(String twilio) {
		this.twilio = twilio;
	}










	public List getClient() {
		return client;
	}










	public void setClient(List client) {
		this.client = client;
	}










	@Override
	public void run() {
		
		 
		if (client!=null){	
			 for(int b=0; b<client.size(); b++) {
				 
				 String  current= (String) client.get(b);
				 System.out.println("Tacher: "+current);
		TwilioEnvoiMethode envoyermethode=new TwilioEnvoiMethode();
		envoyermethode.sendtext(question,Accu, Tocken, twilio, current);
		
	
		
			 }// fin for 
			 
		}//
	
		
			
	}
	


	

}
