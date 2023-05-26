package com.depo.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDate create_at;

	@Column
	private LocalDate update_at;

	@Column
	private Boolean builtIn = false;

	@Column
	private byte status = 0;

}
