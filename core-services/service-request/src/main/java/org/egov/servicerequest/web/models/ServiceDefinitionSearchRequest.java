package org.egov.servicerequest.web.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * ServiceDefinitionSearchRequest
 */
@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDefinitionSearchRequest {
    @JsonProperty("requestInfo")
    @NotNull
    @Valid
    private RequestInfo requestInfo = null;

    @JsonProperty("serviceDefinitionCriteria")
    @Valid
    private ServiceDefinitionCriteria serviceDefinitionCriteria = null;

    @JsonProperty("pagination")
    @Valid
    private Pagination pagination = null;


}