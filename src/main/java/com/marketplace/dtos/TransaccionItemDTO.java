package com.marketplace.dtos;

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
public class TransaccionItemDTO {
	
	
	private Long id;
		
	private Integer cantidad;
		
	private Double precioUnitario;
	
	private Double total;
	
	private Long productoId;

}
