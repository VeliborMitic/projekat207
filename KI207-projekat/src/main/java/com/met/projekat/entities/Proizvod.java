package com.met.projekat.entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "proizvod")
public class Proizvod {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "proizvod_id")
	private int id;
	
	@Column(name = "naziv")
	@NotEmpty
	private String naziv;
	
	@Column(name = "sifra")
	@NotEmpty
	private String sifra;
	
	@Column(name = "opis")
	@NotEmpty
	private String opis;
	
	@Column(name = "slika")
	private String imageUrl;
	
	@Column(name = "cena")
	@NotEmpty
    private BigDecimal cena;
	
	@Column(name = "stanje")
	@NotEmpty
	private int stanje;


	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getCena() {
		return cena;
	}

	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	

	@ManyToMany(mappedBy = "proizvodi")    
	private Set<User> users;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public int getStanje() {
		return stanje;
	}

	public void setStanje(int stanje) {
		this.stanje = stanje;
	}

	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", sifra=" + sifra + ", opis=" + opis + ", imageUrl="
				+ imageUrl + ", cena=" + cena + ", stanje=" + stanje + ", users=" + users + "]";
	}

	

	

	
	
}
