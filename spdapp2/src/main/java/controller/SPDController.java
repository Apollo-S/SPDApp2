package controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.SPDRepository;
import entity.Address;
import entity.RegistrationInfo;
import entity.SPD;

@Controller
public class SPDController {

	@Autowired
	private SPDRepository spdRepository;

	@RequestMapping(value = "/getAllSPD", method = RequestMethod.GET)
	public String getAllSPD(Model model) {
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
		model.addAttribute("spd", spdRepository.findOne(id));
		return "spd/edit";
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
		return "redirect:spd?id=" + spd.getId();
	}
	
	@RequestMapping(value = "/spd", params = "edit", method = RequestMethod.POST)
	public String postAddSPD(

	@RequestMapping(value = "/spd", method = RequestMethod.POST)
	public void postSPD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("add") != null) {
			Address address = new Address(request.getParameter("country"), request.getParameter("region"),
					request.getParameter("city"), request.getParameter("street"), request.getParameter("building"),
					request.getParameter("flat"), request.getParameter("zip"));
			String dated = request.getParameter("dated");
			RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("description"), Date.valueOf(dated));
			SPD spd = new SPD(request.getParameter("surname"), request.getParameter("firstname"),
					request.getParameter("lastname"), request.getParameter("alias"), request.getParameter("inn"),
					request.getParameter("passport"), address, regInfo);
			spd = spdRepository.save(spd);
			response.sendRedirect("spd?id=" + spd.getId());
		} else if (request.getParameter("edit") != null) {
			int spdId = Integer.parseInt(request.getParameter("id"));
			SPD spd = spdRepository.findOne(spdId);
			Address address = spd.getAddress();
			address.setCountry(request.getParameter("country"));
			address.setRegion(request.getParameter("region"));
			address.setCity(request.getParameter("city"));
			address.setStreet(request.getParameter("street"));
			address.setBuilding(request.getParameter("building"));
			address.setFlat(request.getParameter("flat"));
			address.setZip(request.getParameter("zip"));
			RegistrationInfo regInfo = spd.getRegistrationInfo();
			String dated = request.getParameter("dated");
			regInfo.setDescription(request.getParameter("description"));
			regInfo.setDated(Date.valueOf(dated));
			spd.setSurname(request.getParameter("surname"));
			spd.setFirstname(request.getParameter("firstname"));
			spd.setLastname(request.getParameter("lastname"));
			spd.setAlias(request.getParameter("alias"));
			spd.setInn(request.getParameter("inn"));
			spd.setPassport(request.getParameter("passport"));
			spd.setAddress(address);
			spd.setRegistrationInfo(regInfo);
			spdRepository.save(spd);
			response.sendRedirect("spd?id=" + spd.getId());
		} else if (request.getParameter("delete") != null) {
			int spdId = Integer.parseInt(request.getParameter("id"));
			SPD spd = spdRepository.findOne(spdId);
			spdRepository.delete(spd);
			response.sendRedirect("listAllSPD");
		}
	}

}
