package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repositories.SPDDaoImpl;

@WebServlet("/listAllSPD")
public class viewSPDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final SPDDaoImpl spdDao = new SPDDaoImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("spdList", spdDao.selectAll());
			RequestDispatcher view = request.getRequestDispatcher("jsp/listAllSPD.jsp");
			view.forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

}