package com.depo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.depo.domain.Depo;
import com.depo.exception.BuiltInException;
import com.depo.exception.ResourceNotFoundException;
import com.depo.exception.message.ErrorMessage;
import com.depo.mapper.DepoMapper;
import com.depo.repository.DepoRepository;
import com.depo.requestDTO.DepoRequestDTO;
import com.depo.responseDTO.DepoResponseDTO;

@Service
public class DepoServiceImpl implements IDepoService {

	@Autowired
	private DepoRepository depoRepository;

	@Autowired
	private DepoMapper depoMapper;

	// CREATE WAREHOUSE
	@Override
	public DepoResponseDTO createWarehouse(DepoRequestDTO depoRequestDTO) {

		Depo depo = depoRepository.save(depoMapper.depoRequsetDTOToDepo(depoRequestDTO));
		depo.setCreate_at(LocalDate.now());

		depoRepository.save(depo);

		DepoResponseDTO depoResponseDTO = depoMapper.depoToDepoResponseDTO(depo);

		return depoResponseDTO;
	}

	// GET WAREHOUSE
	@Override
	public DepoResponseDTO getWarehouse(Long depoId) {

		Depo depo = getDepoMethod(depoId);

		DepoResponseDTO depoResponseDTO = depoMapper.depoToDepoResponseDTO(depo);

		return depoResponseDTO;
	}
	@Override
	public Depo getDepoMethod(Long depoId) {

		Depo depo = depoRepository.findById(depoId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.DEPO_NOT_FOUND_MESSAGE));

		return depo;
	}

	// UPDATE WAREHOUSE
	@Override
	public DepoResponseDTO updateWarehouse( Long depoId, DepoRequestDTO depoRequestDTO) {

		Depo depo = getDepoMethod(depoId);

		if (depo != null && depo.getBuiltIn()) {
			throw new ResponseStatusException(new BuiltInException(ErrorMessage.BUILTIN_MESSAGE).getHttpStatus(),
					ErrorMessage.BUILTIN_MESSAGE);
		}

		depo.setAddress(depoRequestDTO.getAddress());
		depo.setBuiltIn(depoRequestDTO.getBuiltIn());
		depo.setCity(depoRequestDTO.getCity());
		depo.setState(depoRequestDTO.getState());
		depo.setStatus(depoRequestDTO.getStatus());
		depo.setTitle(depoRequestDTO.getTitle());
		depo.setUpdate_at(LocalDate.now());

		depoRepository.save(depo);

		DepoResponseDTO depoResponseDTO = depoMapper.depoToDepoResponseDTO(depo);

		return depoResponseDTO;
	}

	// DELETE WAREHOUSE
	@Override
	public DepoResponseDTO deleteWarehouse(Long depoId) {

		Depo depo = getDepoMethod(depoId);

		if (depo != null && depo.getBuiltIn()) {
			throw new ResponseStatusException(new BuiltInException(ErrorMessage.BUILTIN_MESSAGE).getHttpStatus(),
					ErrorMessage.BUILTIN_MESSAGE);
		}

		depoRepository.delete(depo);

		DepoResponseDTO depoResponseDTO = depoMapper.depoToDepoResponseDTO(depo);

		return depoResponseDTO;
	}

}
