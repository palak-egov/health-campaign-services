package org.egov.transformer.models.pgr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.egov.common.contract.request.RequestInfo;

import javax.validation.Valid;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestInfoWrapper {

	@NonNull
	@Valid
	@JsonProperty("RequestInfo")
	private RequestInfo requestInfo;
}
