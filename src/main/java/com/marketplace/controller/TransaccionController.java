package com.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.clientes.fakestore.FakeStoreProductos;
import com.marketplace.converter.*;
import com.marketplace.dtos.*;
import com.marketplace.entity.*;
import com.marketplace.services.*;
import com.marketplace.utils.WrapperResponse;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
	
	@Autowired
	TransaccionService transaccionService;
	
	@Autowired
	TransaccionConverter transaccionConverter;
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
	@PutMapping(value = "{id}/anular")
	public ResponseEntity<WrapperResponse<TransaccionDTO>> anular(@PathVariable(name="id") Long id){
		
		Transaccion transaccion = transaccionService.anularTransaccion(id);
		
		return new WrapperResponse<>(true, "Exitoso", transaccionConverter.fromEntity(transaccion))
				.createResponse();
		
	}
	

	@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
	@GetMapping
	public ResponseEntity<WrapperResponse<List<TransaccionDTO>>> buscarTodas(){
		
		List<Transaccion> transacciones = transaccionService.findAll();
		
		return new WrapperResponse<>(true, "Exitoso", transaccionConverter.fromEntity(transacciones))
				.createResponse();
		
	}
}
