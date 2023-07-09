package com.marketplace.converter;

import org.springframework.stereotype.Component;

import com.marketplace.dtos.TarjetaDTO;
import com.marketplace.entity.Tarjeta;

@Component
public class TarjetaConverter extends AbstractConverter<Tarjeta,TarjetaDTO>{

	@Override
	public TarjetaDTO fromEntity(Tarjeta entity) {
		if (entity == null) return null;
		return TarjetaDTO.builder()
				.id(entity.getId())
				.numero(entity.getNumero())
				.saldo(entity.getSaldo())
				.nombre(entity.getNombre())
				.fechaVencimiento(entity.getFechaVencimiento())
				.tipo(entity.getTipo())
				.build();
	}

	@Override
	public Tarjeta fromDTO(TarjetaDTO dto) {
		return null;
	}

	

}
