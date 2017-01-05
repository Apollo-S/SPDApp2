package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Account;
import repositories.AccountDaoImpl;
import repositories.SPDDaoImpl;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDaoImpl spdDao = new SPDDaoImpl();
	private final AccountDaoImpl accountDao = new AccountDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try { // get add row
			if (request.getParameter("add") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				request.setAttribute("spd", spdDao.selectById(spdId));
				request.getRequestDispatcher("jsp/addAccount.jsp").forward(request, response);
			} else if (request.getParameter("edit") != null) { // get edit row
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("account", accountDao.selectById(id));
				request.getRequestDispatcher("jsp/editAccount.jsp").forward(request, response);
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
		try { // post add row
			if (request.getParameter("add") != null) {
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				Account account = new Account(spdId, request.getParameter("accountNumber"), request.getParameter("mfo"),
						request.getParameter("bankName"));
				accountDao.create(account);
				response.sendRedirect("spd?id=" + spdId);
			} else if (request.getParameter("edit") != null) { // post edit row
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int id = Integer.parseInt(request.getParameter("id"));
				Account account = accountDao.selectById(id);
				account.setSpdId(spdId);
				account.setAccountNumber(request.getParameter("accountNumber"));
				account.setMfo(request.getParameter("mfo"));
				account.setBankName(request.getParameter("bankName"));
				accountDao.update(account);
				response.sendRedirect("spd?id=" + spdId);
			} else if (request.getParameter("delete") != null) { // post delete row									
				int id = Integer.parseInt(request.getParameter("id"));
				Account account = accountDao.selectById(id);
				int spdId = account.getSpdId();
				accountDao.delete(account);
				response.sendRedirect("spd?id=" + spdId);
			} else {
				super.doPost(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}
