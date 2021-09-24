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
@Table(name="journal")
public class Journal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="fromnbr")
	private String fromnbr;
	@Column(name="tonbr")
	private String tonbr;
	@Column(name="nquestion")
	private int nquestion;
	@Column(name="reponse")
	private String reponse;
	@Column(name="date")
	private Date date;
	@Column(name="user")
	private String user;
	@Column(name="question")
	private String question;
	@Column(name="idquestion")
	private long idquestion;




	@ManyToOne
	private Shop shopjou;


	







	public Journal(long id, String fromnbr, String tonbr, int nquestion, String reponse, Date date, String user,long idquestion,
			Shop shopjou) {
		super();
		this.id = id;
		this.fromnbr = fromnbr;
		this.tonbr = tonbr;
		this.nquestion = nquestion;
		this.reponse = reponse;
		this.date = date;
		this.user = user;
		this.idquestion = idquestion;
		this.shopjou = shopjou;
	}



	public Journal() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getFromnbr() {
		return fromnbr;
	}



	public void setFromnbr(String fromnbr) {
		this.fromnbr = fromnbr;
	}



	public String getTonbr() {
		return tonbr;
	}



	public void setTonbr(String tonbr) {
		this.tonbr = tonbr;
	}



	public int getNquestion() {
		return nquestion;
	}



	public void setNquestion(int nquestion) {
		this.nquestion = nquestion;
	}



	public String getReponse() {
		return reponse;
	}



	public void setReponse(String reponse) {
		this.reponse = reponse;
	}



	public Shop getShopjou() {
		return shopjou;
	}



	public void setShopjou(Shop shopjou) {
		this.shopjou = shopjou;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}
	
	public String getQuestion() {
		return question;
	}



	public void setQuestion(String question) {
		this.question = question;
	}



	public long getIdquestion() {
		return idquestion;
	}



	public void setIdquestion(long idquestion) {
		this.idquestion = idquestion;
	}

}
