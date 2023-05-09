package com.depo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.depo.domain.Product;
import com.depo.requestDTO.ProductRequestDTO;
import com.depo.responseDTO.ProductResponseDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="create_at", ignore=true)
	@Mapping(target="update_at", ignore=true)
	@Mapping(target="category", ignore=true)
	@Mapping(target="depo", ignore=true)
	Product productRequsetDTOToProduct(ProductRequestDTO productRequestDTO );
	
	
	@Mapping(target="categoryTitle", ignore=true)
	@Mapping(target="depoCity", ignore=true)
	@Mapping(target="depoState", ignore=true)
	@Mapping(target="depoTitle", ignore=true)
	ProductResponseDTO productToProductResponseDTO(Product product);

}
