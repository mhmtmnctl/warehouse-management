package com.depo.service.ServiceInterfaces;

import com.depo.domain.Category;
import com.depo.requestDTO.CategoryRequestDTO;
import com.depo.responseDTO.CategoryResponseDTO;

public interface ICategoryService {

	CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

	CategoryResponseDTO getCategory(Long categoryId);

	CategoryResponseDTO updateCategory(Long categoryId, CategoryRequestDTO categoryRequestDTO);

	CategoryResponseDTO deleteCategory(Long categoryId);

	Category getCategoryMethod(Long categoryId);

}
