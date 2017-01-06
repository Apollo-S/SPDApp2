package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Account;
import beans.Agreement;
import repositories.AccountDAOImpl;
import repositories.AgreementDAOImpl;
import repositories.SPDDAOImpl;

@WebServlet("/agreement")
public class AgreementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			if (request.getParameter("add") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				request.setAttribute("spd", spdDao.selectById(spdId));
				request.getRequestDispatcher("jsp/addAgreement.jsp").forward(request, response);
			} else if (request.getParameter("edit") != null) {
				int id = Integer.parseInt(request.getParameter("id"));	
				request.setAttribute("agreement", agreementDao.selectById(id));
				request.getRequestDispatcher("jsp/editAgreement.jsp").forward(request, response);
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
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				String number = request.getParameter("number");
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Agreement agreement = new Agreement(spdId, number, dateStart);
				agreementDao.create(agreement);
				response.sendRedirect("spd?id=" + spdId);
			} else if (request.getParameter("edit") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int id = Integer.parseInt(request.getParameter("id"));
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Agreement agreement = agreementDao.selectById(id);
				agreement.setSpdId(spdId);
				agreement.setNumber(request.getParameter("number"));
				agreement.setDateStart(dateStart);
				agreementDao.update(agreement);
				response.sendRedirect("spd?id=" + spdId);
			} else if (request.getParameter("delete") != null) {								
				int id = Integer.parseInt(request.getParameter("id"));
				Agreement agreement = agreementDao.selectById(id);
				int spdId = agreement.getSpdId();
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
