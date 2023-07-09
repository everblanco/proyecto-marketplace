package com.marketplace.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.marketplace.enums.StatusTransaccion;


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
public class TransaccionDTO {
	
	
	private Long id;
	
	private LocalDateTime fecha;
	
	private Double total;
	
	private List<TransaccionItemDTO> items;
	
	private StatusTransaccion status;

}
