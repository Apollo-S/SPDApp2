package app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import app.entity.Account;
import app.entity.AgreementTarif;
import app.entity.CompanyAccount;
import app.entity.CompanyAddress;
import app.entity.CompanyDirector;
import app.entity.Job;
import app.entity.Specification;
import app.entity.SpecificationPayment;
import app.entity.SpecificationReport;
import app.repository.AccountRepository;
import app.repository.AgreementTarifRepository;
import app.repository.CompanyAccountRepository;
import app.repository.CompanyAddressRepository;
import app.repository.CompanyDirectorRepository;
import app.repository.SpecificationRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/specification")
public class ReportController {

	private static final String PARAM_DATA_SOURCE_SPEC = "dataSourceSpec";
	private static final String PARAM_DATA_SOURCE_CERT = "dataSourceCert";
	private static final String PARAM_VIEW_NAME_SPEC = "specificationReport";
	private static final String PARAM_VIEW_NAME_CERT = "certificateReport";

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired(required = true)
	private SpecificationRepository specificationRepository;
	
	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;
	
	@Autowired(required = true)
	private CompanyDirectorRepository compDirectorRepository;
	
	@Autowired(required = true)
	private CompanyAddressRepository compAddressRepository;
	
	@Autowired(required = true)
	private CompanyAccountRepository compAccountRepository;
	
	@Autowired(required = true)
	private AccountRepository accountRepository;
	
	@RequestMapping(value = "/printpdf/spec", method = RequestMethod.GET)
	public ModelAndView generatePdfReportSpec(@RequestParam Integer id, ModelAndView modelAndView) {
		logger.info("<<-------------- Begin to generate PDF report -------------->>");
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<SpecificationReport> reports = createReport(id);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		logger.info("<<-------------- jrDataSource created -------------->>");
		parameters.put(PARAM_DATA_SOURCE_SPEC, jrDataSource);
		logger.info("<<-------------- jrDataSource put into parameters -------------->>");
		logger.info("<<-------------- pdfReport bean has been declared in the jasper-views.xml file -------------->>");
		modelAndView = new ModelAndView(PARAM_VIEW_NAME_SPEC, parameters);
		logger.info("<<-------------- Out of generating PDF report -------------->>");
		return modelAndView;
	}
	
	@RequestMapping(value = "/printpdf/cert", method = RequestMethod.GET)
	public ModelAndView generatePdfReportCert(@RequestParam Integer id, ModelAndView modelAndView) {
		logger.info("<<-------------- Begin to generate PDF report -------------->>");
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<SpecificationReport> reports = createReport(id);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		logger.info("<<-------------- jrDataSource created -------------->>");
		parameters.put(PARAM_DATA_SOURCE_CERT, jrDataSource);
		logger.info("<<-------------- jrDataSource put into parameters -------------->>");
		logger.info("<<-------------- pdfReport bean has been declared in the jasper-views.xml file -------------->>");
		modelAndView = new ModelAndView(PARAM_VIEW_NAME_CERT, parameters);
		logger.info("<<-------------- Out of generating PDF report -------------->>");
		return modelAndView;
	}

	private List<SpecificationReport> createReport(Integer id) {
		List<SpecificationReport> reports = new ArrayList<>();
		Specification specification = specificationRepository.findOne(id);
		SpecificationReport report = new SpecificationReport();
		AgreementTarif currentRate = tarifRepository.findAgreementTarifBySpecificationId(id);
		CompanyDirector director = compDirectorRepository.findActualDirectorBySpecificationId(id);
		CompanyAddress companyAddress = compAddressRepository.findActualCompanyAddressBySpecificationId(id);
		CompanyAccount companyAccount = compAccountRepository.findActualCompanyAccountBySpecificationId(id);
		Account spdAccount = accountRepository.findActualSpdAccountBySpdId(specification.getAgreement().getSpd().getId());
		
		report.setAgreementTitle(specification.getAgreement().getNumber());
		report.setAgreementDate(specification.getAgreement().getDateStart());
		report.setSpecificationNumber(specification.getSpecificationNumber());
		report.setSpecificationStartDate(specification.getDateStart());
		report.setSpecificationFinalDate(specification.getDateFinish());
		report.setSpecificationSum(specification.getSpecificationSum());
		report.setConfiguringHours((Integer.toString(specification.getConfiguringHours()) == null) ? 0 : specification.getConfiguringHours());
		logger.info("<<-------------- " + report.getConfiguringHours() + " --------------->>");
		report.setProgrammingHours(specification.getProgrammingHours());
		logger.info("<<-------------- " + report.getProgrammingHours() + " --------------->>");
		report.setArchitectingHours(specification.getArchitectingHours());
		report.setConfiguringRate(currentRate.getConfiguring());
		report.setProgrammingRate(currentRate.getProgramming());
		report.setArchitectingRate(currentRate.getArchitecting());
		report.setCompanyName(specification.getAgreement().getCompany().getTitle());
		report.setCompanyTaxId(specification.getAgreement().getCompany().getEdrpou());
		report.setCompanyInn(specification.getAgreement().getCompany().getInn().equals(null) ? "-" : specification.getAgreement().getCompany().getInn());
		report.setCompanyVatCertificate(specification.getAgreement().getCompany().getVatCertificate().equals(null) ? "-" : specification.getAgreement().getCompany().getVatCertificate());
		report.setCompanyAddress(companyAddress.getPresentation());
		report.setCompanyAccount(companyAccount.getPresentation());
		report.setCompanyDirectorShortName(director.getShortName());
		report.setCompanyDirectorFullName(director.getFullName());
		report.setCompanyDirectorPost(director.getPost());
		report.setSpdFullName(specification.getAgreement().getSpd().getSpdFullName());
		report.setSpdAlias(specification.getAgreement().getSpd().getAlias());
		report.setSpdInn(specification.getAgreement().getSpd().getInn());
		report.setSpdAddress(specification.getAgreement().getSpd().getAddress().getPresentation());
		report.setSpdAccount(spdAccount.getPresentation());
		report.setRegInfo(specification.getAgreement().getSpd().getRegistrationInfo().getDescription());
		List<Job> jobs = new ArrayList<Job>(specification.getJobs());
		for (Job job : jobs) {
			logger.info("<<-------------- " + job.getConfiguringHours() + ", " + job.getProgrammingHours() + ", " + job.getArchitectingHours() + " --------------->>");
			int zero = 0;
			if (job.getConfiguringHours() == null) job.setConfiguringHours(zero);
			if (job.getProgrammingHours() == null) job.setProgrammingHours(zero);
			if (job.getArchitectingHours() == null) job.setArchitectingHours(zero);
			logger.info("<<-------------- " + job.getConfiguringHours() + ", " + job.getProgrammingHours() + ", " + job.getArchitectingHours() + " --------------->>");
		}
		report.setJobs(jobs);
		List<SpecificationPayment> payments = new ArrayList<>(specification.getSpecPayments());
		report.setPayments(payments);
		report.setQuantityOfPayments(payments.size());
		reports.add(report);
		return reports;
	}

}
