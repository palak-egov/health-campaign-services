package digit.service.validator;

import digit.repository.PlanConfigurationRepository;
import digit.repository.PlanFacilityRepository;
import digit.web.models.*;
import org.egov.tracer.model.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import static digit.config.ServiceConstants.*;

import java.util.Collections;

@Component
public class PlanFacilityValidator
{
    private PlanFacilityRepository planFacilityRepository;
    private PlanConfigurationRepository planConfigurationRepository;

    public PlanFacilityValidator(PlanFacilityRepository planFacilityRepository)
    {
        this.planFacilityRepository=planFacilityRepository;
        this.planConfigurationRepository = planConfigurationRepository;
    }

    public void validatePlanFacilityUpdate(PlanFacilityRequest body)
    {
        //validate plan facility existence
        validatePlanFacilityExistence(body);

        // Validate plan configuration existence
        validatePlanConfigExistence(body);
    }

    /**
     * This method validates if the plan facility id provided in the update request exists
     * @param body
     */
    private void validatePlanFacilityExistence(PlanFacilityRequest body) {
        // If plan facility id provided is invalid, throw an exception
        if(CollectionUtils.isEmpty(planFacilityRepository.search(PlanFacilitySearchCriteria.builder()
                .ids(Collections.singleton(body.getPlanFacility().getId()))
                .build()))) {
            throw new CustomException(INVALID_PLAN_FACILITY_ID_CODE, INVALID_PLAN_FACILITY_ID_MESSAGE);
        }
    }

    /**
     * This method validates if the plan configuration id provided in the request exists
     * @param body
     */
    public void validatePlanConfigExistence(PlanFacilityRequest body) {
        // If plan config id provided is invalid, throw an exception
        if(!ObjectUtils.isEmpty(body.getPlanFacility().getPlanConfigurationId()) && CollectionUtils.isEmpty(planConfigurationRepository.search(PlanConfigurationSearchCriteria.builder()
                .id(body.getPlanFacility().getPlanConfigurationId())
                .tenantId(body.getPlanFacility().getTenantId())
                .build()))) {
            throw new CustomException(INVALID_PLAN_CONFIG_ID_CODE, INVALID_PLAN_CONFIG_ID_MESSAGE);
        }
    }

}
