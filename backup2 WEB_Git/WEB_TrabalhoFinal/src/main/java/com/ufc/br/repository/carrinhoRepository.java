package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufc.br.model.Prato;

@Repository
public interface carrinhoRepository extends JpaRepository<Prato, Long>{
	

}

