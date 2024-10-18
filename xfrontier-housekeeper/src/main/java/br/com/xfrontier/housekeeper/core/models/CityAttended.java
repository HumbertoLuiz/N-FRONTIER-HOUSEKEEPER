package br.com.xfrontier.housekeeper.core.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "city_attended")
public class CityAttended extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(name = "ibge_code", nullable = false)
    private String ibgeCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @ManyToMany(mappedBy = "citiesAttended")
    private List<User> users;

}
