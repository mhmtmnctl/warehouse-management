package com.depo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	// CREATE PRODUCT
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {

		ProductResponseDTO productResponseDTO = productService.createProduct(productRequestDTO);

		return ResponseEntity.ok(productResponseDTO);

	}

	// GET PRODUCT BY ID
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
	public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable("id") Long productId) {

		ProductResponseDTO productResponseDTO = productService.getProduct(productId);

		return ResponseEntity.ok(productResponseDTO);
	}

	// UPDATE PRODUCT
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO,
			@PathVariable("id") Long productId) {

		ProductResponseDTO productResponseDTO = productService.updateProduct(productRequestDTO, productId);
		return ResponseEntity.ok(productResponseDTO);

	}
	
	//DELETE PRODUCT
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ProductResponseDTO> deleteProduct(@PathVariable("id") Long productId){
		
		ProductResponseDTO productResponseDTO = productService.deleteProduct(productId);

		return ResponseEntity.ok(productResponseDTO);
		
	}

}
