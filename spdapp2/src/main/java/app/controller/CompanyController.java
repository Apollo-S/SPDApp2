package app.controller;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.Company;
import app.entity.CompanyAddress;
import app.repository.CompanyAddressRepository;
import app.repository.CompanyRepository;

@Controller
@Transactional
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired(required = true)
	private CompanyRepository companyRepository;
	
	@Autowired(required = true)
	private CompanyAddressRepository companyAddressRepository;
	
	@RequestMapping(value = "/companies", method = RequestMethod.GET)
	public String getAllCompanies(Model model) {
		logger.info("***Entering to the getAllCompanies() method***");
		model.addAttribute("companies", companyRepository.findAll());
		return "company/getAll";
	}
	
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	public String getEditCompany(@RequestParam int id, Model model) {
		Company company = companyRepository.findOne(id);
		model.addAttribute("company", company);
		return "company/edit";
	}
	
	@RequestMapping(value = "/company", params = "add", method = RequestMethod.POST)
	public String postAddCompany(@RequestParam String title, @RequestParam String edrpou,
			@RequestParam String inn, @RequestParam String vatCertificate) {
		logger.info("***Adding new company...***");
		Company company = new Company(title, edrpou, inn, vatCertificate);
		company = companyRepository.save(company);
		logger.info("***Company " + company.getTitle() + " added successefully to DB***");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/company", params = "edit", method = RequestMethod.POST)
	public String postEditCompany(@RequestParam Integer id, @RequestParam String title, @RequestParam String edrpou,
			@RequestParam String inn, @RequestParam String vatCertificate) {
		logger.info("***Saving new company's data ...***");
		Company company = companyRepository.findOne(id);
		company.setTitle(title);
		company.setEdrpou(edrpou);
		company.setInn(inn);
		company.setVatCertificate(vatCertificate);
		company = companyRepository.save(company);
		logger.info("***Saving is OK!!!***");
		return "redirect:companies";
	}
	
	@RequestMapping(value = "/company", params = "delete", method = RequestMethod.POST)
	public String postDeleteCompany(@RequestParam int id) {
		companyRepository.delete(id);
		return "redirect:companies";
	}
	
	@RequestMapping(value = "/companyAddress", params = "add", method = RequestMethod.POST)
	public String postAddCompanyAddress(@RequestParam int companyId, @RequestParam String presentation, @RequestParam Date dateStart) {
		Company company = companyRepository.findOne(companyId);
		logger.info("***Adding new address for company='" + company.getTitle() + "' ***");
		CompanyAddress companyAddress = new CompanyAddress(company, presentation, dateStart);
		companyAddress = companyAddressRepository.save(companyAddress);
		logger.info("***CompanyAddress with ID=" + companyAddress.getId() + " has been added to DB successefully***");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/companyAddress", params = "edit", method = RequestMethod.POST)
	public String postEditCompanyAddress(@RequestParam int id, @RequestParam String presentation, 
			@RequestParam Date dateStart) {
		logger.info("***Start edit companyAddress data ...***");
		CompanyAddress companyAddress = companyAddressRepository.findOne(id);
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		companyAddress.setCompany(company);
		companyAddress.setPresentation(presentation);
		companyAddress.setDateStart(dateStart);
		companyAddress = companyAddressRepository.save(companyAddress);
		logger.info("***Saving of companyAddress is OK!!!***");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/companyAddress", params = "delete", method = RequestMethod.POST)
	public String postDeleteCompanyAddress(@RequestParam int id) {
		CompanyAddress companyAddress = companyAddressRepository.findOne(id);
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		logger.info("***Start delete companyAddress with ID=" +companyAddress.getId() + " ***");
		companyAddressRepository.delete(companyAddress);
		return "redirect:" + company.getUrl();
	}
	
}
