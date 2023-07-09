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
public class PagoDTO {
	
	private String nombre;
	private String fechaDeVencimiento;
	private String numeroDeTarjeta;

}
