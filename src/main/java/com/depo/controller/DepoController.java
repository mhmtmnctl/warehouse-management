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

import com.depo.requestDTO.DepoRequestDTO;
import com.depo.responseDTO.DepoResponseDTO;
import com.depo.service.DepoService;

@RestController
@RequestMapping("/warehouse")
public class DepoController {

	@Autowired
	private DepoService depoService;

	// CREATE WAREHOUSE
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepoResponseDTO> createWarehouse(@Valid @RequestBody DepoRequestDTO depoRequestDTO) {

		DepoResponseDTO depoResponseDTO = depoService.createWarehouse(depoRequestDTO);

		return ResponseEntity.ok(depoResponseDTO);

	}

	// GET WAREHOUSE
	@GetMapping("/get/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepoResponseDTO> getWareHouse(@PathVariable("id") Long depoId) {

		DepoResponseDTO depoResponseDTO = depoService.getWarehouse(depoId);

		return ResponseEntity.ok(depoResponseDTO);

	}

	// UPDATE WAREHOUSE
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepoResponseDTO> updateWarehouse(@Valid @PathVariable("id") Long depoId,
			@RequestBody DepoRequestDTO depoRequestDTO) {

		DepoResponseDTO depoResponseDTO = depoService.updateWarehouse(depoId, depoRequestDTO);

		return ResponseEntity.ok(depoResponseDTO);

	}

	// DELETE WAREHOUSE
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<DepoResponseDTO> deleteWarehouse(@PathVariable("id") Long depoId) {

		DepoResponseDTO depoResponseDTO = depoService.deleteWarehouse(depoId);

		return ResponseEntity.ok(depoResponseDTO);

	}

}
