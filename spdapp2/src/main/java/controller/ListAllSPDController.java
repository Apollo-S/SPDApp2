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
public class ListAllSPDController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTRIBUTE_SPD_LIST = "spdList";
	private static final String PAGE_OK = "jsp/listAllSPD.jsp";
	private final SPDDAOImpl spdDao = new SPDDAOImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setAttribute(ATTRIBUTE_SPD_LIST, spdDao.selectAll());
			RequestDispatcher view = request.getRequestDispatcher(PAGE_OK);
			view.forward(request, response);
	}

}