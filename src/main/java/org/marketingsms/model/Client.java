package org.marketingsms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3824382040694543351L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="fromn")
	private String fromn;
	@Column(name="ton")
	private String ton;
	@Column(name="nquestion")
	private int nquestion;
	@Column(name="nreponse")
	private int nreponse;
	@Column(name="redirect")
	private boolean redirect;
	@Column(name="typequestion")
	private String typequestion;
	@Column(name="ndques")
	private int ndques;
    @Column(name="envoyer")
	private int envoyer ;
    @Column(name="stop")
	private int stop;
    @Column(name="ndefois")
  	private int ndefois;
    @Column(name="date")
	private Date date;
    @ManyToOne
	private Shop shopc;
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}


	



















	public Client(long id, String fromn, String ton, int nquestion, int nreponse, boolean redirect, String typequestion,
			int ndques, int envoyer, int stop, int ndefois, Date date, Shop shopc) {
		super();
		this.id = id;
		this.fromn = fromn;
		this.ton = ton;
		this.nquestion = nquestion;
		this.nreponse = nreponse;
		this.redirect = redirect;
		this.typequestion = typequestion;
		this.ndques = ndques;
		this.envoyer = envoyer;
		this.stop = stop;
		this.ndefois = ndefois;
		this.date = date;
		this.shopc = shopc;
	}






















	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}






	public int getNquestion() {
		return nquestion;
	}


	public void setNquestion(int nquestion) {
		this.nquestion = nquestion;
	}


	public int getNreponse() {
		return nreponse;
	}


	public void setNreponse(int nreponse) {
		this.nreponse = nreponse;
	}


	public boolean isRedirect() {
		return redirect;
	}


	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}


	public Shop getShopc() {
		return shopc;
	}


	public void setShopc(Shop shopc) {
		this.shopc = shopc;
	}




	public String getFromn() {
		return fromn;
	}




	public void setFromn(String fromn) {
		this.fromn = fromn;
	}




	public String getTon() {
		return ton;
	}




	public void setTon(String ton) {
		this.ton = ton;
	}




	public String getTypequestion() {
		return typequestion;
	}




	public void setTypequestion(String typequestion) {
		this.typequestion = typequestion;
	}




	public int getNdques() {
		return ndques;
	}




	public void setNdques(int ndques) {
		this.ndques = ndques;
	}









	public int getNdefois() {
		return ndefois;
	}









	public void setNdefois(int ndefois) {
		this.ndefois = ndefois;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getEnvoyer() {
		return envoyer;
	}


	public void setEnvoyer(int envoyer) {
		this.envoyer = envoyer;
	}


	public int getStop() {
		return stop;
	}


	public void setStop(int stop) {
		this.stop = stop;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
