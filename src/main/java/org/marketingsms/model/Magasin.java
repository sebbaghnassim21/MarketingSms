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

import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="magasin")
public class Magasin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codemagasin")
	private int codemagasin ;
	
	@Column(name = "nommagasin")

	private String nommagasin;
	
	@Column(name = "adressemag")

	private String adressemag;
	
	@Column(name = "kbis")
	private String kbis;
	
	@Column(name = "ntel")
	private String ntel;
	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "site")
	private String site;
	
	@Column(name = "datepay")
	private Date datepay;
	
	@Column(name = "dateexp")
	private Date dateexp;
	
	@Column(name = "active")
	private boolean  active;
	

	@Column(name = "forfait")
	private String  forfait;
	
	
	@ManyToOne
	private User usermag;



	public Magasin() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	




	public Magasin(int codemagasin, String nommagasin, String adressemag, String kbis, String ntel, String mobile,
			String mail, String site, Date datepay, Date dateexp, boolean active, String forfait, User usermag) {
		super();
		this.codemagasin = codemagasin;
		this.nommagasin = nommagasin;
		this.adressemag = adressemag;
		this.kbis = kbis;
		this.ntel = ntel;
		this.mobile = mobile;
		this.mail = mail;
		this.site = site;
		this.datepay = datepay;
		this.dateexp = dateexp;
		this.active = active;
		this.forfait = forfait;
		this.usermag = usermag;
	}







	public String getForfait() {
		return forfait;
	}







	public void setForfait(String forfait) {
		this.forfait = forfait;
	}







	public String getSite() {
		return site;
	}










	public void setSite(String site) {
		this.site = site;
	}










	public User getUsermag() {
		return usermag;
	}




	public void setUsermag(User usermag) {
		this.usermag = usermag;
	}




	public int getCodemagasin() {
		return codemagasin;
	}

	public void setCodemagasin(int codemagasin) {
		this.codemagasin = codemagasin;
	}

	public String getNommagasin() {
		return nommagasin;
	}

	public void setNommagasin(String nommagasin) {
		this.nommagasin = nommagasin;
	}

	public String getAdressemag() {
		return adressemag;
	}

	public void setAdressemag(String adressemag) {
		this.adressemag = adressemag;
	}

	public String getKbis() {
		return kbis;
	}

	public void setKbis(String kbis) {
		this.kbis = kbis;
	}

	public String getNtel() {
		return ntel;
	}

	public void setNtel(String ntel) {
		this.ntel = ntel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Date getDatepay() {
		return datepay;
	}

	public void setDatepay(Date datepay) {
		this.datepay = datepay;
	}

	public Date getDateexp() {
		return dateexp;
	}

	public void setDateexp(Date dateexp) {
		this.dateexp = dateexp;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
