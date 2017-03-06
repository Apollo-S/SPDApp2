package app.controller;

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
import app.repository.CompanyRepository;

@Controller
@Transactional
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired(required = true)
	private CompanyRepository companyRepository;
	
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
	public String postEditCompany(@RequestParam int id, @RequestParam String title, @RequestParam String edrpou,
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
	
}
