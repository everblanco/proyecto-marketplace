package com.marketplace.clientes.fakestore;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FakeStoreCliente {
	
	public Optional<FakeStoreProductos> findProductoById(Long productId) {
		
		try {
			
			RestTemplate template = new RestTemplate();
			
			ParameterizedTypeReference<FakeStoreProductos> responseType = new ParameterizedTypeReference<>() {};
			
			String url = "https://api.escuelajs.co/api/v1/products/{productId}";
			
			ResponseEntity<FakeStoreProductos> response = template.exchange(url, HttpMethod.GET, null, responseType, productId);

			if (response.getStatusCode() == HttpStatus.OK) {
				FakeStoreProductos producto = response.getBody();
			    return Optional.of(producto);
			} else {
			   return Optional.empty();
			}
			
		}catch(Exception e) {
			return Optional.empty();
		}
		
		
	}
	
	
	public List<FakeStoreProductos> findAllProductos(String title,Long categoryId) {
		RestTemplate template = new RestTemplate();
		
		ParameterizedTypeReference<List<FakeStoreProductos>> responseType = new ParameterizedTypeReference<>() {};
		
		String url = "https://api.escuelajs.co/api/v1/products";
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("title", title)
				.queryParam("categoryId", categoryId);
		
		ResponseEntity<List<FakeStoreProductos>> response = template.exchange(builder.toUriString(), HttpMethod.GET, null, responseType);

		if (response.getStatusCode() == HttpStatus.OK) {
			List<FakeStoreProductos> productos = response.getBody();
		    return productos;
		} else {
		   return null;
		}
	}
	
	

}
