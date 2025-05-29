package com.forms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forms.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	//Query Methods
	List<Cliente> findByNome (String nome);

}
