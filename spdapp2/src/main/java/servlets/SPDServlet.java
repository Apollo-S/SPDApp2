package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SPD;
import repositories.SPDDaoImpl;

@WebServlet("/spd")
public class SPDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDaoImpl spdDao = new SPDDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("add") != null) {
			request.getRequestDispatcher("jsp/addSPD.jsp").forward(request, response);
		} else {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				SPD spd = spdDao.selectById(id);
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("add") != null) {
			try {
				SPD spd = new SPD(request.getParameter("surname"), request.getParameter("firstname"),
						request.getParameter("lastname"), request.getParameter("alias"), request.getParameter("inn"),
						request.getParameter("passport"));
				spdDao.create(spd);
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			response.sendRedirect("listAllSPD");

		}
	}

}
