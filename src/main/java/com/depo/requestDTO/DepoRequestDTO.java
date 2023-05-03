package com.depo.requestDTO;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepoRequestDTO {

	
	private String title;
	
	
	private String address;
	
	@NotNull(message = "Please enter the homes description")
    @Enumerated(EnumType.STRING)
	private String state;
	
	
	private String city;

	
	private byte status = 0;

	
	private Boolean builtIn = false;
}
