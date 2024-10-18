package br.com.xfrontier.housekeeper.api.dtos.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class HousekeeperLocationPagedResponse {

	private List<HousekeeperLocationResponse> housekeeper;

	private Long quantityHousekeeper;

	public HousekeeperLocationPagedResponse(List<HousekeeperLocationResponse> housekeeper,
			Integer pageSize, Long totalElements) {
		this.housekeeper = housekeeper;
		this.quantityHousekeeper = totalElements > pageSize ? totalElements - pageSize : 0;
	}
}
