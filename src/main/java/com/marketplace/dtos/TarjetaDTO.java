package com.marketplace.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marketplace.entity.Tarjeta;
import com.marketplace.enums.TipoTarjeta;

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
public class TarjetaDTO {
	
	private Long id;
	private String numero;
	private Double saldo;
	private String nombre;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimiento;
	private TipoTarjeta tipo;

}
