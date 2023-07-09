package com.marketplace.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
public class RecargaTarjetaDTO {
	
	@Pattern(regexp = "\\d{16}", message = "El campo debe tener exactamente 16 dígitos")
	@NotBlank
	private String numero;
	@Positive
	@NotNull
	private Double recarga;
	

}
