package com.learning.core.models;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.learning.core.enums.Icon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Job {

	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(nullable = false)
	private BigDecimal minAmount;

	@Column(nullable = false)
	private Integer qtyHours;

	@Column(nullable = false)
	private BigDecimal percentComission;

	@Column(nullable = false)
	private Integer roomHours;

	@Column(nullable = false)
	private BigDecimal roomAmount;

	@Column(nullable = false)
	private Integer livingHours;

	@Column(nullable = false)
	private BigDecimal livingAmount;

	@Column(nullable = false)
	private Integer bathroomHours;

	@Column(nullable = false)
	private BigDecimal bathroomAmount;

	@Column(nullable = false)
	private Integer kitchenHours;

	@Column(nullable = false)
	private BigDecimal kitchenAmount;

	@Column(nullable = false)
	private Integer yardHours;

	@Column(nullable = false)
	private BigDecimal yardAmount;

	@Column(nullable = false)
	private Integer othersHours;

	@Column(nullable = false)
	private BigDecimal othersAmount;

	@Column(nullable = false, length = 14)
	@Enumerated(EnumType.STRING)
	private Icon icon;

	@Column(nullable = false)
	private Integer position;

}
