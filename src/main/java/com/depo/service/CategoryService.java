package com.depo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.depo.domain.Category;
import com.depo.exception.BuiltInException;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.CategoryMapper;
import com.depo.repository.CategoryRepository;
import com.depo.requestDTO.CategoryRequestDTO;
import com.depo.responseDTO.CategoryResponseDTO;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	// CREATE CATEGORY
	public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {

		Category category = categoryMapper.categoryRequsetDTOToCategory(categoryRequestDTO);
		category.setCreate_at(LocalDate.now());
		categoryRepository.save(category);
		CategoryResponseDTO categoryResponseDTO = categoryMapper.categoryToCategoryResponseDTO(category);

		return categoryResponseDTO;
	}

	// GET CATEGORY
	public CategoryResponseDTO getCategory(Long categoryId) {

		Category category = getCategoryMethod(categoryId);

		CategoryResponseDTO categoryResponseDTO = categoryMapper.categoryToCategoryResponseDTO(category);

		return categoryResponseDTO;
	}

	// UPDATE CATEGORY
	public CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO) {

		Category category = getCategoryMethod(categoryId);

		if (category != null && category.getBuiltIn()) {
			throw new ResponseStatusException(new BuiltInException(ErrorMessage.BUILTIN_MESSAGE).getHttpStatus(),
					ErrorMessage.BUILTIN_MESSAGE);
		}

		category.setStatus(categoryRequestDTO.getStatus());
		category.setTitle(categoryRequestDTO.getTitle());
		category.setBuiltIn(categoryRequestDTO.getBuiltIn());
		category.setUpdate_at(LocalDate.now());

		categoryRepository.save(category);

		CategoryResponseDTO categoryResponseDTO = categoryMapper.categoryToCategoryResponseDTO(category);

		return categoryResponseDTO;
	}

	

	// DELETE CATEGORY
	public CategoryResponseDTO deleteCategory(Long categoryId) {

		Category category = getCategoryMethod(categoryId);

		if (category != null && category.getBuiltIn()) {
			throw new ResponseStatusException(new BuiltInException(ErrorMessage.BUILTIN_MESSAGE).getHttpStatus(),
					ErrorMessage.BUILTIN_MESSAGE);
		}

		categoryRepository.delete(category);

		CategoryResponseDTO categoryResponseDTO = categoryMapper.categoryToCategoryResponseDTO(category);

		return categoryResponseDTO;
	}
	
	// Find Category Method
		public Category getCategoryMethod(Long categoryId) {

			Category category = categoryRepository.findById(categoryId)
					.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE));
			return category;

		}

}
