package com.learning.core.models;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
@Table(name = "jobs")
public class Job {

	@EqualsAndHashCode.Include
	@ToString.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(name = "min_amount", nullable = false)
	private BigDecimal minAmount;

	@Column(name = "qty_hours", nullable = false)
	private Integer qtyHours;

	@Column(name = "percent_comission", nullable = false)
	private BigDecimal percentComission;

	@Column(name = "room_hours", nullable = false)
	private Integer roomHours;

	@Column(name = "room_amount", nullable = false)
	private BigDecimal roomAmount;

	@Column(name = "living_hours", nullable = false)
	private Integer livingHours;

	@Column(name = "living_amount", nullable = false)
	private BigDecimal livingAmount;

	@Column(name = "bathroom_hours", nullable = false)
	private Integer bathroomHours;

	@Column(name = "bathroom_amount", nullable = false)
	private BigDecimal bathroomAmount;

	@Column(name = "kitchen_hours", nullable = false)
	private Integer kitchenHours;

	@Column(name = "kitchen_amount", nullable = false)
	private BigDecimal kitchenAmount;

	@Column(name = "yard_hours", nullable = false)
	private Integer yardHours;

	@Column(name = "yard_amount", nullable = false)
	private BigDecimal yardAmount;

	@Column(name = "others_hours", nullable = false)
	private Integer othersHours;

	@Column(name = "others_amount", nullable = false)
	private BigDecimal othersAmount;

	@Column(nullable = false, length = 14)
	@Enumerated(EnumType.STRING)
	private Icon icon;

	@Column(nullable = false)
	private Integer position;

}
