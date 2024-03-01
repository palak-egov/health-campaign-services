package org.egov.referralmanagement.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egov.common.ds.Tuple;
import org.egov.common.models.household.Household;
import org.egov.referralmanagement.repository.rowmapper.HouseholdRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HouseholdRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	private HouseholdRowMapper householdRowMapper;
	
	 public Tuple<Long, List<Household>> findByView (String localityCode, Integer limit, Integer offset, String tenantId) {
	    	
	    	
	    	String query = "select * from household_address_mv where localitycode=:localitycode and rank between :start and :end ";

	        Map<String, Object> paramsMap = new HashMap<>();
	        paramsMap.put("start", offset);
	        paramsMap.put("end", offset+limit);
	        paramsMap.put("localitycode", localityCode);

	        Map<String, Object> paramsMapCount = new HashMap<>();
	        paramsMapCount.put("localitycode", localityCode);
	        
	        Long totalCount = Long.valueOf(namedParameterJdbcTemplate.queryForObject("select max(rank) where localitycode=:localitycode",paramsMapCount,Integer.class));
	        return new Tuple<>(totalCount, this.namedParameterJdbcTemplate.query(query, paramsMap, householdRowMapper));
	    }
}