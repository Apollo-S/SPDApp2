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
import beans.Agreement;
import beans.AgreementTarif;
import beans.SPD;
import repositories.AccountDAOImpl;
import repositories.AgreementDAOImpl;
import repositories.AgreementTarifDAOImpl;
import repositories.SPDDAOImpl;

@WebServlet("/agreement")
public class AgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	private final AgreementTarifDAOImpl tarifDao = new AgreementTarifDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int spdId = Integer.parseInt(request.getParameter("spdId"));
			SPD spd = spdDao.selectById(spdId);
			request.setAttribute("spd", spd);
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("jsp/addAgreement.jsp").forward(request, response);
			} else {
				int agreementId = Integer.parseInt(request.getParameter("id"));
				Agreement agreement = agreementDao.selectById(agreementId);
				List<AgreementTarif> tarifs = tarifDao.selectAllByAgreementId(agreementId);
				request.setAttribute("agreement", agreement);
				request.setAttribute("tarifs", tarifs);
				if (request.getParameter("edit") != null) {
					request.getRequestDispatcher("jsp/editAgreement.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/editAgreement.jsp").forward(request, response);
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			if (request.getParameter("add") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				String number = request.getParameter("number");
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Agreement agreement = new Agreement(spdId, number, dateStart);
				agreementDao.create(agreement);
				response.sendRedirect("agreement?id=" + agreement.getId() + "&spdId=" + spdId);
			} else if (request.getParameter("edit") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int agreementId = Integer.parseInt(request.getParameter("id"));
				String number = request.getParameter("number");
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Agreement agreement = agreementDao.selectById(agreementId);
				agreement.setSpdId(spdId);
				agreement.setNumber(number);
				agreement.setDateStart(dateStart);
				agreementDao.update(agreement);
				response.sendRedirect("spd?id=" + spdId);
			} else if (request.getParameter("delete") != null) {								
				int agreementId = Integer.parseInt(request.getParameter("id"));
				Agreement agreement = agreementDao.selectById(agreementId);
				List<AgreementTarif> tarifs = tarifDao.selectAllByAgreementId(agreementId);
				int spdId = agreement.getSpdId();
				for(AgreementTarif tarif : tarifs) {
					tarifDao.delete(tarif);
				}
				agreementDao.delete(agreement);
				response.sendRedirect("spd?id=" + spdId);
			} else {
				super.doPost(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
