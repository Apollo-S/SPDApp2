package app.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Agreement;
import app.entity.AgreementTarif;
import app.entity.Job;
import app.entity.Specification;
import app.repository.AgreementRepository;
import app.repository.AgreementTarifRepository;
import app.repository.CalculationRepository;
import app.repository.JobRepository;
import app.repository.SpecificationRepository;
import utils.BeanUtil;

@Controller
@Transactional
public class SpecificationController {

	private static final Logger logger = LoggerFactory.getLogger(SpecificationController.class);

	@Autowired(required = true)
	private SpecificationRepository specificationRepository;
	
	@Autowired(required = true)
	private CalculationRepository calculationRepository;
	
	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	
	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;
	
	@Autowired(required = true)
	private JobRepository jobRepository;

	@RequestMapping(value = "/specification", method = RequestMethod.GET)
	public String getEditSpecification(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getEditSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		model.addAttribute("specification", specification);
		logger.info("<== Finding sum of current Calculations turnover ... ==>");
		Double calculationsTotalAmount = calculationRepository.findSumOfCalculationsBySpecificationId(id);
		model.addAttribute("calculationsTotalAmount", calculationsTotalAmount);
		logger.info("<== Finding next number for future Calculation ... ==>");
		int nextCalculationNumber = calculationRepository.findMaxCalculationNumberBySpecificationId(id);
		model.addAttribute("nextCalculationNumber", nextCalculationNumber + 1);
		AgreementTarif currentTarif = tarifRepository.findAgreementTarifBySpecificationId(specification.getId());
		logger.info("<== Got 'currentTarif' with ID=" + currentTarif.getId() + " ==>");
		model.addAttribute("currentTarif", currentTarif);
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
		specification = specificationRepository.save(specification);
		logger.info("<== Saving new 'Specification' with ID=" + specification.getId() + " for 'Agreement='"
				+ agreement.getNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddSpecification()' method ... ==>");
		return "redirect:" + specification.getUrl();
	}

	@RequestMapping(value = "/specification", params = "edit", method = RequestMethod.POST)
	public String postEditSpecification(@RequestParam Integer id, @RequestParam Integer specificationNumber,
			@RequestParam String specificationSum, @RequestParam Date dateStart, @RequestParam Date dateFinish,
			@RequestParam Integer configuringHours, @RequestParam Integer programmingHours,
			@RequestParam Integer architectingHours) {
		logger.info("<== Enter to 'postEditSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		logger.info("<== Starting update 'Specification' by ID=" + specification.getId() + " ==>");
		Agreement agreement = specification.getAgreement();
		specification.setAgreement(agreement);
		specification.setDateStart(dateStart);
		specification.setDateFinish(dateFinish);
		specification.setSpecificationNumber(specificationNumber);
		specification.setSpecificationSum(BeanUtil.convertStringToDouble(specificationSum));
		specification.setConfiguringHours(configuringHours);
		specification.setProgrammingHours(programmingHours);
		specification.setArchitectingHours(architectingHours);
		specification = specificationRepository.save(specification);
		logger.info("<== Updating of 'postEditSpecification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditSpecification()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}

	@RequestMapping(value = "/specification", params = "delete", method = RequestMethod.POST)
	public String postDeleteSpecification(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteSpecification()' method ... ==>");
		Specification specification = specificationRepository.findOne(id);
		logger.info("<== Starting delete 'Specification' with ID=" + specification.getId() + " ==>");
		Agreement agreement = specification.getAgreement();
		specificationRepository.delete(specification);
		logger.info("<== Deleting of 'Specification' with ID=" + specification.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteSpecification()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}
	
	@RequestMapping(value = "/job", params = "add", method = RequestMethod.POST)
	public String postAddJob(@RequestParam Integer specificationId, @RequestParam String jobName,
			@RequestParam Integer configuring, @RequestParam Integer programming, @RequestParam Integer architecting) {
		logger.info("<== Enter to 'postAddJob()' method ... ==>");
		Specification specification = specificationRepository.findOne(specificationId);
		logger.info("<== Adding new 'Job' for 'Specification='" + specification.getSpecificationNumber() + "' ==>");
		Job job = new Job(specification, jobName, configuring, programming, architecting);
		job = jobRepository.save(job);
		logger.info("<== Saving new 'Job' with ID=" + job.getId() + " for 'Specification='"
				+ specification.getSpecificationNumber() + " was successeful ==>");
		logger.info("<== Out of 'postAddJob()' method ... ==>");
		return "redirect:" + specification.getUrl();
	}

}
