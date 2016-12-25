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
import dao.SPDDAO;
import repositories.SPDDaoImpl;

@WebServlet("/spd")
public class SPDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDaoImpl spdDao = new SPDDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			if (request.getParameter("add") != null) {
				request.getRequestDispatcher("jsp/addSPD.jsp").forward(request, response);
			} else {
				int id = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(id);
				request.setAttribute("spd", spd);
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

				SPD spd = new SPD(request.getParameter("surname"), request.getParameter("firstname"),
						request.getParameter("lastname"), request.getParameter("alias"), request.getParameter("inn"),
						request.getParameter("passport"));
				spdDao.create(spd);
				response.sendRedirect("spd?id=" + spd.getId());
			} else if (request.getParameter("edit") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(id);
				spd.setSurname(request.getParameter("surname"));
				spd.setFirstname(request.getParameter("firstname"));
				spd.setLastname(request.getParameter("lastname"));
				spd.setAlias(request.getParameter("alias"));
				spd.setInn(request.getParameter("inn"));
				spd.setPassport(request.getParameter("passport"));
				spdDao.update(spd);
				response.sendRedirect("spd?id=" + spd.getId());
			} else if (request.getParameter("delete") != null) {
				int id = Integer.parseInt(request.getParameter("id"));
				SPD spd = spdDao.selectById(id);
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
