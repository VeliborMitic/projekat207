package com.met.projekat.service;

import java.util.List;

import com.met.projekat.entities.Proizvod;



public interface ProizvodService {
	
List<Proizvod> listAllProizvodi();
	
	public Proizvod findOne(int id);

}
