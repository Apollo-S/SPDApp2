package app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SpecificationReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private String agreementTitle;
	private Date agreementDate;
	private Integer specificationNumber;
	private Date specificationStartDate;
	private Date specificationFinalDate;
	private Double specificationSum;
	private Double configuringRate;
	private Double programmingRate;
	private Double architectingRate;
	private Integer configuringHours;
	private Integer programmingHours;
	private Integer architectingHours;
	private String companyName;
	private String companyTaxId;
	private String companyInn;
	private String companyVatCertificate;
	private String companyAddress;
	private String companyAccount;
	private String companyDirectorFullName;
	private String companyDirectorShortName;
	private String companyDirectorPost;
	private String spdAlias;
	private String spdFullName;
	private String spdAddress;
	private String spdAccount;
	private String spdInn;
	private List<Job> jobs;
	private List<SpecificationPayment> payments;
	private Integer quantityOfPayments;

	public SpecificationReport() {
	}

	public String getAgreementTitle() {
		return agreementTitle;
	}

	public void setAgreementTitle(String agreementTitle) {
		this.agreementTitle = agreementTitle;
	}

	public Date getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(Date agreementDate) {
		this.agreementDate = agreementDate;
	}

	public Integer getSpecificationNumber() {
		return specificationNumber;
	}

	public void setSpecificationNumber(Integer specificationNumber) {
		this.specificationNumber = specificationNumber;
	}

	public Date getSpecificationStartDate() {
		return specificationStartDate;
	}

	public void setSpecificationStartDate(Date specificationStartDate) {
		this.specificationStartDate = specificationStartDate;
	}

	public Date getSpecificationFinalDate() {
		return specificationFinalDate;
	}

	public void setSpecificationFinalDate(Date specificationFinalDate) {
		this.specificationFinalDate = specificationFinalDate;
	}

	public Double getSpecificationSum() {
		return specificationSum;
	}

	public void setSpecificationSum(Double specificationSum) {
		this.specificationSum = specificationSum;
	}

	public Integer getConfiguringHours() {
		return configuringHours;
	}

	public void setConfiguringHours(Integer configuringHours) {
		this.configuringHours = configuringHours;
	}

	public Integer getProgrammingHours() {
		return programmingHours;
	}

	public void setProgrammingHours(Integer programmingHours) {
		this.programmingHours = programmingHours;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTaxId() {
		return companyTaxId;
	}

	public void setCompanyTaxId(String companyTaxId) {
		this.companyTaxId = companyTaxId;
	}

	public String getCompanyInn() {
		return companyInn;
	}

	public void setCompanyInn(String companyInn) {
		this.companyInn = companyInn;
	}

	public String getCompanyVatCertificate() {
		return companyVatCertificate;
	}

	public void setCompanyVatCertificate(String companyVatCertificate) {
		this.companyVatCertificate = companyVatCertificate;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyDirectorFullName() {
		return companyDirectorFullName;
	}

	public void setCompanyDirectorFullName(String companyDirectorFullName) {
		this.companyDirectorFullName = companyDirectorFullName;
	}

	public String getCompanyDirectorShortName() {
		return companyDirectorShortName;
	}

	public void setCompanyDirectorShortName(String companyDirectorShortName) {
		this.companyDirectorShortName = companyDirectorShortName;
	}

	public String getSpdAlias() {
		return spdAlias;
	}

	public void setSpdAlias(String spdAlias) {
		this.spdAlias = spdAlias;
	}

	public String getSpdFullName() {
		return spdFullName;
	}

	public void setSpdFullName(String spdFullName) {
		this.spdFullName = spdFullName;
	}

	public String getSpdAddress() {
		return spdAddress;
	}

	public void setSpdAddress(String spdAddress) {
		this.spdAddress = spdAddress;
	}

	public Integer getArchitectingHours() {
		return architectingHours;
	}

	public void setArchitectingHours(Integer architectingHours) {
		this.architectingHours = architectingHours;
	}

	public Double getConfiguringRate() {
		return configuringRate;
	}

	public void setConfiguringRate(Double configuringRate) {
		this.configuringRate = configuringRate;
	}

	public Double getProgrammingRate() {
		return programmingRate;
	}

	public void setProgrammingRate(Double programmingRate) {
		this.programmingRate = programmingRate;
	}

	public Double getArchitectingRate() {
		return architectingRate;
	}

	public void setArchitectingRate(Double architectingRate) {
		this.architectingRate = architectingRate;
	}

	public String getCompanyDirectorPost() {
		return companyDirectorPost;
	}

	public void setCompanyDirectorPost(String companyDirectorPost) {
		this.companyDirectorPost = companyDirectorPost;
	}

	public String getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}

	public String getSpdInn() {
		return spdInn;
	}

	public void setSpdInn(String spdInn) {
		this.spdInn = spdInn;
	}

	public String getSpdAccount() {
		return spdAccount;
	}

	public void setSpdAccount(String spdAccount) {
		this.spdAccount = spdAccount;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<SpecificationPayment> getPayments() {
		return payments;
	}

	public void setPayments(List<SpecificationPayment> payments) {
		this.payments = payments;
	}

	public Integer getQuantityOfPayments() {
		return quantityOfPayments;
	}

	public void setQuantityOfPayments(Integer quantityOfPayments) {
		this.quantityOfPayments = quantityOfPayments;
	}

}
