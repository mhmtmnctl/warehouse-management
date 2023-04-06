package com.depo.responseDTO;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

	
	
	private Long id;

	
	private String title;

	
	private byte status;

	
	private Boolean builtIn;

	
	private LocalDate create_at;

	
	private LocalDate update_at;
}
