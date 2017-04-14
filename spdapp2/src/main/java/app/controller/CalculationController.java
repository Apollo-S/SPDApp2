package app.controller;

import java.util.Date;
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
import app.entity.Calculation;
import app.entity.Specification;
import app.repository.CalculationRepository;
import app.repository.SpecificationRepository;

@Controller
@Transactional
public class CalculationController {

	private static final Logger logger = LoggerFactory.getLogger(CalculationController.class);

	@Autowired(required = true)
	private CalculationRepository calcRepository;
	
	@Autowired(required = true)
	private SpecificationRepository specRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/calculation", method = RequestMethod.GET)
	public String getEditCalculation(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getEditCalculation()' method ... ==>");
		Calculation calculation = calcRepository.findOne(id);
		model.addAttribute("calculation", calculation);
		Double actualEsvRate = calcRepository.findActualEsvRateByCalculationId(id);
		model.addAttribute("esvRate", actualEsvRate);
		Double actualSimpleTaxRate = calcRepository.findActualSimpleTaxRateByCalculationId(id);
		model.addAttribute("simpleTaxRate", actualSimpleTaxRate);
		Double actualBankComissionRate = calcRepository.getActualBankComissionRateByCalculationId(id);
		model.addAttribute("bankComissionRate", actualBankComissionRate);
		logger.info("<== Out of 'getEditCalculation()' method ... ==>");
		return "calculation/edit";
	}

	@RequestMapping(value = "/calculation", params = "add", method = RequestMethod.POST)
	public String postAddCalculation(@RequestParam int specificationId, @RequestParam Integer partNumber, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddCalculation()' method ... ==>");
		Specification specification = specRepository.findOne(specificationId);
		logger.info("<== Saving new 'Calculation' for 'Specification='" + specification.getId() + "' ==>");
		Calculation calculation = new Calculation(specification, partNumber, dateStart);
		calculation = calcRepository.save(calculation);
		logger.info("<== Saving new 'Calculation' with ID=" + calculation.getId() + " for 'Specification=" + specification.getId() + "' was successful ==>");
		logger.info("<== Out of 'postAddCalculation()' method ... ==>");
		return "redirect:" + calculation.getUrl();
	}
}
