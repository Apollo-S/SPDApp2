package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.impl.SPDDAOImpl;

@WebServlet("/listAllSPD")
public class ListAllSPDServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final SPDDAOImpl spdDao = new SPDDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setAttribute("spdList", spdDao.selectAll());
			RequestDispatcher view = request.getRequestDispatcher("jsp/listAllSPD.jsp");
			view.forward(request, response);
	}

}