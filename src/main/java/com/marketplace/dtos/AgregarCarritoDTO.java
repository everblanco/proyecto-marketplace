package com.marketplace.dtos;


import javax.validation.constraints.NotNull;
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
public class AgregarCarritoDTO {
	
	@NotNull
	private Long productoId;
	@NotNull
	@Positive
	private Integer cantidad;

}
