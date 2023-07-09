package com.marketplace.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marketplace.entity.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long>{
	
	public Optional<Tarjeta> findByNumero(String numero);
	
	public Optional<Tarjeta> findByNumeroAndNombreAndFechaVencimiento(String numero, String nombre, LocalDate fechaVencimiento);

}
