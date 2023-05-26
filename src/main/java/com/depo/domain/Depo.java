package com.depo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Depo extends BaseEntity {

	@Column
	private String title;

	@Column
	private String address;

	@Column
	private String state;

	@Column
	private String city;



}
