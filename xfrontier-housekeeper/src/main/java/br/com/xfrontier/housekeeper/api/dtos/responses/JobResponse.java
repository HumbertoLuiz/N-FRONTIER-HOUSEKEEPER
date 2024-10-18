package br.com.xfrontier.housekeeper.api.dtos.responses;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class JobResponse {

	private Long id;

	private String name;

	private BigDecimal minAmount;

	private Integer qtyHours;

	private BigDecimal percentComission;

	private Integer roomHours;

	private BigDecimal roomAmount;

	private Integer livingHours;

	private BigDecimal livingAmount;

	private Integer bathroomHours;

	private BigDecimal bathroomAmount;

	private Integer kitchenHours;

	private BigDecimal kitchenAmount;

	private Integer yardHours;

	private BigDecimal yardAmount;

	private Integer othersHours;

	private BigDecimal othersAmount;

	private String icon;

	private Integer position;
}
