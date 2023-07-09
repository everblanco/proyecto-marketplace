package com.marketplace.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StatusTransaccion {
	
	@JsonProperty("exitosa")
	EXITOSA, 
	@JsonProperty("rechazada")
	RECHAZADA,
	@JsonProperty("anulada")
	ANULADA

}
