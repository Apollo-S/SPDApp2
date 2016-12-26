package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Address;
import beans.RegistrationInfo;
import beans.SPD;
import dao.SPDDAO;
import repositories.AddressDaoImpl;
import repositories.RegistrationInfoDaoImpl;
import repositories.SPDDaoImpl;

@WebServlet("/spd")
public class SPDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDaoImpl spdDao = new SPDDaoImpl();
	private final AddressDaoImpl addressDao = new AddressDaoImpl();
	private final RegistrationInfoDaoImpl regInfoDao = new RegistrationInfoDaoImpl();

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
				Address address = addressDao.selectById(spd.getAddressId());
				RegistrationInfo regInfo = regInfoDao.selectById(spd.getRegistrationInfoId());
				request.setAttribute("spd", spd);
				request.setAttribute("address", address);
				request.setAttribute("regInfo", regInfo);
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
				
				RegistrationInfo regInfo = new RegistrationInfo(request.getParameter("description"), 
						Date.valueOf(request.getParameter("dated")));
				regInfoDao.create(regInfo);
				
				SPD spd = new SPD(request.getParameter("surname"), request.getParameter("firstname"),
						request.getParameter("lastname"), request.getParameter("alias"), request.getParameter("inn"),
						request.getParameter("passport"), address.getId(), regInfo.getId());
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
