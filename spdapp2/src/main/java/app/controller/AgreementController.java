package app.controller;

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import app.entity.Agreement;
import app.entity.SPD;
import app.repository.AgreementRepository;
import app.repository.SPDRepository;

@Controller
public class AgreementController {

	@Autowired(required = true)
	private AgreementRepository agreementRepository;
	@Autowired(required = true)
	private SPDRepository spdRepository;

	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.GET)
	public String getAddAgreement(@RequestParam int spdId, Model model) {
		model.addAttribute("spd", spdRepository.findOne(spdId));
		return "agreement/add";
	}

	@RequestMapping(value = "/agreement", params = "edit", method = RequestMethod.GET)
	public String getEditAgreement(@RequestParam int id, Model model) {
		Agreement agreement = agreementRepository.findOne(id);
		int specNumber = agreementRepository.findMaxSpecificationNumberByAgreementId(id);
		model.addAttribute("agreement", agreement);
		model.addAttribute("specNumber", specNumber + 1);
		return "agreement/edit";
	}

	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.POST)
	public String postAddAgreement(@RequestParam int spdId, @RequestParam String number, @RequestParam Date dateStart) {
		SPD spd = spdRepository.findOne(spdId);
		Agreement agreement = new Agreement(spd, number, dateStart);
		agreement = agreementRepository.save(agreement);
		return "redirect:agreement?edit=&id=" + agreement.getId() + "&spdId=" + spdId;
	}

	@RequestMapping(value = "/agreement", params = "edit", method = RequestMethod.POST)
	public String postEditAccount(@RequestParam int id, @RequestParam int spdId, @RequestParam String number,
			@RequestParam Date dateStart) {
		Agreement agreement = agreementRepository.findOne(id);
		SPD spd = spdRepository.findOne(spdId);
		agreement.setSpd(spd);
		agreement.setNumber(number);
		agreement.setDateStart(dateStart);
		agreement = agreementRepository.save(agreement);
		return "redirect:spd?id=" + agreement.getSpd().getId();
	}

	@RequestMapping(value = "/agreement", params = "delete", method = RequestMethod.POST)
	public String postDeleteAgreement(@RequestParam int id) {
		Agreement agreement = agreementRepository.findOne(id);
		int spdId = agreement.getSpd().getId();
		agreementRepository.delete(id);
		return "redirect:spd?id=" + spdId;
	}

}
