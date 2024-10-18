package br.com.xfrontier.housekeeper.api.dtos.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class ErrorResponse extends HateoasResponse {

	private Integer status;

	private LocalDateTime timestamp;

	private String message;

	private String path;
}
