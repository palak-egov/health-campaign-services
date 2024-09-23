package digit.service;

import digit.service.validator.PlanFacilityValidator;
import digit.web.models.PlanFacilityRequest;
import digit.web.models.PlanFacilityResponse;
import org.springframework.stereotype.Service;

@Service
public class PlanFacilityService
{
    private PlanFacilityValidator planFacilityValidator;

    public PlanFacilityService(PlanFacilityValidator planFacilityValidator)
    {
        this.planFacilityValidator = planFacilityValidator;
    }

    /**
     * This method processes the requests that come for updating plans facility
     * @param body
     * @return The response containing the updated plan facility.
     */
    public PlanFacilityResponse updatePlanFacility(PlanFacilityRequest body)
    {
        planFacilityValidator.validatePlanFacilityUpdate(body);
        return null;
    }
}

