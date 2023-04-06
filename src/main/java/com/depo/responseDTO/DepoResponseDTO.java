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
public class DepoResponseDTO {

	private Long id;

	private String title;

	private String address;

	private String state;

	private String city;

	private byte status;

	private Boolean builtIn;

	private LocalDate create_at;

	private LocalDate update_at;
}
