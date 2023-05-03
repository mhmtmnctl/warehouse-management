package com.depo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.depo.domain.Depo;
import com.depo.requestDTO.DepoRequestDTO;
import com.depo.responseDTO.DepoResponseDTO;

@Mapper(componentModel = "spring")
public interface DepoMapper {

	@Mapping(target="create_at", ignore=true)
	@Mapping(target="update_at", ignore=true)
	@Mapping(target="id", ignore=true)
	Depo depoRequsetDTOToDepo(DepoRequestDTO depoRequestDTO );

	
	
	DepoResponseDTO depoToDepoResponseDTO(Depo depo);
}
