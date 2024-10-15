package com.learning.core.models;

import java.io.Serializable;
import java.math.BigDecimal;

import com.learning.core.enums.Icon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "jobs")
public class Job extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(name = "min_amount", nullable = false)
	private BigDecimal minAmount;

	@Column(name = "qty_hours", nullable = false)
	private Integer qtyHours;

	@Column(name = "percent_comission", nullable = false)
	private BigDecimal percentComission;

	@Column(name = "bedroom_hours", nullable = false)
	private Integer bedroomHours;

	@Column(name = "bedroom_amount", nullable = false)
	private BigDecimal bedroomAmount;

	@Column(name = "room_hours", nullable = false)
	private Integer roomHours;

	@Column(name = "room_amount", nullable = false)
	private BigDecimal roomAmount;

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
