package com.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.converter.TarjetaConverter;
import com.marketplace.dtos.CrearTarjetaDTO;
import com.marketplace.dtos.RecargaTarjetaDTO;
import com.marketplace.dtos.TarjetaDTO;
import com.marketplace.entity.Tarjeta;
import com.marketplace.services.TarjetaService;
import com.marketplace.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {
	
	@Autowired
	TarjetaService tarjetaService; 
	
	@Autowired
	TarjetaConverter tarjetaConverter;
	
	@PostMapping
	@Transactional
	public ResponseEntity<WrapperResponse<TarjetaDTO>> create(@RequestBody @Validated CrearTarjetaDTO request){
		
		Tarjeta tarjeta = tarjetaService.crearTarjeta(request);
		
		return new WrapperResponse<>(true, "Exitoso", tarjetaConverter.fromEntity(tarjeta))
				.createResponse();
	}
	
	
	@GetMapping("byNumber/{numero}")
	public ResponseEntity<WrapperResponse<TarjetaDTO>> findByNumber(@PathVariable("numero") String numero){
		
		Tarjeta tarjeta = tarjetaService.findByNumero(numero).orElse(null);
		
		return new WrapperResponse<>(true, "Exitoso", tarjetaConverter.fromEntity(tarjeta))
				.createResponse();
	}
	
	
	@PostMapping("recargas")
	@Transactional
	public ResponseEntity<WrapperResponse<TarjetaDTO>> recargas(@RequestBody @Validated RecargaTarjetaDTO request){
		
		Tarjeta tarjeta = tarjetaService.recarga(request);
		
		return new WrapperResponse<>(true, "Exitoso", tarjetaConverter.fromEntity(tarjeta))
				.createResponse();
	}
	

}
