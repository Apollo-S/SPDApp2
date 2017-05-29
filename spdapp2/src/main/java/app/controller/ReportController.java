package app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.entity.Account;
import app.entity.AgreementTarif;
import app.entity.CompanyAccount;
import app.entity.CompanyAddress;
import app.entity.CompanyDirector;
import app.entity.Specification;
import app.entity.SpecificationReport;
import app.repository.AgreementRepository;
import app.repository.CompanyRepository;
import app.repository.SpecificationRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired(required = true)
	private SpecificationRepository specRepository;
	
	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/specification/printpdf", method = RequestMethod.GET)
	public ModelAndView generatePdfReport(@RequestParam Integer id, ModelAndView modelAndView) {
		logger.info("<<-------------- generate PDF report ---------->>");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		logger.info("<<-------------- parameterMap created ---------->>");
		Specification specification = specRepository.findOne(id);
		SpecificationReport report = new SpecificationReport();
		AgreementTarif currentRate = agreementRepository.findAgreementTarifBySpecificationId(id);
		CompanyDirector director = agreementRepository.findActualDirectorBySpecificationId(id);
		CompanyAddress companyAddress = agreementRepository.findActualCompanyAddressBySpecificationId(id);
		CompanyAccount companyAccount = agreementRepository.findActualCompanyAccountBySpecificationId(id);
		Account spdAccount = agreementRepository.findActualSpdAccountBySpdId(specification.getAgreement().getSpd().getId());
		report.setAgreementTitle(specification.getAgreement().getNumber());
		report.setAgreementDate(specification.getAgreement().getDateStart());
		report.setSpecificationNumber(specification.getSpecificationNumber());
		report.setSpecificationStartDate(specification.getDateStart());
		report.setSpecificationFinalDate(specification.getDateFinish());
		report.setSpecificationSum(specification.getSpecificationSum());
		report.setConfiguringHours(specification.getConfiguringHours());
		report.setProgrammingHours(specification.getProgrammingHours());
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
		report.setCompanyDirectorPost(director.getPost());
		report.setSpdFullName(specification.getAgreement().getSpd().getSpdFullName());
		report.setSpdAlias(specification.getAgreement().getSpd().getAlias());
		report.setSpdInn(specification.getAgreement().getSpd().getInn());
		report.setSpdAddress(specification.getAgreement().getSpd().getAddress().getPresentation());
		report.setSpdAccount(spdAccount.getPresentation());
		
		List<SpecificationReport> reports = new ArrayList<>();
		reports.add(report);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
		logger.info("<<-------------- jrDataSource created ---------->>");
		parameterMap.put("dataSourceSpec", jrDataSource);
		logger.info("<<////////////////// jrDataSource put into parameterMap //////////////////>>");
		// pdfReport bean has been declared in the jasper-views.xml file
		modelAndView = new ModelAndView("specificationReport", parameterMap);
		return modelAndView;
	}

//	@RequestMapping(value = "helloReport4", method = RequestMethod.GET)
//	public ModelAndView getRpt4(ModelMap modelMap, ModelAndView modelAndView) {
//		modelMap.put("datasource", getWidgets());
//		modelMap.put("format", "pdf");
//		modelAndView = new ModelAndView("rpt_HelloWorld", modelMap);
//		return modelAndView;
//	}

}
