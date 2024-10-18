package br.com.xfrontier.housekeeper.core.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "token_black_list")
public class TokenBlackList extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(nullable= false)
    private String token;
}
