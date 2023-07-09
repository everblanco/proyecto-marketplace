package com.marketplace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marketplace.entity.Carrito;


public interface CarritoRepository extends JpaRepository<Carrito, Long>{
	
	
	

}
