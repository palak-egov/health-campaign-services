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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.egov.commons.Bank;
import org.egov.commons.EgwStatus;
import org.egov.commons.utils.EntityType;
import org.egov.infra.persistence.validator.annotation.OptionalPattern;
import org.egov.infra.persistence.validator.annotation.Required;
import org.egov.infra.persistence.validator.annotation.Unique;
import org.egov.infra.validation.regex.Constants;
import org.egov.infstr.ValidationError;
import org.egov.infstr.models.BaseModel;
import org.egov.works.utils.WorksConstants;
import org.hibernate.validator.constraints.Length;

@Unique(fields={"code"},id="id",tableName="EGW_CONTRACTOR",columnName={"CODE"},message="contractor.code.isunique")
public class Contractor  extends BaseModel implements EntityType{
	
	@Required(message="contractor.code.null")
	@Length(max=50,message="contractor.code.length")
	@OptionalPattern(regex=WorksConstants.alphaNumericwithspecialchar,message="contractor.code.alphaNumeric")  
	private String code;
	 	 
	@Required(message="contractor.name.null") 
	@Length(max=100,message="contractor.name.length")
	@OptionalPattern(regex=WorksConstants.alphaNumericwithspecialchar,message="contractor.name.alphaNumeric")
	private String name;
	
	@Length(max=250,message="contractor.correspondenceAddress.length")
	private String correspondenceAddress;
	
	@Length(max=250,message="contractor.paymentAddress.length")
	private String paymentAddress;
	
	@Length(max=100,message="contractor.contactPerson.length")
	@OptionalPattern(regex=Constants.ALPHANUMERIC_WITHSPACE,message="contractor.contactPerson.alphaNumeric")
	private String contactPerson;

	@OptionalPattern(regex=Constants.EMAIL,message="contractor.email.invalid")
	@Length(max=100,message="contractor.email.length")
	private String email;
	
	@Length(max=250,message="contractor.narration.length")
	private String narration;
	
	@Length(max=14,message="contractor.panNumber.length")
	@OptionalPattern(regex=Constants.ALPHANUMERIC,message="contractor.panNumber.alphaNumeric")
	private String panNumber;
	
	@Length(max=14,message="contractor.tinNumber.length")
	@OptionalPattern(regex=Constants.ALPHANUMERIC,message="contractor.tinNumber.alphaNumeric")
	private String tinNumber;
	
	private Bank bank;
	
	@Length(max=15,message="contractor.ifscCode.length")
	@OptionalPattern(regex=Constants.ALPHANUMERIC,message="contractor.ifscCode.alphaNumeric")
	private String ifscCode;
	
	@Length(max=22,message="contractor.bankAccount.length")
	@OptionalPattern(regex=Constants.ALPHANUMERIC,message="contractor.bankAccount.alphaNumeric")
	private String bankAccount;
	
	@Length(max=50,message="contractor.pwdApprovalCode.length")
	@OptionalPattern(regex=WorksConstants.alphaNumericwithspecialchar,message="contractor.pwdApprovalCode.alphaNumeric")
	private String pwdApprovalCode;
	private boolean isEditEnabled;
	
	@Valid
	private List<ContractorDetail> contractorDetails = new LinkedList<ContractorDetail>();
	
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorrespondenceAddress() {
		return correspondenceAddress;
	}

	public void setCorrespondenceAddress(String correspondenceAddress) {
		this.correspondenceAddress = correspondenceAddress;
	}

	public String getPaymentAddress() {
		return paymentAddress;
	}

	public void setPaymentAddress(String paymentAddress) {
		this.paymentAddress = paymentAddress;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getTinNumber() {
		return tinNumber;
	}

	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getPwdApprovalCode() {
		return pwdApprovalCode;
	}

	public void setPwdApprovalCode(String pwdApprovalCode) {
		this.pwdApprovalCode = pwdApprovalCode;
	}

	public List<ContractorDetail> getContractorDetails() {
		return contractorDetails;
	}

	public void setContractorDetails(List<ContractorDetail> contractorDetails) {
		this.contractorDetails = contractorDetails;
	}
	
	public void addContractorDetail(ContractorDetail contractorDetail){
		this.contractorDetails.add(contractorDetail);
	}
	
	public List<ValidationError> validate()	{
		List<ValidationError> errorList = null;
		if(contractorDetails!= null && contractorDetails.isEmpty()) {
			return Arrays.asList(new ValidationError("contractorDetails","contractor.details.altleastone_details_needed"));
		}
		else if(contractorDetails!= null && !contractorDetails.isEmpty()) {			
			for (ContractorDetail contractorDetail : contractorDetails) {				
				errorList = contractorDetail.validate();
				if(errorList!=null)	{
					return errorList;
				}
			}			
		}
		return errorList;
	}

	public String getBankaccount() {
		// TODO Auto-generated method stub
		return this.bankAccount;
	}

	public String getBankname() {
		// TODO Auto-generated method stub
		if(this.bank==null){
			return "";
		}else{
			return this.bank.getName();
		}
	}

	public String getIfsccode() {
		// TODO Auto-generated method stub
		return this.ifscCode;
	}

	public String getPanno() {
		// TODO Auto-generated method stub
		return this.panNumber;
	}

	public String getTinno() {
		// TODO Auto-generated method stub
		return this.tinNumber;
	}

	public String getModeofpay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getEntityId() {
		return Integer.valueOf(id.intValue());
	}
	
	@Override
	public String getEntityDescription() {
		
		return getName();
	}	

	public boolean getIsEditEnabled() {
		return isEditEnabled;
	}

	public void setIsEditEnabled(boolean isEditEnabled) {
		this.isEditEnabled = isEditEnabled;
	}

    @Override
    public EgwStatus getEgwStatus() {
        // TODO Auto-generated method stub
        return null;
    }
}

