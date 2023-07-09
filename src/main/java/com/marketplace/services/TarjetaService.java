package com.marketplace.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketplace.clientes.fakestore.FakeStoreCliente;
import com.marketplace.clientes.fakestore.FakeStoreProductos;
import com.marketplace.dtos.CrearTarjetaDTO;
import com.marketplace.dtos.RecargaTarjetaDTO;
import com.marketplace.entity.Tarjeta;
import com.marketplace.repository.TarjetaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TarjetaService {
	
	@Autowired
	TarjetaRepository tarjetaRepository;
	
	@Autowired
	FakeStoreCliente fakeStoreCliente;
	
	public Optional<Tarjeta> findByNumero(String numero){
		
		try {
			return tarjetaRepository.findByNumero(numero);
			
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	@Transactional
	public Tarjeta crearTarjeta(CrearTarjetaDTO request) {
		
		if(request == null) throw new RuntimeException("Bad Request");
		
		Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByNumero(request.getNumero());
		
		if(tarjetaOpt.isPresent()) throw new RuntimeException("Ya existe una tarjeta con el número indicado");
		
		String productoToken = request.getNumero().substring(0, 6);
		
		String numeroToken = request.getNumero().substring(6);
		
		Optional<FakeStoreProductos> productoOpt = fakeStoreCliente.findProductoById(Long.parseLong(productoToken));
		
		if(productoOpt.isEmpty()) throw new RuntimeException("El id del producto no existe");
		
		Tarjeta tarjeta = Tarjeta.builder()
				.numero(request.getNumero())
				.saldo(0d)
				.nombre(request.getNombre())
				.fechaVencimiento(LocalDate.now().withDayOfMonth(1).plusYears(3L))
				.tipo(request.getTipo())
				.build();
		
		tarjeta = tarjetaRepository.save(tarjeta);
		
		return tarjeta;
		
		
	}
	
	
	@Transactional
	public Tarjeta recarga(RecargaTarjetaDTO request) {
		
		Optional<Tarjeta> tarjetaOpt = this.findByNumero(request.getNumero());
		
		if(tarjetaOpt.isEmpty()) throw new RuntimeException("No existe el número de tarjeta");
		
		Tarjeta tarjeta = tarjetaOpt.get();
		
		tarjeta.setSaldo(tarjeta.getSaldo()+request.getRecarga());
		
		tarjeta = tarjetaRepository.save(tarjeta);
		
		return tarjeta;
		
	}
	
	
	public Optional<Tarjeta> buscarPorDatosTarjeta(String numeroDeTarjeta, String nombre, LocalDate fechaVencimiento){
		
		fechaVencimiento = fechaVencimiento.withDayOfMonth(1);
		
		Optional<Tarjeta> tarjetaOpt = tarjetaRepository.findByNumeroAndNombreAndFechaVencimiento(numeroDeTarjeta, nombre, fechaVencimiento);
		
		return tarjetaOpt;
	}
	

}
