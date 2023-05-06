package com.depo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.depo.domain.Product;
import com.depo.requestDTO.ProductRequestDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	
	@Mapping(target="id", ignore=true)
	@Mapping(target="create_at", ignore=true)
	@Mapping(target="update_at", ignore=true)
	@Mapping(target="category", ignore=true)
	@Mapping(target="depo", ignore=true)
	Product productRequsetDTOToProduct(ProductRequestDTO productRequestDTO );

}
