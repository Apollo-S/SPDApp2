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
import app.entity.AgreementTarif;
import app.entity.SPD;
import app.repository.AgreementRepository;
import app.repository.AgreementTarifRepository;
import app.repository.SPDRepository;

@Controller
@Transactional
public class AgreementController {

	private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired(required = true)
	private AgreementRepository agreementRepository;

	@Autowired(required = true)
	private SPDRepository spdRepository;

	@Autowired(required = true)
	private AgreementTarifRepository tarifRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/agreements", method = RequestMethod.GET)
	public String getAddAgreement(Model model) {
		model.addAttribute("agreements", agreementRepository.findAll());
		return "agreement/getAll";
	}

	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.GET)
	public String getAddAgreement(@RequestParam int spdId, Model model) {
		model.addAttribute("spd", spdRepository.findOne(spdId));
		return "agreement/add";
	}

	@RequestMapping(value = "/agreement", method = RequestMethod.GET)
	public String getAgreement(@RequestParam int id, Model model) {
		logger.info("<== Enter to 'getAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		int specNumber = agreementRepository.findMaxSpecificationNumberByAgreementId(id);
		model.addAttribute("agreement", agreement);
		model.addAttribute("specNumber", specNumber + 1);
		logger.info("<== Out of 'getAgreement()' method ... ==>");
		return "agreement/edit";
	}

	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.POST)
	public String postAddAgreement(@RequestParam int spdId, @RequestParam String number, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAddAgreement()' method ... ==>");
		SPD spd = spdRepository.findOne(spdId);
		logger.info("<== Saving new 'Agreement' for 'SPD='" + spd.getAlias() + "' ==>");
		Agreement agreement = new Agreement(spd, number, dateStart);
		agreement = agreementRepository.save(agreement);
		logger.info("<== Saving new 'Agreement' with ID=" + agreement.getId() + " for 'SPD=" + spd.getAlias() + "' was successful ==>");
		logger.info("<== Out of 'postAddAgreement()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}

	@RequestMapping(value = "/agreement", params = "edit", method = RequestMethod.POST)
	public String postEditAgreement(@RequestParam int id, @RequestParam String number, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		logger.info("<== Starting update 'Agreement' by ID=" + agreement.getId() + " ==>");
		SPD spd = spdRepository.findOne(agreement.getSpd().getId());
		agreement.setSpd(spd);
		agreement.setNumber(number);
		agreement.setDateStart(dateStart);
		agreement = agreementRepository.save(agreement);
		logger.info("<== Updating of 'Agreement' with ID=" + agreement.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditAgreement()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = "/agreement", params = "delete", method = RequestMethod.POST)
	public String postDeleteAgreement(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteAgreement()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(id);
		logger.info("***Start delete 'Agreement' by ID=" + agreement.getId() + " ==>");
		SPD spd = spdRepository.findOne(agreement.getSpd().getId());	
		agreementRepository.delete(agreement);
		logger.info("<== 'Agreement' with ID=" + agreement.getId() + " was deleted from DB ==>");
		logger.info("<== Out of 'postDeleteAgreement()' method ... ==>");
		return "redirect:" + spd.getUrl();
	}
	
	@RequestMapping(value = "/agreementTarif", params = "add", method = RequestMethod.POST)
	public String postAddAgreementTarif(@RequestParam Integer agreementId, @RequestParam Double configuring, @RequestParam Double programming, 
			@RequestParam Double architecting, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postAgreementTarifEdit()' method ... ==>");
		Agreement agreement = agreementRepository.findOne(agreementId);
		logger.info("<== Adding new 'AgreementTarif' for 'Agreement='" + agreement.getNumber() + "' ==>");
		AgreementTarif tarif = new AgreementTarif(agreement, configuring, programming, architecting, dateStart);
		tarif = tarifRepository.save(tarif);
		logger.info("<== Saving new 'AgreementTarif' with ID=" + tarif.getId() + " for 'Agreement='" + agreement.getNumber() +  " was successeful ==>");
		logger.info("<== Out of 'postAddAgreementTarif()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}

	@RequestMapping(value = "/agreementTarif", params = "edit", method = RequestMethod.POST)
	public String postEditAgreementTarif(@RequestParam Integer id, @RequestParam Double configuring,
			@RequestParam Double programming, @RequestParam Double architecting, @RequestParam Date dateStart) {
		logger.info("<== Enter to 'postEditAgreementTarif()' method ... ==>");
		AgreementTarif tarif = tarifRepository.findOne(id);
		logger.info("<== Starting update 'AgreementTarif' by ID=" + tarif.getId() + " ==>");
		Agreement agreement = agreementRepository.findOne(tarif.getAgreement().getId());
		tarif.setAgreement(agreement);
		tarif.setConfiguring(configuring);
		tarif.setProgramming(programming);
		tarif.setArchitecting(architecting);
		tarif.setDateStart(dateStart);
		tarifRepository.save(tarif);
		logger.info("<== Updating of 'AgreementTarif' with ID=" + tarif.getId() + " was successful ==>");
		logger.info("<== Out of 'postEditAgreementTarif()' method ... ==>");
		return "redirect:" + agreement.getUrl();
	}
	
	@RequestMapping(value = "/agreementTarif", params = "delete", method = RequestMethod.POST)
	public String postDeleteAgreementTarif(@RequestParam int id) {
		logger.info("<== Enter to 'postDeleteAgreementTarif()' method ... ==>");
		AgreementTarif tarif = tarifRepository.findOne(id);
		Agreement agreement = agreementRepository.findOne(tarif.getAgreement().getId());
		logger.info("<== Starting delete 'AgreementTarif' with ID=" + tarif.getId() + " ==>");
		tarifRepository.delete(tarif);
		logger.info("<== Deleting of 'AgreementTarif' with ID=" + tarif.getId() + " was successful ==>");
		logger.info("<== Out of 'postDeleteAgreementTarif()' method ... ==>");
		return "redirect:" + agreement.getUrl(); 
	}

}
