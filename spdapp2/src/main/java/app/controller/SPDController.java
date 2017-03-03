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

import app.entity.Address;
import app.entity.RegistrationInfo;
import app.entity.SPD;
import app.repository.SPDRepository;

@Controller
@Transactional
public class SPDController {
	
	private static final Logger logger = LoggerFactory.getLogger(SPDController.class);

	@Autowired(required = true)
	private SPDRepository spdRepository;

	@RequestMapping(value = "/spds", method = RequestMethod.GET)
	public String getAllSPD(Model model) {
		logger.info("Entering to the getAllSPD() method***");
		model.addAttribute("spds", spdRepository.findAll());
		return "spd/getAll";
	}

	@RequestMapping(value = "/spd", params = "add", method = RequestMethod.GET)
	public String getAddSPD() {
		return "spd/add";
	}

	@RequestMapping(value = "/spd", params = "edit", method = RequestMethod.GET)
	public String getEditSPD(@RequestParam int id, Model model) {
		model.addAttribute("spd", spdRepository.findOne(id));
		return "spd/edit";
	}

	@RequestMapping(value = "/spd", method = RequestMethod.GET)
	public String getViewSPD(@RequestParam int id, Model model) {
		SPD spd = spdRepository.findOne(id);
		model.addAttribute("spd", spd);
		return "spd/view";
	}

	@RequestMapping(value = "/spd", params = "add", method = RequestMethod.POST)
	public String postAddSPD(@RequestParam String surname, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String alias, @RequestParam String inn,
			@RequestParam String passport, @RequestParam String description, @RequestParam Date dated,
			@RequestParam String country, @RequestParam String region, @RequestParam String city,
			@RequestParam String street, @RequestParam String building, @RequestParam String flat,
			@RequestParam String zip) {
		Address address = new Address(country, region, city, street, building, flat, zip);
		RegistrationInfo regInfo = new RegistrationInfo(description, dated);
		SPD spd = new SPD(surname, firstname, lastname, alias, inn, passport, address, regInfo);
		spd = spdRepository.save(spd);
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = "/spd", params = "edit", method = RequestMethod.POST)
	public String postEditSPD(@RequestParam int id, @RequestParam String surname, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String alias, @RequestParam String inn,
			@RequestParam String passport, @RequestParam String description, @RequestParam Date dated,
			@RequestParam String country, @RequestParam String region, @RequestParam String city,
			@RequestParam String street, @RequestParam String building, @RequestParam String flat,
			@RequestParam String zip) {
		SPD spd = spdRepository.findOne(id);
		Address address = spd.getAddress();
		RegistrationInfo regInfo = spd.getRegistrationInfo();
		address.setCountry(country);
		address.setRegion(region);
		address.setCity(city);
		address.setStreet(street);
		address.setBuilding(building);
		address.setFlat(flat);
		address.setZip(zip);
		regInfo.setDescription(description);
		regInfo.setDated(dated);
		spd.setSurname(surname);
		spd.setFirstname(firstname);
		spd.setLastname(lastname);
		spd.setAlias(alias);
		spd.setInn(inn);
		spd.setPassport(passport);
		spd.setAddress(address);
		spd.setRegistrationInfo(regInfo);
		spdRepository.save(spd);
		return "redirect:" + spd.getUrl();
	}

	@RequestMapping(value = "/spd", params = "delete", method = RequestMethod.POST)
	public String postDeleteSPD(@RequestParam int id) {
		spdRepository.delete(id);
		return "redirect:spds";
	}

}
