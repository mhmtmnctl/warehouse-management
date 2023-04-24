package com.depo.requestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

	@NotNull
	@NotBlank(message = "Please provide an e-mail")
	@Size(min = 10, max = 80)
	@Email(message = "Please enter a valid e-mail")
	private String email;

	@NotNull
	@NotBlank(message = "Please provide a password")
	//@Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$", message = "please enter a valid password")
	private String password;

}
