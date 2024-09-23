package digit.web.controllers;


import digit.service.PlanFacilityService;
import digit.web.models.PlanResponse;
import digit.web.models.PlanSearchRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("plan")
public class PlanFacilityController {
    @Autowired
    private PlanFacilityService planFacilityService;
    @RequestMapping(value = "/facility/_search", method = RequestMethod.POST)
    public ResponseEntity<PlanResponse> searchPost(@Valid @RequestBody PlanSearchRequest body) {
        PlanResponse planResponse = planFacilityService.searchPlan(body);
        return ResponseEntity.status(HttpStatus.OK).body(planResponse);
    }
}
