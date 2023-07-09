package com.marketplace.dtos;

import java.util.List;

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
public class CarritoDTO {
	
	
	private Long id;
	
	private double total; 
		
	private List<CarritoItemDTO> items;
	
	

}
