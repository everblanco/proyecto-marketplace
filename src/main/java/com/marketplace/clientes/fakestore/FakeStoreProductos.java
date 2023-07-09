package com.marketplace.clientes.fakestore;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductos {
	
	private Long id;
	private String title;
	private Double price;
	private String description;
	
	private List<String> images;
	
	private LocalDateTime creationAt; 
	private LocalDateTime updatedAt;
	
	private FakeStoreCategoria category;
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class FakeStoreCategoria {
		private Long id;
		private String name;
		private String image;
		private LocalDateTime creationAt; 
		private LocalDateTime updatedAt;
	}
}

