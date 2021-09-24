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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Fournissez une adresse email valide")
	@NotEmpty(message = "Fournissez une adresse email")
	private String email;
	
	@Column(name = "password")
	@Transient
	private String password;
	
	@Column(name = "titre")
	@NotEmpty(message = "Fournissez le titre")
	private String titre;
	
	@Column(name = "first_name")
	@NotEmpty(message = "Fournissez votre nom")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message = "Fournissez votre prenom")
	private String lastName;
	
	@Column(name = "enabled")
	private boolean enabled;
	


	@Column(name = "confirmation_token")
	private String confirmationToken;
	
	@Column (name="role")
	private String role;

	@Column(name="compagnie")
	private String compagnie;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column (name="city")
	private String city;
	
	@Column (name="Province")
	private String Province;
	
	@Column (name="Postalcode")
	private String Postalcode;
	
	@Column (name="country")
	private String country;
	
	@Column (name="TEL")
	/*@Pattern(regexp="(^$|[0-9])")   //@Pattern(regexp="(^$|[0-9]{10})")*/
	private String Tel;
	
	
	@Column (name="Fax")
	/*@Pattern(regexp="(^$|[0-9])")   //@Pattern(regexp="(^$|[0-9]{10})")*/
	private String fax;
	
	
	@Column (name="accountsid")
	private String accountsid;
	
	@Column (name="authtoken")//AUTH_TOKEN
	private String authtoken;
	
	@Column (name="twilio")//twilio
	private String twilio;
	
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private Collection<Shop> shops;

	@OneToMany(mappedBy="usermag",fetch=FetchType.LAZY)
	private Collection<Magasin> magasins;
	
	private Date datecreation;
	

	
	
	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	





	public User(int id, String email, String password, String titre, String firstName, String lastName, boolean enabled,
			String confirmationToken, String role, String compagnie, String adresse, String city, String province,
			String postalcode, String country, String tel, String fax, String accountsid, String authtoken,
			String twilio, Collection<Shop> shops, Collection<Magasin> magasins, Date datecreation) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.titre = titre;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.confirmationToken = confirmationToken;
		this.role = role;
		this.compagnie = compagnie;
		this.adresse = adresse;
		this.city = city;
		Province = province;
		Postalcode = postalcode;
		this.country = country;
		Tel = tel;
		this.fax = fax;
		this.accountsid = accountsid;
		this.authtoken = authtoken;
		this.twilio = twilio;
		this.shops = shops;
		this.magasins = magasins;
		this.datecreation = datecreation;
	}







	public String getTitre() {
		return titre;
	}







	public void setTitre(String titre) {
		this.titre = titre;
	}







	public Collection<Magasin> getMagasins() {
		return magasins;
	}




	public void setMagasins(Collection<Magasin> magasins) {
		this.magasins = magasins;
	}




	public String getCompagnie() {
		return compagnie;
	}


	public void setCompagnie(String compagnie) {
		this.compagnie = compagnie;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getProvince() {
		return Province;
	}


	public void setProvince(String province) {
		Province = province;
	}


	public String getPostalcode() {
		return Postalcode;
	}


	public void setPostalcode(String postalcode) {
		Postalcode = postalcode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getTel() {
		return Tel;
	}


	public void setTel(String tel) {
		Tel = tel;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getConfirmationToken() {
		return confirmationToken;
	}
	

	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean value) {
		this.enabled = value;
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


	public Collection<Shop> getShops() {
		return shops;
	}


	public void setShops(Collection<Shop> shops) {
		this.shops = shops;
	}


	public Date getDatecreation() {
		return datecreation;
	}


	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	
}