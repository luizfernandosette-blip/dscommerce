package com.devsuperior.dscommerce.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.ProductRepository;

@Service  //esse é o service de produtos
public class ProductService {
	
	@Autowired  //para instanciar automaticamente
	private ProductRepository repository;
	
	@Transactional(readOnly = true)  //import do SPRINGFRAMEWORK, nao é do jackarta
	public ProductDTO findById(Long id) {
		
		Optional<Product> result = repository.findById(id); 
		Product product = result.get();
		ProductDTO dto = new ProductDTO(product);
		return dto;
	}
	
}
