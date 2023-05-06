package com.depo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depo.requestDTO.ProductRequestDTO;
import com.depo.responseDTO.ProductResponseDTO;
import com.depo.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO){
		
		ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);
		
		return ResponseEntity.ok(productResponseDTO);
		
	}

}
