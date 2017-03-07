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
		logger.info("<== Enter to 'getEditCompany()' method ... ==>");
		Company company = companyRepository.findOne(id);
		model.addAttribute("company", company);
		logger.info("<== Out of 'getEditCompany()' method ... ==>");
		return "company/edit";
	}

	@RequestMapping(value = "/company", params = "add", method = RequestMethod.POST)
	public String postAddCompany(@RequestParam String title, @RequestParam String edrpou, @RequestParam String inn,
			@RequestParam String vatCertificate) {
		logger.info("<== Enter to 'postAddCompany()' method ... ==>");
		logger.info("<== Saving new 'Company' ... ==>");
		Company company = new Company(title, edrpou, inn, vatCertificate);
		company = companyRepository.save(company);
		logger.info("<== Saving new 'Company' with ID=" + company.getId() + " was successful ==>");
		logger.info("<== Out of 'postAddCompany()' method ... ==>");
		return "redirect:" + company.getUrl();
	}

	@RequestMapping(value = "/company", params = "edit", method = RequestMethod.POST)
	public String postEditCompany(@RequestParam Integer id, @RequestParam String title, @RequestParam String edrpou,
			@RequestParam String inn, @RequestParam String vatCertificate) {
		logger.info("<== Enter to 'postEditCompany()' method ... ==>");
		Company company = companyRepository.findOne(id);
		logger.info("<== Starting update 'Company' by ID=" + company.getId() + " ==>");
		company.setTitle(title);
		company.setEdrpou(edrpou);
		company.setInn(inn);
		company.setVatCertificate(vatCertificate);
		company = companyRepository.save(company);
		logger.info("<== Updating of 'Company' with ID=" + company.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompany()' method ... ==>");
		return "redirect:companies";
	}

	@RequestMapping(value = "/company", params = "delete", method = RequestMethod.POST)
	public String postDeleteCompany(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompany()' method ... ==>");
		logger.info("***Starting delete 'Company' by ID=" + id + " ==>");
		companyRepository.delete(id);
		logger.info("<== 'Agreement' with ID=" + id + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteCompany()' method ... ==>");
		return "redirect:companies";
	}

	@RequestMapping(value = "/companyAddress", params = "add", method = RequestMethod.POST)
	public String postAddCompanyAddress(@RequestParam int companyId, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddCompanyAddress()' method ... ==>");
		Company company = companyRepository.findOne(companyId);
		logger.info("<== Adding new 'CompanyAddress' for 'Company='" + company.getTitle() + "' ==>");
		CompanyAddress companyAddress = new CompanyAddress(company, presentation, dateStart);
		companyAddress = companyAddressRepository.save(companyAddress);
		logger.info("<== Saving new 'CompanyAddress' with ID=" + companyAddress.getId() + " for 'Company='" + company.getTitle() +  " was successeful ==>");
		logger.info("<== Out of 'postAddCompanyAddress()' method ... ==>");
		return "redirect:" + company.getUrl();
	}

	@RequestMapping(value = "/companyAddress", params = "edit", method = RequestMethod.POST)
	public String postEditCompanyAddress(@RequestParam int id, @RequestParam String presentation,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditCompanyAddress()' method ... ==>");
		CompanyAddress companyAddress = companyAddressRepository.findOne(id);
		logger.info("<== Starting update 'CompanyAddress' by ID=" + companyAddress.getId() + " ==>");
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		companyAddress.setCompany(company);
		companyAddress.setPresentation(presentation);
		companyAddress.setDateStart(dateStart);
		companyAddress = companyAddressRepository.save(companyAddress);
		logger.info("<== Updating of 'CompanyAddress' with ID=" + companyAddress.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyAddress()' method ... ==>");
		return "redirect:" + company.getUrl();
	}

	@RequestMapping(value = "/companyAddress", params = "delete", method = RequestMethod.POST)
	public String postDeleteCompanyAddress(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompanyAddress()' method ... ==>");
		CompanyAddress companyAddress = companyAddressRepository.findOne(id);
		Company company = companyRepository.findOne(companyAddress.getCompany().getId());
		logger.info("<== Starting delete 'CompanyAddress' with ID=" + companyAddress.getId() + " ==>");
		companyAddressRepository.delete(companyAddress);
		logger.info("<== Deleting of 'CompanyAddress' with ID=" + companyAddress.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteCompanyAddress()' method ... ==>");
		return "redirect:" + company.getUrl();
	}

}
