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
@Table(name="messagee")
public class Messagee implements Serializable {
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
	@Column(name = "company")
	private String company;
	@ManyToOne
	private Shop shopmess ;
	
	
	
	public Messagee() {
		super();
	
	}
	public Messagee(long id, String text, Shop shopmess, String company) {
		super();
		this.id = id;
		this.text = text;
		this.company = company;
		this.shopmess = shopmess;
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
	public Shop getShopmess() {
		return shopmess;
	}
	public void setShopmess(Shop shopmess) {
		this.shopmess = shopmess;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	

	
	
}
