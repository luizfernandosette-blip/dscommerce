package com.devsuperior.dscommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	//Estrutura em camadas: O controlador rest chama o product service e recebe resposta uma lista de DTO da entidade
	//ProductService chama ProductRepository que por sua vez chama a entidade e opera o BD
	 
	@GetMapping
	public Page<ProductDTO> findAll(Pageable pageable) { //O import é do spring.data.domain
		
		return service.findAll(pageable);
		
		
	}//exemplo de uma consulta paginada
	
}//A customizacao do retorno da paginacao é feita do lado do front end. No postman por exemplo:
//se eu der localhost:8080/products  e passar os argumentos ?size=12$page=2&sort=name ele vai trazer a paginacao de 12 em 12 e ja 
//vai buscar a paginacao de numero 2 ordenada por nome
