package com.depo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.depo.domain.Category;
import com.depo.domain.Depo;
import com.depo.domain.Product;
import com.depo.domain.Transaction;
import com.depo.exception.BuiltInException;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.ProductMapper;
import com.depo.repository.CategoryRepository;
import com.depo.repository.DepoRepository;
import com.depo.repository.ProductRepository;
import com.depo.repository.TransactionRepository;
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

	@Autowired
	private TransactionRepository transactionRepository;

	// CREATE PRODUCT
	public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

		return getProductResponseDTO(productRequestDTO);
	}

	// GET PRODUCT
	public ProductResponseDTO getProduct(Long productId) {

		Product product = getProductMethod(productId);

		ProductResponseDTO productResponseDTO = getProductResponseDTO(product);

		return productResponseDTO;
	}

	// UPDATE PRODUCT
	public ProductResponseDTO updateProduct(@Valid ProductRequestDTO productRequestDTO, Long productId) {

		Product product = getProductMethod(productId);

		List<Long> depoIdList = productRequestDTO.getDepoIds();
		List<Depo> depoList = new ArrayList<>();

		for (Long depo : depoIdList) {

			depoList.add(depoRepository.findById(depo)
					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DEPO_NOT_FOUND_MESSAGE)));

		}

		Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE));

		product.setBuiltIn(productRequestDTO.getBuiltIn());
		product.setStatus(productRequestDTO.getStatus());
		product.setStock_alarm_limit(productRequestDTO.getStock_alarm_limit());
		product.setStock_amount(productRequestDTO.getStock_amount());
		product.setTitle(productRequestDTO.getTitle());
		product.setUpdate_at(LocalDate.now());
		product.setCategory(category);
		product.setDepo(depoList);

		productRepository.save(product);

		Transaction transaction = new Transaction();

		transaction.setCategory(category);
		transaction.setCreate_at(LocalDate.now());
		transaction.setTransaction("product updated");

		transactionRepository.save(transaction);

		ProductResponseDTO productResponseDTO = getProductResponseDTO(product);

		return productResponseDTO;
	}
	
	//DELETE PRODUCT
		public ProductResponseDTO deleteProduct(Long productId) {

			Product product = productRepository.findByIdWithCategories(productId);

			if (product != null && product.getBuiltIn()) {
				throw new ResponseStatusException(new BuiltInException(ErrorMessage.BUILTIN_MESSAGE).getHttpStatus(),
						ErrorMessage.BUILTIN_MESSAGE);
			}

			ProductResponseDTO productResponseDTO = getProductResponseDTO(product);
			productRepository.delete(product);

	 
			return productResponseDTO;
		}

	// Product to ProductResponseDTO Method
	public ProductResponseDTO getProductResponseDTO(ProductRequestDTO productRequestDTO) {

		List<Long> depoIdList = productRequestDTO.getDepoIds();
		List<Depo> depoList = new ArrayList<>();

		for (Long depo : depoIdList) {

			depoList.add(depoRepository.findById(depo)
					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DEPO_NOT_FOUND_MESSAGE)));

		}

		Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE));

		Product product = productMapper.productRequsetDTOToProduct(productRequestDTO);

		product.setCreate_at(LocalDate.now());
		product.setCategory(category);
		product.setDepo(depoList);

		productRepository.save(product);

		ProductResponseDTO productResponseDTO = getProductResponseDTO(product);

		Transaction transaction = new Transaction();

		transaction.setCategory(category);
		transaction.setCreate_at(LocalDate.now());
		transaction.setTransaction("product created");

		transactionRepository.save(transaction);

		return productResponseDTO;

	}

	// Get Product Method
	public Product getProductMethod(Long productId) {

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE));
		;
		return product;
	}

	// ProductTOProductResponseDTO method
	public ProductResponseDTO getProductResponseDTO(Product product) {

		List<String> depoCities = new ArrayList<>();
		List<String> depoTitles = new ArrayList<>();
		List<String> depoStates = new ArrayList<>();

		for (Depo w : product.getDepo()) {
			depoCities.add(w.getCity());
			depoTitles.add(w.getTitle());
			depoStates.add(w.getState());
		}
		ProductResponseDTO productResponseDTO = productMapper.productToProductResponseDTO(product);
		productResponseDTO.setCategoryTitle(product.getCategory().getTitle());
		productResponseDTO.setDepoCity(depoCities);
		productResponseDTO.setDepoTitle(depoTitles);
		productResponseDTO.setDepoState(depoStates);
		productResponseDTO.setCategoryTitle(product.getCategory().getTitle());

		return productResponseDTO;

	}

	

}
