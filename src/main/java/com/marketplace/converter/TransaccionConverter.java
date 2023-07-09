package com.marketplace.converter;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.marketplace.dtos.*;
import com.marketplace.entity.*;

@Component
public class TransaccionConverter extends AbstractConverter<Transaccion,TransaccionDTO>{

	@Override
	public TransaccionDTO fromEntity(Transaccion entity) {
		
		if(entity == null) return null;
		
		return TransaccionDTO.builder()
				.id(entity.getId())
				.fecha(entity.getFecha())
				.total(entity.getTotal())
				.items(entity.getItems().stream()
						.map(e -> fromEntity(e))
						.collect(Collectors.toList()))
				.build();
	}

	@Override
	public Transaccion fromDTO(TransaccionDTO dto) {
		return null;
	}
	
	public TransaccionItemDTO fromEntity(TransaccionItem entity) {
		return TransaccionItemDTO.builder()
				.total(entity.getTotal())
				.cantidad(entity.getCantidad())
				.precioUnitario(entity.getPrecioUnitario())
				.productoId(entity.getProductoId())
				.id(entity.getId())
				.build();
	}

}
