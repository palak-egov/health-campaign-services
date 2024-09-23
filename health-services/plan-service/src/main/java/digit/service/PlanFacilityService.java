package digit.service;

import digit.repository.PlanRepository;
import digit.web.models.Plan;
import digit.web.models.PlanResponse;
import digit.web.models.PlanSearchRequest;
import org.egov.common.utils.ResponseInfoUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanFacilityService {

    private PlanRepository planRepository;

    public PlanFacilityService(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    /**
     * This method processes the requests that come for searching plans.
     * @param body
     * @return
     */
    public PlanResponse searchPlan(PlanSearchRequest body) {
        // Delegate search request to repository
        List<Plan> planList = planRepository.search(body.getPlanSearchCriteria());

        // Build and return response back to controller
        return PlanResponse.builder()
                .responseInfo(ResponseInfoUtil.createResponseInfoFromRequestInfo(body.getRequestInfo(), Boolean.TRUE))
                .plan(planList)
                .build();
    }
}
