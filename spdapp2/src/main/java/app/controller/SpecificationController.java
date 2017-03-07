package app.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Agreement;
import app.entity.Specification;
import app.repository.AgreementRepository;
import app.repository.SpecificationRepository;

@Controller
@Transactional
public class SpecificationController {

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
	
	@RequestMapping(value = "/specification", method = RequestMethod.GET)
	public String getEditSpecification(@RequestParam int id, Model model) {
		Specification specification = specRepository.findOne(id);
		model.addAttribute("specification", specification);
		return "specification/edit";
	}
	
	@RequestMapping(value = "/specification", params = "add", method = RequestMethod.POST)
	public String postAddAgreement(@RequestParam Integer agreementId, @RequestParam Integer specificationNumber, @RequestParam Date dateStart ) {
		Agreement agreement = agreementRepository.findOne(agreementId);
		Specification specification = new Specification(agreement, specificationNumber, dateStart);
		specification = specRepository.save(specification);
		return "redirect:" + specification.getUrl();
	}

	@RequestMapping(value = "/specification", params = "edit", method = RequestMethod.POST)
	public String postEditSpecification(@RequestParam Integer id, @RequestParam Integer specificationNumber, @RequestParam Double specificationSum, 
			@RequestParam Date dateStart, @RequestParam Date dateFinish, @RequestParam Integer configuringHours, @RequestParam Integer programmingHours,
			@RequestParam Integer architectingHours) {
		Specification specification = specRepository.findOne(id);
		Agreement agreement = agreementRepository.findOne(specification.getAgreement().getId());
		specification.setAgreement(agreement);
		specification.setDateStart(dateStart);
		specification.setDateFinish(dateFinish);
		specification.setSpecificationNumber(specificationNumber);
		specification.setSpecificationSum(specificationSum);
		specification.setConfiguringHours(configuringHours);
		specification.setProgrammingHours(programmingHours);
		specification.setArchitectingHours(architectingHours);
		specification = specRepository.save(specification);
		return "redirect:" + agreement.getUrl(); 
	}

	@RequestMapping(value = "/specification", params = "delete", method = RequestMethod.POST)
	public String postDeleteSpecification(@RequestParam int id) {
		Specification specification = specRepository.findOne(id);
		Agreement agreement = agreementRepository.findOne(specification.getAgreement().getId());
		specRepository.delete(specification);
		return "redirect:" + agreement.getUrl(); 
	}
	
}
