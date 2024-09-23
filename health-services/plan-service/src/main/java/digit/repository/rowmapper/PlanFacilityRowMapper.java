package digit.repository.rowmapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import digit.web.models.PlanFacility;
import org.egov.common.contract.models.AuditDetails;
import org.egov.tracer.model.CustomException;
import org.postgresql.util.PGobject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Component
public class PlanFacilityRowMapper implements ResultSetExtractor<List<PlanFacility>> {

    private ObjectMapper objectMapper;

    public PlanFacilityRowMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PlanFacility> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, PlanFacility> planFacilityMap = new LinkedHashMap<>();
        while(rs.next()){
            String planFacilityId = rs.getString("plan_id");

            PlanFacility planFacilityEntry = planFacilityMap.get(planFacilityId);
            if(ObjectUtils.isEmpty(planFacilityEntry)){
                planFacilityEntry = new PlanFacility();

                // Prepare audit details
                AuditDetails auditDetails = AuditDetails.builder()
                        .createdBy(rs.getString("created_by"))
                        .createdTime(rs.getLong("created_time"))
                        .lastModifiedBy(rs.getString("last_modified_by"))
                        .lastModifiedTime(rs.getLong("last_modified_time"))
                        .build();

                // Prepare plan facility object
                planFacilityEntry.setId(planFacilityId);
                planFacilityEntry.setTenantId(rs.getString("tenant_id"));
                planFacilityEntry.setPlanConfigurationId(rs.getString("plan_configuration_id"));
                planFacilityEntry.setAdditionalDetails(getAdditionalDetail((PGobject) rs.getObject("additional_details")));
                planFacilityEntry.setAuditDetails(auditDetails);
            }

            planFacilityMap.put(planFacilityId, planFacilityEntry);
        }
        return new ArrayList<>(planFacilityMap.values());
    }

    private JsonNode getAdditionalDetail(PGobject pGobject){
        JsonNode additionalDetail = null;

        try {
            if(ObjectUtils.isEmpty(pGobject)){
                additionalDetail = objectMapper.readTree(pGobject.getValue());
            }
        }
        catch (IOException e){
            throw new CustomException("PARSING_ERROR", "Failed to parse additionalDetails object");
        }

        return additionalDetail;
    }
}
