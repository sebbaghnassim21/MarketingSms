package org.marketingsms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id ;
	@Column(name = "text")
	private String text;
	@Column(name = "type")
	private String type ;
	@Column(name = "questiona")
	private String questiona;
	@Column(name = "questionb")
	private String questionb;
	@Column(name = "questionc")
	private String questionc;
	@Column(name = "questiond")
	private String questiond;
	@Column(name = "questione")
	private String questione;
	private int nques;
	@ManyToOne
	private Shop shop;
	
	
	
	public Question(long id, String text, String type, String questiona, String questionb, String questionc,
			String questiond, String questione, Shop shop,int nques) {
		super();
		this.id = id;
		this.text = text;
		this.type = type;
		this.questiona = questiona;
		this.questionb = questionb;
		this.questionc = questionc;
		this.questiond = questiond;
		this.questione = questione;
		this.shop = shop;
		this.nques=nques;
	}



	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getQuestiona() {
		return questiona;
	}



	public void setQuestiona(String questiona) {
		this.questiona = questiona;
	}



	public String getQuestionb() {
		return questionb;
	}



	public void setQuestionb(String questionb) {
		this.questionb = questionb;
	}



	public String getQuestionc() {
		return questionc;
	}



	public void setQuestionc(String questionc) {
		this.questionc = questionc;
	}



	public String getQuestiond() {
		return questiond;
	}



	public void setQuestiond(String questiond) {
		this.questiond = questiond;
	}



	public String getQuestione() {
		return questione;
	}



	public void setQuestione(String questione) {
		this.questione = questione;
	}



	public Shop getShop() {
		return shop;
	}



	public void setShop(Shop shop) {
		this.shop = shop;
	}



	public int getNques() {
		return nques;
	}



	public void setNques(int nques) {
		this.nques = nques;
	}
	
	
	

}
