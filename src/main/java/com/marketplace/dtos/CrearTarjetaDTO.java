package com.marketplace.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CrearTarjetaDTO {
	
	private Long id;
	@Pattern(regexp = "\\d{16}", message = "El campo debe tener exactamente 16 dígitos")
	@NotBlank
	private String numero;
	private String nombre;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVencimiento;
	private TipoTarjeta tipo;

}
