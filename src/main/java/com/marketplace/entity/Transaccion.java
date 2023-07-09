package com.marketplace.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.marketplace.enums.StatusTransaccion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name="transacciones")
public class Transaccion {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="fecha",nullable = false,updatable = false)
	private LocalDateTime fecha;
	
	@Column(name="total",nullable = false,updatable = false)
	private Double total;
	
	@OneToMany(mappedBy = "transaccion",cascade = CascadeType.ALL)
	private List<TransaccionItem> items;
	
	@ManyToOne
	@JoinColumn(name = "tarjeta_id",nullable = false,updatable = false)
	private Tarjeta tarjeta;
	
	@Column(name = "status",nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusTransaccion status;

}
