package org.marketingsms.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tacher")
public class Tacher implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	@Column(name="camp")
	private String camp;
	@Column(name = "codemag")
	private int codemag;
	@Column(name = "datede")
	private Date datede; 
	@Column(name = "timede")
	private String timede; 
	@Column(name = "type")
	private String type;
	@Column(name = "statut")
	private String statut;
	
	
	public Tacher() {
		super();
		// TODO Auto-generated constructor stub
	}









	public Tacher(long id, String camp, int codemag, Date datede, String timede, String type, String statut) {
		super();
		this.id = id;
		this.camp = camp;
		this.codemag = codemag;
		this.datede = datede;
		this.timede = timede;
		this.type = type;
		this.statut = statut;
	}









	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCamp() {
		return camp;
	}


	public void setCamp(String camp) {
		this.camp = camp;
	}


	public int getCodemag() {
		return codemag;
	}


	public void setCodemag(int codemag) {
		this.codemag = codemag;
	}


	public Date getDatede() {
		return datede;
	}


	public void setDatede(Date datede) {
		this.datede = datede;
	}


	

	public String getTimede() {
		return timede;
	}


	public void setTimede(String timede) {
		this.timede = timede;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}









	public String getStatut() {
		return statut;
	}









	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	
	
	
	
	
	
}
