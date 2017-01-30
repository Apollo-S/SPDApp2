package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PaymentDAO;
import dao.impl.AccountDAOImpl;

import dao.impl.AgreementDAOImpl;

import dao.impl.PaymentDAOImpl;

import dao.impl.SPDDAOImpl;
import entity.Account;
import entity.Address;
import entity.Agreement;

import entity.Payment;
import entity.RegistrationInfo;
import entity.SPD;

@WebServlet("/spd")
public class SPDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	
	private final AccountDAOImpl accountDao = new AccountDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	
	private final PaymentDAO paymentDAO = new PaymentDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("jsp/addSPD.jsp").forward(request, response);
			} else {
				int spdId = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(spdId);
				Address address = spd.getAddress();
				RegistrationInfo regInfo = spd.getRegistrationInfo();
				List<Account> accounts = accountDao.selectAllBySPDId(spdId);
				List<Agreement> agreements = agreementDao.selectAllBySPDId(spdId);
				List<Payment> payments = paymentDAO.selectAllBySPDId(spdId);
				request.setAttribute("spd", spd);
				request.setAttribute("address", address);
				request.setAttribute("regInfo", regInfo);
				request.setAttribute("accounts", accounts);
				request.setAttribute("agreements", agreements);
				request.setAttribute("payments", payments);
				if (request.getParameter("edit") != null) {
					request.getRequestDispatcher("jsp/editSPD.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/viewSPD.jsp").forward(request, response);
				}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		} else {
			super.doPost(request, response);
		}
	}

}
