package org.marketingsms.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "shop")
public class Shop implements Serializable {


/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "codemag")
private int codemag;
@Column(name = "nommag")
private String nommag;
@Column(name = "codesms")
private String codesms;
@Column(name = "accountsid")
private String accountsid;
@Column(name = "authtoken")
private String authtoken;
@Column(name = "twilio")
private String twilio;
@Column(name = "typepiece")
private String typepiece;
@Column(name = "typecomp")
private String typecomp;
@Column(name = "statut")
private String statut;
@Column(name="date")
private Date date;
private int nquestion ;
@ManyToOne
private User user;
@OneToMany(mappedBy="shop",fetch=FetchType.LAZY)
private Collection<Question> question;
@OneToMany(mappedBy="shopc",fetch=FetchType.LAZY)
private Collection<Client> client;

@OneToMany(mappedBy="shopjou",fetch=FetchType.LAZY)
private Collection<Journal> journal;

@OneToMany(mappedBy="shopmess",fetch=FetchType.LAZY)
private Collection<Messagee> messagee;

public Shop() {
	super();
	// TODO Auto-generated constructor stub
}







public Collection<Question> getQuestion() {
	return question;
}







public void setQuestion(Collection<Question> question) {
	this.question = question;
}







public static long getSerialversionuid() {
	return serialVersionUID;
}







public Shop(int codemag, String nommag, String codesms, String accountsid, String authtoken, String twilio, User user,
		int nquestion,Collection<Question> question, String typepiece, String typecomp, String statut, Date date) {
	super();
	this.codemag = codemag;
	this.nommag = nommag;
	this.codesms = codesms;
	this.accountsid = accountsid;
	this.authtoken = authtoken;
	this.twilio = twilio;
	this.user = user;
	this.question = question;
	this.nquestion= nquestion;
    this.typepiece=typepiece;
    this.typecomp=typecomp;
    this.statut=statut;
    this.date=date;
}







public Date getDate() {
	return date;
}







public void setDate(Date date) {
	this.date = date;
}







public int getNquestion() {
	return nquestion;
}







public void setNquestion(int nquestion) {
	this.nquestion = nquestion;
}







public String getAccountsid() {
	return accountsid;
}

public void setAccountsid(String accountsid) {
	this.accountsid = accountsid;
}

public String getAuthtoken() {
	return authtoken;
}

public void setAuthtoken(String authtoken) {
	this.authtoken = authtoken;
}

public String getTwilio() {
	return twilio;
}

public void setTwilio(String twilio) {
	this.twilio = twilio;
}

public int getCodemag() {
	return codemag;
}
public void setCodemag(int codemag) {
	this.codemag = codemag;
}
public String getNommag() {
	return nommag;
}
public void setNommag(String nommag) {
	this.nommag = nommag;
}
public String getCodesms() {
	return codesms;
}
public void setCodesms(String codesms) {
	this.codesms = codesms;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}







public String getTypepiece() {
	return typepiece;
}







public void setTypepiece(String typepiece) {
	this.typepiece = typepiece;
}







public Collection<Client> getClient() {
	return client;
}







public void setClient(Collection<Client> client) {
	this.client = client;
}







public Collection<Journal> getJournal() {
	return journal;
}







public void setJournal(Collection<Journal> journal) {
	this.journal = journal;
}







public Collection<Messagee> getMessagee() {
	return messagee;
}







public void setMessagee(Collection<Messagee> messagee) {
	this.messagee = messagee;
}







public String getTypecomp() {
	return typecomp;
}







public void setTypecomp(String typecomp) {
	this.typecomp = typecomp;
}







public String getStatut() {
	return statut;
}







public void setStatut(String statut) {
	this.statut = statut;
}




}
