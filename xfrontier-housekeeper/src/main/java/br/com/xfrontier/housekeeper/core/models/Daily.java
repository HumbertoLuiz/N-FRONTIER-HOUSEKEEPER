package br.com.xfrontier.housekeeper.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.xfrontier.housekeeper.core.enums.DailyStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "daily")
public class Daily extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "date_service", nullable = false)
    private LocalDateTime dateService;

    @Column(name = "time_service", nullable = false)
    private Integer timeService;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DailyStatus status;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "value_commission", nullable = false)
    private BigDecimal valueCommission;

    @Column(nullable = false, length = 60)
    private String address;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 30)
    private String neighborhood;

    @Column(nullable = true, length = 100)
    private String complement;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "ibge_code", nullable = false)
    private String ibgeCode;

    @Column(name = "quantity_bedrooms", nullable = false)
    private Integer quantityBedrooms;

    @Column(name = "quantity_rooms", nullable = false)
    private Integer quantityRooms;

    @Column(name = "quantity_kitchens", nullable = false)
    private Integer quantityKitchens;

    @Column(name = "quantity_bathrooms", nullable = false)
    private Integer quantityBathrooms;

    @Column(name = "quantity_yards", nullable = false)
    private Integer quantityYards;

    @Column(name = "quantity_others", nullable = false)
    private Integer quantityOthers;

    @Column(nullable = true)
    private String observations;

    @Column(name = "reason_cancellation" ,nullable = true)
    private String reasonCancellation;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "housekeeper_id", nullable = true)
    private User housekeeper;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToMany
    @JoinTable(
        name = "daily_applicant",
        joinColumns = @JoinColumn(name = "daily_id"),
        inverseJoinColumns = @JoinColumn(name = "applicant_id")
    )
    private List<User> applicants;

    @OneToMany(mappedBy = "daily")
    private List<Payment> payments;

    public Boolean isNoPayment() {
        return status.equals(DailyStatus.NO_PAYMENT);
    }

    public Boolean isPaid() {
        return status.equals(DailyStatus.PAID);
    }

    public Boolean isConfirmed() {
        return status.equals(DailyStatus.CONFIRMED);
    }

    public Boolean isConcluded() {
        return status.equals(DailyStatus.CONCLUDED);
    }

}
