package com.depo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.depo.domain.Category;
import com.depo.requestDTO.CategoryRequestDTO;
import com.depo.responseDTO.CategoryResponseDTO;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

	
	
	@Mapping(target="create_at", ignore=true)
	@Mapping(target="update_at", ignore=true)
	@Mapping(target="id", ignore=true)
	Category categoryRequsetDTOToCategory(CategoryRequestDTO categoryRequestDTO );
	
	CategoryResponseDTO categoryToCategoryResponseDTO(Category category);
}
