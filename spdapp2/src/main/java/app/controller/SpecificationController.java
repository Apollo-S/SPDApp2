package app.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(SpecificationController.class);

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
		logger.info("<== Enter to 'getEditSpecification()' method ... ==>");
		Specification specification = specRepository.findOne(id);
		model.addAttribute("specification", specification);
		logger.info("<== Out of 'getEditSpecification()' method ... ==>");
		return "specification/edit";
	}

	@RequestMapping(value = "/specification", params = "add", method = RequestMethod.POST)
	public String postAddSpecification(@RequestParam Integer agreementId, @RequestParam Integer specificationNumber,
			@RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddSpecification()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(agreementId);
		logger.info("<== Adding new 'Specification' for 'Agreement='" + agreement.getNumber() + "' ==>");
		Specification specification = new Specification(agreement, specificationNumber, dateStart);
		specification = specRepository.save(specification);
		logger.info("<== Saving new 'Specification' with ID=" + specification.getId() + " for 'Agreement='"
				+ agreement.getNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddSpecification()' method ... ==>");
		return "redirect:" + specification.getUrl();
	}

	@RequestMapping(value = "/specification", params = "edit", method = RequestMethod.POST)
	public String postEditSpecification(@RequestParam Integer id, @RequestParam Integer specificationNumber,
			@RequestParam Double specificationSum, @RequestParam Date dateStart, @RequestParam Date dateFinish,
			@RequestParam Integer configuringHours, @RequestParam Integer programmingHours,
			@RequestParam Integer architectingHours) {
		logger.info("<== Enter to 'postEditSpecification()' method ... ==>");
		Specification specification = specRepository.findOne(id);
		logger.info("<== Starting update 'Specification' by ID=" + specification.getId() + " ==>");
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
		logger.info("<== Updating of 'postEditSpecification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditSpecification()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}

	@RequestMapping(value = "/specification", params = "delete", method = RequestMethod.POST)
	public String postDeleteSpecification(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteSpecification()' method ... ==>");
		Specification specification = specRepository.findOne(id);
		logger.info("<== Starting delete 'Specification' with ID=" + specification.getId() + " ==>");
		Agreement agreement = agreementRepository.findOne(specification.getAgreement().getId());
		specRepository.delete(specification);
		logger.info("<== Deleting of 'Specification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteSpecification()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}

}
