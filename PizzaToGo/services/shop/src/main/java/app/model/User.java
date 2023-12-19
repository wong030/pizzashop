package app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT r FROM User r"),
	@NamedQuery(name = "User.findByUserName", query = "SELECT r FROM User r WHERE r.userName = :userName")
})

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", nullable = false)
	private Integer userId;
	
	@Column(name="username", length=20, nullable=false, unique=true)
	private String userName;
	
	@Column(name="E_Mail", length=50, nullable=false)
	private String email;
	
	@Column(name="firstname", length=20, nullable=false)
	private String firstName;
	
	@Column(name="lastname", length=20, nullable=false)
	private String lastName;
	
	@Column(name="street", length=30, nullable=false)
	private String street;
	
	@Column(name="streetNr", length=20, nullable=false)
	private String streetNr;
	
	@Column(name="zip", length=20, nullable=false)
	private String zip;
	
	@Column(name="city", length=30, nullable=false)
	private String city;
	
	@Column(name = "password_hash", nullable = false)
	private byte[] passwordHash;

	@Column(name = "password_salt", nullable = false)
	private byte[] passwordSalt;
	
	public User() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNr() {
		return streetNr;
	}

	public void setStreetNr(String streetNr) {
		this.streetNr = streetNr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(byte[] passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	
	
	
}
