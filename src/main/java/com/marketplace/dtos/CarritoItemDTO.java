package com.marketplace.dtos;

import javax.validation.constraints.NotNull;

import com.marketplace.clientes.fakestore.FakeStoreProductos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoItemDTO {
	
	private Long id;
		
	private Long productoId;
	
	private Integer cantidad;
	
	private boolean disponible;
	
	private FakeStoreProductos producto;

}

