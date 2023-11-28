package com.depo.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity{

	@Column
	private String title;

	@Column
	private Integer stock_amount;
	
	@Column
	private Integer stock_alarm_limit;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany
	@JoinColumn(name = "product_id")
	private List<Depo> depo;

}