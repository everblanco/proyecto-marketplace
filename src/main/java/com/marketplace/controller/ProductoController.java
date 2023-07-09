package com.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.clientes.fakestore.FakeStoreCliente;
import com.marketplace.clientes.fakestore.FakeStoreProductos;
import com.marketplace.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/productos")
public class ProductoController {
	
	@Autowired
	private FakeStoreCliente fakeStoreCliente;
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
	@GetMapping
	public ResponseEntity<WrapperResponse<List<FakeStoreProductos>>> findAll(
			@RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "categoryId", required = false) Long categoryId
			){
		
		List<FakeStoreProductos> productos = fakeStoreCliente.findAllProductos(title,categoryId);
		
		return new WrapperResponse<>(true, "Exitoso", productos)
				.createResponse();
	}
	
	
	
	@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
	@GetMapping(value = "{id}")
	public ResponseEntity<WrapperResponse<FakeStoreProductos>> findById(@PathVariable(name="id") Long id){
		
		FakeStoreProductos producto = fakeStoreCliente.findProductoById(id).orElse(null);
		
		return new WrapperResponse<>(true, "Exitoso", producto)
				.createResponse();
		
	}
	
	

}
