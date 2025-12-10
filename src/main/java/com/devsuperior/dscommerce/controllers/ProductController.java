package com.devsuperior.dscommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscommerce.dto.ProductDTO;
import com.devsuperior.dscommerce.services.ProductService;

@RestController
@RequestMapping(value = "/products")  //conf de um controlador rest. basta usar essas anotations > tudo q for /products vai cair aqui
public class ProductController { //Alguns gostam de usar Controller outros Resources  ambos sao recursos REST que tratam requisicoes

	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);   //Boas praticas customizacao do codigo de retorno da resposta
		
		
	}
		 
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) { //O import é do spring.data.domain
		
		Page<ProductDTO> dto = service.findAll(pageable);
		return ResponseEntity.ok(dto);		
		
	}//exemplo de uma consulta paginada
	

	@PostMapping
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
		
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);   //Boas praticas customizacao do codigo de retorno da resposta
		
		
	}
	
}//A customizacao do retorno da paginacao é feita do lado do front end. No postman por exemplo:
//se eu der localhost:8080/products  e passar os argumentos ?size=12$page=2&sort=name ele vai trazer a paginacao de 12 em 12 e ja 
//vai buscar a paginacao de numero 2 ordenada por nome



