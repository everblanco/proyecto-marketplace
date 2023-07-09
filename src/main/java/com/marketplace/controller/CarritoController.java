package com.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.converter.*;
import com.marketplace.dtos.AgregarCarritoDTO;
import com.marketplace.dtos.*;
import com.marketplace.entity.*;
import com.marketplace.services.CarritoService;
import com.marketplace.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/carritos")
public class CarritoController {
	
	@Autowired
	CarritoService carritoService;
	
	@Autowired
	CarritoConverter carritoConverter;
	
	@Autowired
	TransaccionConverter transaccionConverter;
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
	@PostMapping
	@Transactional
	public ResponseEntity<WrapperResponse<CarritoItemDTO>>  agregarAlCarrito(@RequestBody @Validated  AgregarCarritoDTO request) {
		
		CarritoItem item = carritoService.agregarAlCarrito(request);
		
		return new WrapperResponse<>(true, "Exitoso", carritoConverter.fromEntity(item))
				.createResponse();
		
	}
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE})
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<WrapperResponse<Void>>  eliminarDelCarrito(@PathVariable("id") Long id) {
		
		carritoService.borrarItem(id); 
		
		return new WrapperResponse<Void>(true, "Exitoso", null)
				.createResponse();
		
	}
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
	@PostMapping("/pagar")
	@Transactional
	public ResponseEntity<WrapperResponse<TransaccionDTO>>  pagar(@RequestBody @Validated  PagoDTO request) {
		
		Transaccion transaccion = carritoService.pagar(request);
		
		return new WrapperResponse<>(true, "Exitoso", transaccionConverter.fromEntity(transaccion))
				.createResponse();
		
	}
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
	@GetMapping
	public ResponseEntity<WrapperResponse<CarritoDTO>>  consultarCarrito() {
		
		CarritoDTO carrito = carritoService.consultarCarrito();
		
		return new WrapperResponse<>(true, "Exitoso", carrito)
				.createResponse();
		
	}

}
