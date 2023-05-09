package com.depo.responseDTO;

import java.time.LocalDate;
import java.util.List;

import com.depo.enums.DepoEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

	private Long id;

	private String title;

	private Integer stock_amount;

	private Integer stock_alarm_limit;

	private byte status;

	private Boolean builtIn;

	private LocalDate create_at;

	private LocalDate update_at;

	private String categoryTitle;

	private List<String>  depoTitle;

	private List<String>  depoCity;

	private List<String>  depoState;
}
