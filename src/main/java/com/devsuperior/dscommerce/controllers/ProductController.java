package com.devsuperior.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")  //conf de um controlador rest. basta usar essas anotations > tudo q for /products vai cair aqui
public class ProductController { //Alguns gostam de usar Controller outros Resources  ambos sao recursos REST que tratam requisicoes

	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		
		ProductDTO dto = service.findById(id);
		return dto;
		
		
	}
	
}
