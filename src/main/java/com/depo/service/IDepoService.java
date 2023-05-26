package com.depo.service;

import com.depo.domain.Depo;
import com.depo.requestDTO.DepoRequestDTO;
import com.depo.responseDTO.DepoResponseDTO;

public interface IDepoService {
	
	DepoResponseDTO createWarehouse(DepoRequestDTO depoRequestDTO);
	DepoResponseDTO getWarehouse(Long depoId);
	Depo getDepoMethod(Long depoId);
	DepoResponseDTO updateWarehouse( Long depoId, DepoRequestDTO depoRequestDTO);
	DepoResponseDTO deleteWarehouse(Long depoId);
	
	
	
}
