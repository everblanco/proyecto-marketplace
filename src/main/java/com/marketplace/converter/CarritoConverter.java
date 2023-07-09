package com.marketplace.converter;

import org.springframework.stereotype.Component;

import com.marketplace.dtos.*;
import com.marketplace.entity.*;

@Component
public class CarritoConverter extends AbstractConverter<CarritoItem,CarritoItemDTO>{

	@Override
	public CarritoItemDTO fromEntity(CarritoItem entity) {
		if(entity == null) return null;
		
		return CarritoItemDTO.builder()
				.cantidad(entity.getCantidad())
				.id(entity.getId())
				.productoId(entity.getProductoId())
				.build();
			
	}

	@Override
	public CarritoItem fromDTO(CarritoItemDTO dto) {
		return null;
	}
	
	

}
