package com.depo.requestDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	@Size(min = 2, max = 30)
	@NotNull
	@NotBlank(message = "Please provide first name")
	private String first_name;

	@Size(min = 2, max = 30)
	@NotNull
	@NotBlank(message = "Please provide last name")
	private String last_name;

	@NotNull
	@NotBlank(message = "Please provide phone number")
	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}", message = "Please enter valid phone number") // regex yanlış olabilir.
	private String phone;

	@NotNull
	@NotBlank(message = "Please provide an e-mail")
	@Size(min = 10, max = 80)
	@Email(message = "Please enter a valid e-mail")
	private String email;

	@NotNull
	@NotBlank(message = "Please provide a password")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$")
	private String password;

	@Max(1)
	@Min(0)
	private byte status;
}
