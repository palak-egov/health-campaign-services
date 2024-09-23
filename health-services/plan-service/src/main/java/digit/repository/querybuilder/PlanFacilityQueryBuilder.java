package digit.repository.querybuilder;

import digit.config.Configuration;
import digit.util.QueryUtil;
import digit.web.models.PlanFacilitySearchCriteria;
import digit.web.models.PlanSearchCriteria;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashSet;
import java.util.List;

@Component
public class PlanFacilityQueryBuilder {

    private Configuration config;

    public PlanFacilityQueryBuilder(Configuration config) {
        this.config = config;
    }

    private static final String PLAN_FACILITY_SEARCH_BASE_QUERY = "SELECT id FROM plan_facility_linkage ";

    private static final String PLAN_FACILITY_QUERY = "SELECT plan_facility_linkage.id as plan_facility_id, plan_facility_linkage.tenant_id as plan_facility_tenant_id, plan_facility_linkage.plan_configuration_id as plan_facility_plan_configuration_id, plan_facility_linkage.additional_details as plan_facility_additional_details, plan.created_by as plan_created_by, plan.created_time as plan_created_time, plan.last_modified_by as plan_last_modified_by, plan.last_modified_time as plan_last_modified_time,\n" +
            "\t   plan_activity.id as plan_activity_id, plan_activity.code as plan_activity_code, plan_activity.description as plan_activity_description, plan_activity.planned_start_date as plan_activity_planned_start_date, plan_activity.planned_end_date as plan_activity_planned_end_date, plan_activity.dependencies as plan_activity_dependencies, plan_activity.plan_id as plan_activity_plan_id, plan_activity.created_by as plan_activity_created_by, plan_activity.created_time as plan_activity_created_time, plan_activity.last_modified_by as plan_activity_last_modified_by, plan_activity.last_modified_time as plan_activity_last_modified_time,\n" +
            "\t   plan_activity_condition.id as plan_activity_condition_id, plan_activity_condition.entity as plan_activity_condition_entity, plan_activity_condition.entity_property as plan_activity_condition_entity_property, plan_activity_condition.expression as plan_activity_condition_expression, plan_activity_condition.activity_id as plan_activity_condition_activity_id, plan_activity_condition.is_active as plan_activity_condition_is_active, plan_activity_condition.created_by as plan_activity_condition_created_by, plan_activity_condition.created_time as plan_activity_condition_created_time, plan_activity_condition.last_modified_by as plan_activity_condition_last_modified_by, plan_activity_condition.last_modified_time as plan_activity_condition_last_modified_time,\n" +
            "\t   plan_resource.id as plan_resource_id, plan_resource.resource_type as plan_resource_resource_type, plan_resource.estimated_number as plan_resource_estimated_number, plan_resource.plan_id as plan_resource_plan_id, plan_resource.activity_code as plan_resource_activity_code, plan_resource.created_by as plan_resource_created_by, plan_resource.created_time as plan_resource_created_time, plan_resource.last_modified_by as plan_resource_last_modified_by, plan_resource.last_modified_time as plan_resource_last_modified_time,\n" +
            "\t   plan_target.id as plan_target_id, plan_target.metric as plan_target_metric, plan_target.metric_value as plan_target_metric_value, plan_target.metric_comparator as plan_target_metric_comparator, plan_target.metric_unit as plan_target_metric_unit, plan_target.plan_id as plan_target_plan_id, plan_target.activity_code as plan_target_activity_code, plan_target.created_by as plan_target_created_by, plan_target.created_time as plan_target_created_time, plan_target.last_modified_by as plan_target_last_modified_by, plan_target.last_modified_time as plan_target_last_modified_time\n" +
            "\t   FROM plan \n" +
            "\t   LEFT JOIN plan_activity ON plan.id = plan_activity.plan_id\n" +
            "\t   LEFT JOIN plan_activity_condition ON plan_activity.id = plan_activity_condition.activity_id\n" +
            "\t   LEFT JOIN plan_resource ON plan.id = plan_resource.plan_id\n" +
            "\t   LEFT JOIN plan_target ON plan.id = plan_target.plan_id";

    private static final String PLAN_FACILITY_SEARCH_QUERY_ORDER_BY_CLAUSE = " order by plan_facility_linkage.last_modified_time desc ";

    public String getPlanFacilityQuery(List<String> ids, List<Object> preparedStmtList) {
        return buildPlanFacilityQuery(ids, preparedStmtList);
    }

    private String buildPlanFacilityQuery(List<String> ids, List<Object> preparedStmtList) {
        StringBuilder builder = new StringBuilder(PLAN_FACILITY_QUERY);

        if (!CollectionUtils.isEmpty(ids)) {
            QueryUtil.addClauseIfRequired(builder, preparedStmtList);
            builder.append(" plan.id IN ( ").append(QueryUtil.createQuery(ids.size())).append(" )");
            QueryUtil.addToPreparedStatement(preparedStmtList, new LinkedHashSet<>(ids));
        }

        return builder.toString();
    }
    public String getPlanFacilitySearchQuery(PlanFacilitySearchCriteria planFacilitySearchCriteria, List<Object> preparedStmtList) {
        String query = buildPlanFacilitySearchQuery(planFacilitySearchCriteria, preparedStmtList);
        query = QueryUtil.addOrderByClause(query, PLAN_FACILITY_SEARCH_QUERY_ORDER_BY_CLAUSE);
        query = getPaginatedQuery(query, planFacilitySearchCriteria, preparedStmtList);
        return query;
    }

    private String buildPlanFacilitySearchQuery(PlanFacilitySearchCriteria planFacilitySearchCriteria, List<Object> preparedStmtList) {
        StringBuilder builder = new StringBuilder(PLAN_FACILITY_SEARCH_BASE_QUERY);

        if (!ObjectUtils.isEmpty(planFacilitySearchCriteria.getTenantId())) {
            QueryUtil.addClauseIfRequired(builder, preparedStmtList);
            builder.append(" tenant_id = ? ");
            preparedStmtList.add(planFacilitySearchCriteria.getTenantId());
        }

        if (!ObjectUtils.isEmpty(planFacilitySearchCriteria.getPlanConfigurationId())) {
            QueryUtil.addClauseIfRequired(builder, preparedStmtList);
            builder.append(" plan_configuration_id = ? ");
            preparedStmtList.add(planFacilitySearchCriteria.getPlanConfigurationId());
        }



        return builder.toString();
    }

    private String getPaginatedQuery(String query, PlanFacilitySearchCriteria planFacilitySearchCriteria, List<Object> preparedStmtList) {
        StringBuilder paginatedQuery = new StringBuilder(query);

        // Append offset
        paginatedQuery.append(" OFFSET ? ");
        preparedStmtList.add(ObjectUtils.isEmpty(planFacilitySearchCriteria.getOffset()) ? config.getDefaultOffset() : planFacilitySearchCriteria.getOffset());

        // Append limit
        paginatedQuery.append(" LIMIT ? ");
        preparedStmtList.add(ObjectUtils.isEmpty(planFacilitySearchCriteria.getLimit()) ? config.getDefaultLimit() : planFacilitySearchCriteria.getLimit());

        return paginatedQuery.toString();
    }


}
