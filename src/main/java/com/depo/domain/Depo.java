package com.depo.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_depo")
public class Depo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;
	
	@Column
	private String address;
	
	@Column
	private String state;
	
	@Column
	private String city;

	@Column
	private byte status = 0;

	@Column
	private Boolean builtIn = false;

	@Column
	private LocalDate create_at;

	@Column
	private LocalDate update_at;
}
