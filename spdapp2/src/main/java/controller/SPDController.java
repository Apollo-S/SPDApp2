package controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.impl.SPDDAOImpl;
import entity.Address;
import entity.RegistrationInfo;
import entity.SPD;

@Controller
public class SPDController {

	private static final String ATTRIBUTE_SPD_LIST = "spdList";
	private static final String PAGE_LIST_ALL_SPD = "view/spd/listAllSPD.jsp";
	
	@Autowired
	private SPDDAOImpl spdDao;

	@RequestMapping(value = "/listAllSPD", method = RequestMethod.GET)
	public void getSPDList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ATTRIBUTE_SPD_LIST, spdDao.selectAll());
		RequestDispatcher view = request.getRequestDispatcher(PAGE_LIST_ALL_SPD);
		view.forward(request, response);
	}

	@RequestMapping(value = "/spd", method = RequestMethod.GET)
	public void getSPD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("view/spd/addSPD.jsp").forward(request, response);
			} else {
				int spdId = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(spdId);
				Address address = spd.getAddress();
				RegistrationInfo regInfo = spd.getRegistrationInfo();
//				List<Account> accounts = spd.getAccounts();
//				List<Agreement> agreements = spd.getAgreements();
//				List<Payment> payments = spd.getPayments();
				request.setAttribute("spd", spd);
				request.setAttribute("address", address);
				request.setAttribute("regInfo", regInfo);
//				request.setAttribute("accounts", accounts);
//				request.setAttribute("agreements", agreements);
//				request.setAttribute("payments", payments);
				if (request.getParameter("edit") != null) {
					request.getRequestDispatcher("view/spd/editSPD.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("view/spd/viewSPD.jsp").forward(request, response);
				}
			}
	
	}

	@RequestMapping(value = "/spd", method = RequestMethod.POST)
	public void postSPD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			spd = spdDao.save(spd);
			response.sendRedirect("spd?id=" + spd.getId());
		} else if (request.getParameter("edit") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			SPD spd = spdDao.selectById(id);
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
			spdDao.save(spd);
			response.sendRedirect("spd?id=" + spd.getId());
		} else if (request.getParameter("delete") != null) {
			int spdId = Integer.parseInt(request.getParameter("id"));
			SPD spd = spdDao.selectById(spdId);
			spdDao.delete(spd);
			response.sendRedirect("listAllSPD");
		}
	}

}
