package com.learning.web.dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.learning.core.enums.Icon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobForm {

	@NotBlank
	@Size(min = 3, max = 50)
	private String name;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal minAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer qtyHours;
	
    @NotNull
    @PositiveOrZero
    @Max(100)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal percentComission;
	
    @NotNull
    @PositiveOrZero
	private Integer roomHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal roomAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer livingHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal livingAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer bathroomHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal bathroomAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer kitchenHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")    
	private BigDecimal kitchenAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer yardHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal yardAmount;
	
    @NotNull
    @PositiveOrZero
	private Integer othersHours;
	
    @NotNull
    @PositiveOrZero
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal othersAmount;
	
    @NotNull
	private Icon icon;
	
    @NotNull
    @Positive
	private Integer position;
}
