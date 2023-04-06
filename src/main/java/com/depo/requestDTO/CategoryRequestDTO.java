package com.depo.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

	private String title;

	private byte status = 0;

	private Boolean builtIn = false;

}
