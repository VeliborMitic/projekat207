package com.met.projekat.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.met.projekat.entities.Proizvod;

public interface ProizvodRepository extends JpaRepository <Proizvod, Integer>{
	Proizvod findById (int proizvod_id);
}
