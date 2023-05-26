package com.depo.service.ServiceInterfaces;

import com.depo.domain.Product;
import com.depo.requestDTO.ProductRequestDTO;
import com.depo.responseDTO.ProductResponseDTO;

public interface IProductService {
	
	ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
	ProductResponseDTO getProduct(Long productId);
	ProductResponseDTO updateProduct( ProductRequestDTO productRequestDTO, Long productId);
	ProductResponseDTO deleteProduct(Long productId);
	ProductResponseDTO getProductResponseDTO(ProductRequestDTO productRequestDTO);
	Product getProductMethod(Long productId);
	ProductResponseDTO getProductResponseDTO(Product product);
	
	
	
	
	

}
