package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Account;
import beans.Address;
import beans.Agreement;
import beans.AgreementTarif;
import beans.RegistrationInfo;
import beans.SPD;
import repositories.AccountDAOImpl;
import repositories.AddressDAOImpl;
import repositories.AgreementDAOImpl;
import repositories.AgreementTarifDAOImpl;
import repositories.RegistrationInfoDAOImpl;
import repositories.SPDDAOImpl;

@WebServlet("/spd")
public class SPDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	private final AddressDAOImpl addressDao = new AddressDAOImpl();
	private final RegistrationInfoDAOImpl regInfoDao = new RegistrationInfoDAOImpl();
	private final AccountDAOImpl accountDao = new AccountDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	private final AgreementTarifDAOImpl tarifDao = new AgreementTarifDAOImpl();

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
				Address address = addressDao.selectById(spd.getAddressId());
				RegistrationInfo regInfo = regInfoDao.selectById(spd.getRegistrationInfoId());
				List<Account> accounts = accountDao.selectAllBySPDId(spdId); 
				List<Agreement> agreements = agreementDao.selectAllBySPDId(spdId); 
				request.setAttribute("spd", spd);
				request.setAttribute("address", address);
				request.setAttribute("regInfo", regInfo);
				request.setAttribute("accounts", accounts);
				request.setAttribute("agreements", agreements);
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
		try {
			if (request.getParameter("add") != null) {
				Address address = new Address(request.getParameter("country"), request.getParameter("region"),
						request.getParameter("city"), request.getParameter("street"), request.getParameter("building"),
						request.getParameter("flat"), request.getParameter("zip"));
				addressDao.create(address);
				String dated = request.getParameter("dated");
				RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("description"), 
						Date.valueOf(dated));
				regInfoDao.create(regInfo);
				SPD spd = new SPD(request.getParameter("surname"), request.getParameter("firstname"),
						request.getParameter("lastname"), request.getParameter("alias"), request.getParameter("inn"),
						request.getParameter("passport"), address.getId(), regInfo.getId());
				spdDao.create(spd);
				response.sendRedirect("spd?id=" + spd.getId());
			} else if (request.getParameter("edit") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(id);
				Address address = addressDao.selectById(spd.getAddressId());
				address.setCountry(request.getParameter("country"));
				address.setRegion(request.getParameter("region"));
				address.setCity(request.getParameter("city"));
				address.setStreet(request.getParameter("street"));
				address.setBuilding(request.getParameter("building"));
				address.setFlat(request.getParameter("flat"));
				address.setZip(request.getParameter("zip"));
				RegistrationInfo regInfo = regInfoDao.selectById(spd.getRegistrationInfoId());
				String dated = request.getParameter("dated");
				regInfo.setDescription(request.getParameter("description"));
				regInfo.setDated(Date.valueOf(dated));
				spd.setSurname(request.getParameter("surname"));
				spd.setFirstname(request.getParameter("firstname"));
				spd.setLastname(request.getParameter("lastname"));
				spd.setAlias(request.getParameter("alias"));
				spd.setInn(request.getParameter("inn"));
				spd.setPassport(request.getParameter("passport"));
				spd.setAddressId(address.getId());
				spd.setRegistrationInfoId(regInfo.getId());
				addressDao.update(address);
				regInfoDao.update(regInfo);
				spdDao.update(spd);
				response.sendRedirect("spd?id=" + spd.getId());
			} else if (request.getParameter("delete") != null) { // post delete row
				int spdId = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(spdId);
				Address address = addressDao.selectById(spd.getAddressId());
				RegistrationInfo regInfo = regInfoDao.selectById(spd.getRegistrationInfoId());
				List<Agreement> agreements = agreementDao.selectAllBySPDId(spdId);
				for(Agreement agreement : agreements) {
					List<AgreementTarif> tarifs = tarifDao.selectAllByAgreementId(agreement.getId());
					for(AgreementTarif tarif : tarifs) {
						tarifDao.delete(tarif);
					}
					agreementDao.delete(agreement);
				}
				List<Account> accounts = accountDao.selectAllBySPDId(spdId);
				for(Account account : accounts) {
					accountDao.delete(account);
				}
				addressDao.delete(address);
				regInfoDao.delete(regInfo);
				spdDao.delete(spd);
				response.sendRedirect("listAllSPD");
			} else {
				super.doPost(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
