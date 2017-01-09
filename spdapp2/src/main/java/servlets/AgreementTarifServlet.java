package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Agreement;
import beans.AgreementTarif;
import repositories.AgreementDAOImpl;
import repositories.AgreementTarifDAOImpl;

@WebServlet("/tarif")
public class AgreementTarifServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	private final AgreementTarifDAOImpl tarifDao = new AgreementTarifDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int agreementId = Integer.parseInt(request.getParameter("agreementId"));
			Agreement agreement = agreementDao.selectById(agreementId);
			request.setAttribute("agreement", agreement);
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("jsp/addAgreementTarif.jsp").forward(request, response);
			} else if (request.getParameter("edit") != null) {
				int tarifId = Integer.parseInt(request.getParameter("id"));	
				AgreementTarif tarif = tarifDao.selectById(tarifId);
				request.setAttribute("tarif", tarif);
				request.getRequestDispatcher("jsp/editAgreementTarif.jsp").forward(request, response);
			} else {
				super.doGet(request, response);
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
				int agreementId = Integer.parseInt(request.getParameter("agreementId"));
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				double configuring = Double.parseDouble(request.getParameter("configuring"));
				double programming = Double.parseDouble(request.getParameter("programming"));
				double architecting = Double.parseDouble(request.getParameter("architecting"));
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				AgreementTarif tarif = new AgreementTarif(agreementId, configuring, programming, architecting, dateStart);
				tarifDao.create(tarif);
				response.sendRedirect("agreement?id=" + tarif.getAgreementId() + "&spdId=" + spdId);
			} else if (request.getParameter("edit") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int tarifId = Integer.parseInt(request.getParameter("id"));
				int agreementId = Integer.parseInt(request.getParameter("agreementId"));
				double configuring = Double.parseDouble(request.getParameter("configuring"));
				double programming = Double.parseDouble(request.getParameter("programming"));
				double architecting = Double.parseDouble(request.getParameter("architecting"));
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				AgreementTarif tarif = tarifDao.selectById(tarifId);
				tarif.setAgreementId(agreementId);
				tarif.setConfiguring(configuring);
				tarif.setProgramming(programming);
				tarif.setArchitecting(architecting);
				tarif.setDateStart(dateStart);
				tarifDao.update(tarif);
				response.sendRedirect("agreement?id=" + tarif.getAgreementId() + "&spdId=" + spdId);
			} else if (request.getParameter("delete") != null) {								
				int tarifId = Integer.parseInt(request.getParameter("id"));
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				AgreementTarif tarif = tarifDao.selectById(tarifId);
				int agreementId = tarif.getAgreementId();
				tarifDao.delete(tarif);
				response.sendRedirect("agreement?id=" + agreementId + "&spdId=" + spdId);
			} else {
				super.doPost(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
