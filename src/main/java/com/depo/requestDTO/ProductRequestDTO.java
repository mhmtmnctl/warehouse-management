package com.depo.requestDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

	private String title;

	private Integer stock_amount;

	private Integer stock_alarm_limit;

	private byte status = 0;

	private Boolean builtIn = false;

	private Long categoryId;

	private List<Long> depoIds;

}
