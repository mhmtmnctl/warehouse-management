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
import com.depo.requestDTO.CategoryRequestDTO;
import com.depo.responseDTO.CategoryResponseDTO;
import com.depo.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;

	// CREATE CATEGORY
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponseDTO> createCategory(
			@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {

		CategoryResponseDTO categoryResponseDTO = categoryService.createCategory(categoryRequestDTO);

		return ResponseEntity.ok(categoryResponseDTO);
	}

	// GET CATEGORY
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id") Long categoryId) {

		CategoryResponseDTO categoryResponseDTO = categoryService.getCategory(categoryId);

		return ResponseEntity.ok(categoryResponseDTO);

	}

	// UPDATE CATEGORY
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponseDTO> updateCategory(@Valid @PathVariable("id") Long categoryId,
			@RequestBody CategoryRequestDTO categoryRequestDTO) {

		CategoryResponseDTO categoryResponseDTO = categoryService.updateCategory(categoryId,categoryRequestDTO);
		
		return ResponseEntity.ok(categoryResponseDTO);
	}
	
	//DELETE CATEGORY
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable("id") Long categoryId){
		
		CategoryResponseDTO categoryResponseDTO = categoryService.deleteCategory(categoryId);
		
		return ResponseEntity.ok(categoryResponseDTO);
		
	}

}
