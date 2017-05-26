package app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Company;
import app.entity.CompanyDirector;
import app.repository.CompanyDirectorRepository;
import app.repository.CompanyRepository;

@Controller
@Transactional
public class CompanyDirectorController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyDirectorController.class);

	@Autowired(required = true)
	private CompanyRepository companyRepository;

	@Autowired(required = true)
	private CompanyDirectorRepository directorRepository;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/companyDirector", params = "add", method = RequestMethod.POST)
	public String postAddCompanyDirector(@RequestParam int companyId, @RequestParam String post, @RequestParam String fullName, 
			@RequestParam String shortName, @RequestParam Date employmentDate) {
		logger.info("<== Enter to 'postAddCompanyDirector()' method ... ==>");
		Company company = companyRepository.findOne(companyId);
		logger.info("<== Adding new 'CompanyDirector' for 'Company='" + company.getTitle() + "' ==>");
		CompanyDirector companyDirector = new CompanyDirector(company, post, fullName, shortName, employmentDate);
		companyDirector = directorRepository.save(companyDirector);
		logger.info("<== Saving new 'CompanyDirector' with ID=" + companyDirector.getId() + " for 'Company='" + company.getTitle() +  " was successeful ==>");
		logger.info("<== Out of 'postAddCompanyDirector()' method ... ==>");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/companyDirector/edit/{id}", method = RequestMethod.POST)
	public String postEditCompanyDirectorWithPathVar(@PathVariable("id") Integer id, @RequestParam String post, @RequestParam String fullName, 
			@RequestParam String shortName, @RequestParam Date employmentDate, @RequestParam Date firedDate) {
		logger.info("<== Enter to 'postEditCompanyDirector()' method ... ==>");
		CompanyDirector director = directorRepository.findOne(id);
		Company company = companyRepository.findOne(director.getCompany().getId());
		logger.info("<== Starting update 'CompanyDirector' by ID=" + director.getId() + " ==>");
		director.setCompany(company);
		director.setPost(post);
		director.setFullName(fullName);
		director.setShortName(shortName);
		director.setEmploymentDate(employmentDate);
		director.setFiredDate(firedDate);
		director = directorRepository.save(director);
		logger.info("<== Updating of 'CompanyDirector' with ID=" + director.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyDirector()' method ... ==>");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/companyDirector", params="edit", method = RequestMethod.POST)
	public String postEditCompanyDirector(@RequestParam Integer id, @RequestParam String post, @RequestParam String fullName, 
			@RequestParam String shortName, @RequestParam Date employmentDate, @RequestParam Date firedDate) {
		logger.info("<== Enter to 'postEditCompanyDirector()' method ... ==>");
		CompanyDirector director = directorRepository.findOne(id);
		Company company = companyRepository.findOne(director.getCompany().getId());
		logger.info("<== Starting update 'CompanyDirector' by ID=" + director.getId() + " ==>");
		director.setCompany(company);
		director.setPost(post);
		director.setFullName(fullName);
		director.setShortName(shortName);
		director.setEmploymentDate(employmentDate);
		director.setFiredDate(firedDate);
		director = directorRepository.save(director);
		logger.info("<== Updating of 'CompanyDirector' with ID=" + director.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditCompanyDirector()' method ... ==>");
		return "redirect:" + company.getUrl();
	}
	
	@RequestMapping(value = "/companyDirector", params = "delete", method = RequestMethod.POST)
	public String postDeleteCompanyDirector(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteCompanyDirector()' method ... ==>");
		logger.info("<== Starting delete 'CompanyDirector' by ID=" + id + " ==>");
		CompanyDirector director = directorRepository.findOne(id);
		directorRepository.delete(director);
		logger.info("<== 'CompanyDirector' with ID=" + id + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteCompanyDirector()' method ... ==>");
		return "redirect:" + director.getCompany().getUrl();
	}

}
