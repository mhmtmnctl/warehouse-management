package com.depo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depo.domain.Category;
import com.depo.domain.Depo;
import com.depo.domain.Product;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.ProductMapper;
import com.depo.repository.CategoryRepository;
import com.depo.repository.DepoRepository;
import com.depo.repository.ProductRepository;
import com.depo.requestDTO.ProductRequestDTO;
import com.depo.responseDTO.ProductResponseDTO;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private DepoRepository depoRepository;

	public ProductResponseDTO createProduct( ProductRequestDTO productRequestDTO) {
		
		Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow( ()-> 
			new ResourceNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE));
		
		//List<Depo> depoList = depoRepository.findById(productRequestDTO.getDepoId()).orElseThrow( () ->
	//		new ResourceNotFoundException(ErrorMessage.DEPO_NOT_FOUND_MESSAGE));
		
		Product product = productMapper.productRequsetDTOToProduct(productRequestDTO);
		
		product.setCreate_at(LocalDate.now());
		product.setCategory(category);
		//product.setDepo(depoList);
		

		
		return null;
	}

}
