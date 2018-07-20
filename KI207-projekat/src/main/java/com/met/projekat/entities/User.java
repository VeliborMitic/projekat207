package com.met.projekat.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_name")
	@NotEmpty(message = "*Unesite username")
	private String userName;
	
	@Column(name = "email")
	@Email(message = "*Uneti Email nije validan")
	@NotEmpty(message = "*Unesite email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Vas password mora imati najmanje 5 karaktera")
	@NotEmpty(message = "*Unesite password")
	@Transient
	private String password;
	
	@Column(name = "first_name")
	@NotEmpty(message = "*Unesite ime")
	private String firstName;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Unesite prezime")
	private String lastName;
	
	@Column(name = "active")
	private int active;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
@JoinTable(name = "user_proizvod", joinColumns = @JoinColumn(name = "user_id"), 
inverseJoinColumns = @JoinColumn(name = "proizvod_id"))
private Set<Proizvod> proizvodi;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

	public Set<Proizvod> getProizvodi() {
		// TODO Auto-generated method stub
		return proizvodi;
	} 
	public void setProizvodi(Set<Proizvod> proizvodi) {
		this.proizvodi = proizvodi;
	}
	
	
	
	public boolean hasProizvod(Proizvod pr) {
		for (Proizvod prc: getProizvodi()) {
			if (prc.getId() == pr.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", active=" + active + ", roles=" + roles
				+ ", proizvodi=" + proizvodi + "]";
	}
	
	

}
