package com.marketplace.entity;

import java.time.LocalDate;

import com.marketplace.enums.TipoTarjeta;

import jakarta.persistence.*;
import jakarta.persistence.Table;
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
@Entity
@Table(name="tarjetas")
public class Tarjeta {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero",length = 16,updatable = false,unique=true,nullable = false)
	private String numero;
	
	@Column(name="saldo",nullable = false)
	private Double saldo;
	
	@Column(name="nombre",length = 100,nullable = false)
	private String nombre;
	
	@Column(name="fecha_vencimiento",nullable = false)
	private LocalDate fechaVencimiento;
	
	@Column(name="tipo",nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoTarjeta tipo;
	

}
