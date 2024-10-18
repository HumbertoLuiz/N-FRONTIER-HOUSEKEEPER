package br.com.xfrontier.housekeeper.api.dtos.responses;

import java.util.Objects;

public class AvailabilityResponse {

	private Boolean availability;

	public AvailabilityResponse() {}
	
	public AvailabilityResponse(Boolean availability) {
		this.availability = availability;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availability);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvailabilityResponse other = (AvailabilityResponse) obj;
		return Objects.equals(availability, other.availability);
	}
	
}
