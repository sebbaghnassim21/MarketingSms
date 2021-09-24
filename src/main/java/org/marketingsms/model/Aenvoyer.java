package org.marketingsms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Aenvoyer")
public class Aenvoyer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="min")
	private String min;
	@Column(name="ila")
	private String ila;
	@Column(name="textm")
	private String textm;
	@Column(name="envoyer")
    private int envoyer ;
	
	
	public Aenvoyer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Aenvoyer(long id, String min, String ila, String textm, int envoyer) {
		super();
		this.id = id;
		this.min = min;
		this.ila = ila;
		this.textm = textm;
		this.envoyer = envoyer;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getMin() {
		return min;
	}


	public void setMin(String min) {
		this.min = min;
	}


	public String getIla() {
		return ila;
	}


	public void setIla(String ila) {
		this.ila = ila;
	}


	public String getTextm() {
		return textm;
	}


	public void setTextm(String textm) {
		this.textm = textm;
	}


	public int getEnvoyer() {
		return envoyer;
	}


	public void setEnvoyer(int envoyer) {
		this.envoyer = envoyer;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	

}
