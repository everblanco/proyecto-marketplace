package com.marketplace.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoTarjeta {
	@JsonProperty("credito")
	CREDITO,
	@JsonProperty("debito")
	DEBITO

}
