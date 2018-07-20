package com.met.projekat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.projekat.entities.Proizvod;
import com.met.projekat.repository.ProizvodRepository;
@Service
public class ProizvodServiceImpl implements ProizvodService {
	@Autowired
	private ProizvodRepository proizvodRepository;

	@Override
	public List<Proizvod> listAllProizvodi() {
		List<Proizvod> proizvodi = proizvodRepository.findAll();
		return proizvodi;
		
	}

	@Override //////////////////////////////////////////////////////////////////////
	public Proizvod findOne(int id) {
		Proizvod p = proizvodRepository.getOne(id);
		return p;
		
	}

}
