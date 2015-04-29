/**
 * eGov suite of products aim to improve the internal efficiency,transparency,
   accountability and the service delivery of the government  organizations.

    Copyright (C) <2015>  eGovernments Foundation

    The updated version of eGov suite of products as by eGovernments Foundation
    is available at http://www.egovernments.org

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see http://www.gnu.org/licenses/ or
    http://www.gnu.org/licenses/gpl.html .

    In addition to the terms of the GPL license to be adhered to in using this
    program, the following additional terms are to be complied with:

	1) All versions of this program, verbatim or modified must carry this
	   Legal Notice.

	2) Any misrepresentation of the origin of the material is prohibited. It
	   is required that all modified versions of this material be marked in
	   reasonable ways as different from the original version.

	3) This license does not grant any rights to any user of the program
	   with regards to rights under trademark law for use of the trade names
	   or trademarks of eGovernments Foundation.

  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.works.models.masters;

import org.egov.commons.CFinancialYear;
import org.egov.commons.CFunction;
import org.egov.commons.EgwStatus;
import org.egov.commons.EgwTypeOfWork;
import org.egov.commons.Functionary;
import org.egov.commons.Fund;
import org.egov.commons.Fundsource;
import org.egov.commons.Scheme;
import org.egov.commons.SubScheme;
import org.egov.commons.utils.EntityType;
import org.egov.infra.admin.master.entity.Boundary;
import org.egov.infra.admin.master.entity.Department;
import org.egov.infra.persistence.validator.annotation.Required;
import org.egov.infra.persistence.validator.annotation.Unique;
import org.egov.infstr.models.BaseModel;
import org.egov.works.models.estimate.WorkType;
import org.hibernate.validator.constraints.Length;

@Unique(fields={"code"},id="id", tableName="EGW_DEPOSITCODE",columnName={"CODE"},message="depositCode.isUnique")
public class DepositCode extends BaseModel implements EntityType {
	
	private String code;
	@Length(max=1024,message="depositCode.description.length")
	private String description;
	private WorkType worksType;
	
	@Required(message="depositCode.workName.null")
	@Length(max=256,message="depositCode.workName.length")
	private String codeName;
	private Fund fund;
	private Functionary functionary;
	private CFunction function;
	private Scheme scheme;
	private SubScheme subScheme;
	private Department department;
	private Boundary ward;
	private Boundary zone;
	
	@Required(message="depositCode.finYear.null")
	private CFinancialYear financialYear;
	private Fundsource fundSource;
	private EgwTypeOfWork typeOfWork;
	private EgwTypeOfWork subTypeOfWork;
	private Boolean isActive;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public WorkType getWorksType() {
		return worksType;
	}
	public void setWorksType(WorkType worksType) {
		this.worksType = worksType;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Fund getFund() {
		return fund;
	}
	public void setFund(Fund fund) {
		this.fund = fund;
	}
	public Functionary getFunctionary() {
		return functionary;
	}
	public void setFunctionary(Functionary functionary) {
		this.functionary = functionary;
	}
	public CFunction getFunction() {
		return function;
	}
	public void setFunction(CFunction function) {
		this.function = function;
	}
	public Scheme getScheme() {
		return scheme;
	}
	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}
	public SubScheme getSubScheme() {
		return subScheme;
	}
	public void setSubScheme(SubScheme subScheme) {
		this.subScheme = subScheme;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Boundary getWard() {
		return ward;
	}
	public void setWard(Boundary ward) {
		this.ward = ward;
	}
	public Boundary getZone() {
		return zone;
	}
	public void setZone(Boundary zone) {
		this.zone = zone;
	}
	public CFinancialYear getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(CFinancialYear financialYear) {
		this.financialYear = financialYear;
	}
	public Fundsource getFundSource() {
		return fundSource;
	}
	public void setFundSource(Fundsource fundSource) {
		this.fundSource = fundSource;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public EgwTypeOfWork getTypeOfWork() {
		return typeOfWork;
	}
	public void setTypeOfWork(EgwTypeOfWork typeOfWork) {
		this.typeOfWork = typeOfWork;
	}
	public EgwTypeOfWork getSubTypeOfWork() {
		return subTypeOfWork;
	}
	public void setSubTypeOfWork(EgwTypeOfWork subTypeOfWork) {
		this.subTypeOfWork = subTypeOfWork;
	}
	
	@Override
	public String getName() {
		return codeName;
	}
	
	@Override
	public String getEntityDescription() {
		return description;
	}
	
	@Override
	public Integer getEntityId() {
		return Integer.valueOf(id.intValue());
	}
	
	@Override
	public String getIfsccode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getModeofpay() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getBankaccount() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getBankname() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getPanno() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getTinno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EgwStatus getEgwStatus() {
		// TODO Auto-generated method stub
		return null;
	}
}
