package com.marketplace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketplace.entity.Tarjeta;
import com.marketplace.entity.Transaccion;
import com.marketplace.enums.StatusTransaccion;
import com.marketplace.repository.TarjetaRepository;
import com.marketplace.repository.TransaccionRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TransaccionService {
	
	@Autowired
	TransaccionRepository transaccionRepository;
	
	@Autowired
	TarjetaRepository tarjetaRepository;
	
	@Transactional
	public Transaccion anularTransaccion(Long transaccionId) {
		
		Transaccion transaccion = transaccionRepository.findById(transaccionId)
				.orElseThrow(() -> new RuntimeException("No existe la transacción"));
		
		if(transaccion.getStatus() != StatusTransaccion.EXITOSA) {
			
			throw new RuntimeException("La transacción no está en esta exitosa, y no se puede anular");
		}
		
		Tarjeta tarjeta = transaccion.getTarjeta();
		
		tarjeta.setSaldo(tarjeta.getSaldo()+transaccion.getTotal());
		
		tarjeta = tarjetaRepository.save(tarjeta);
		
		transaccion.setStatus(StatusTransaccion.ANULADA);
		
		transaccion = transaccionRepository.save(transaccion);
		
		return transaccion;
		
	}
	
	
	public List<Transaccion> findAll(){
		
		return transaccionRepository.findAll();
	}

}
