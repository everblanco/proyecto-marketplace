package com.marketplace.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marketplace.clientes.fakestore.FakeStoreCliente;
import com.marketplace.clientes.fakestore.FakeStoreProductos;
import com.marketplace.converter.CarritoConverter;
import com.marketplace.dtos.*;
import com.marketplace.entity.*;
import com.marketplace.entity.CarritoItem;
import com.marketplace.enums.StatusTransaccion;
import com.marketplace.repository.CarritoItemRepository;
import com.marketplace.repository.CarritoRepository;
import com.marketplace.repository.TarjetaRepository;
import com.marketplace.repository.TransaccionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CarritoService {
	
	@Autowired
	CarritoRepository carritoRepository;
	
	@Autowired
	FakeStoreCliente fakeStoreCliente;
	
	@Autowired
	CarritoItemRepository carritoItemRepository;
	
	@Autowired
	TarjetaService tarjetaService;
	
	@Autowired
	TarjetaRepository tarjetaRepository;
	
	@Autowired
	TransaccionRepository transaccionRepository;
	
	@Autowired
	CarritoConverter carritoConverter;
	
	@Transactional
	public CarritoItem agregarAlCarrito(AgregarCarritoDTO request) {
		
		Optional<Carrito>  carritoOpt = this.getCarrito();
		
		Carrito carrito;
		
		if(carritoOpt.isEmpty()) {
			
			carrito = carritoRepository.save(Carrito.builder().build());
			
			carrito.setItems(new ArrayList<>());
			
		}else {
			
			carrito = carritoOpt.get();
		}
		
		Optional<FakeStoreProductos> productoOpt = fakeStoreCliente.findProductoById(request.getProductoId());
		
		if(productoOpt.isEmpty()) throw new RuntimeException("El id del producto no existe");
		
		CarritoItem item = null;
		
		for(CarritoItem itemPreexistente : carrito.getItems()) {
			
			if(itemPreexistente.getProductoId().longValue() == request.getProductoId()) {
				
				item = itemPreexistente;
				
				break;
			}
		}
		
	
		if(item == null) {
			
			item = CarritoItem.builder()
					.carrito(carrito)
					.productoId(request.getProductoId())
					.cantidad(request.getCantidad())
					.build();
		}else {
			
			item.setCantidad(item.getCantidad()+request.getCantidad());
		}
		 
		
//		carrito.getItems().add(item);
		
//		carrito = carritoRepository.save(carrito);
		
		item = carritoItemRepository.save(item);
		
		return item;
		
	}
	
	
	public Optional<Carrito> getCarrito(){
		
//		Optional<Carrito>  carrito = carritoRepository.findFirtsByOrderBy();
		
		List<Carrito> carritos = carritoRepository.findAll();
		
		if(carritos.isEmpty()) {
			return Optional.empty();
		}
		
		return Optional.of(carritos.get(0));
	}
	
	
	
	public void borrarItem(Long id) {
		
		Optional<CarritoItem> itemOpt = carritoItemRepository.findById(id);
		
		if(itemOpt.isEmpty()) throw new RuntimeException("El item no existe");
		
		carritoItemRepository.delete(itemOpt.get());
		
	}
	
	public Transaccion pagar(PagoDTO request) {
		
		int anno = Integer.parseInt(String.format("20%s", request.getFechaDeVencimiento().split("/")[1]));
		
		int mes = Integer.parseInt(request.getFechaDeVencimiento().split("/")[0]);
		
		LocalDate fechaVencimiento = LocalDate.of(anno, Month.of(mes), 1);
		
		Optional<Tarjeta> tarjetaOpt = tarjetaService.buscarPorDatosTarjeta(request.getNumeroDeTarjeta(), request.getNombre(),fechaVencimiento);
		
		if(tarjetaOpt.isEmpty()) throw new RuntimeException("Datos de pago incorrectos");
		
		Tarjeta tarjeta = tarjetaOpt.get();
		
		Optional<Carrito> carritoOpt = this.getCarrito();
		
		if(carritoOpt.isEmpty()) throw new RuntimeException("El carrito está vacio");
		
		Carrito carrito = carritoOpt.get();
		
		if(carrito.getItems().isEmpty()) throw new RuntimeException("El carrito está vacio");
		
		double total = 0;
		
		Transaccion transaccion = Transaccion.builder()
				.fecha(LocalDateTime.now())
				.build();
		
		List<TransaccionItem> items = new ArrayList<>();
		
		for(CarritoItem item : carrito.getItems()) {
			
			FakeStoreProductos producto = fakeStoreCliente.findProductoById(item.getProductoId())
					.orElseThrow(()->new RuntimeException(String.format("El producto %s ya no se encuentra disponible en el api de platzi, favor eliminar el producto del carrito antes de proceder con la compra", item.getProductoId())));
			
			double subTotal = item.getCantidad()*producto.getPrice();
			
			total+= subTotal;
			
			
			
			TransaccionItem i = TransaccionItem.builder()
					.transaccion(transaccion)
					.total(subTotal)
					.cantidad(item.getCantidad())
					.precioUnitario(producto.getPrice())
					.productoId(producto.getId())
					.build();
			
			items.add(i);
		}
		
		if(tarjeta.getSaldo()<total) throw new RuntimeException("Saldo insuficiente");
		
		tarjeta.setSaldo(tarjeta.getSaldo()-total);
		
		tarjeta = tarjetaRepository.save(tarjeta);
		
		transaccion.setTotal(total);
		
		transaccion.setItems(items);
		
		transaccion.setTarjeta(tarjeta);
		
		transaccion.setStatus(StatusTransaccion.EXITOSA);
		
		transaccion = transaccionRepository.save(transaccion);
		
		carritoRepository.delete(carrito);
		
		return transaccion;
	}
	
	public CarritoDTO consultarCarrito() {
		
		Carrito carrito = this.getCarrito().orElse(null);
		
		if(carrito == null) {
			return new CarritoDTO();
		}
		
		List<CarritoItemDTO> items = new ArrayList<>();
		
		double total = 0;
		
		for(CarritoItem item : carrito.getItems()) {
			
			CarritoItemDTO i = carritoConverter.fromEntity(item);
			
			items.add(i);
			
			Optional<FakeStoreProductos> productoOpto = fakeStoreCliente.findProductoById(item.getProductoId());
			
			if(productoOpto.isEmpty()) {
				
				i.setDisponible(false);
				
				continue;
			}
			
			FakeStoreProductos producto = productoOpto.get();
			
			i.setDisponible(true);
			
			i.setProducto(producto);
			
			total+=producto.getPrice()*item.getCantidad();
			
		}
		
		return CarritoDTO.builder()
				.id(carrito.getId())
				.total(total)
				.items(items)
				.build();
		
		
	}

}
