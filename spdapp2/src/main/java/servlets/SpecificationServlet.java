package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.event.SwingPropertyChangeSupport;

import beans.Agreement;
import beans.AgreementTarif;
import beans.SPD;
import beans.Specification;
import repositories.AgreementDAOImpl;
import repositories.AgreementTarifDAOImpl;
import repositories.SPDDAOImpl;
import repositories.SpecificationDAOImpl;
import utils.BeanUtils;

@WebServlet("/specification")
public class SpecificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	private final AgreementTarifDAOImpl tarifDao = new AgreementTarifDAOImpl();
	private final SpecificationDAOImpl specDao = new SpecificationDAOImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int agreementId = Integer.parseInt(request.getParameter("agreementId"));
			Agreement agreement = agreementDao.selectById(agreementId);
			int spdId = agreement.getSpdId();
			SPD spd = spdDao.selectById(spdId);
			request.setAttribute("spd", spd);
			request.setAttribute("agreement", agreement);
			if (request.getParameter("add") != null) {
				int specNumber = (specDao.getLastSpecificationNumberByAgreementId(agreementId)) + 1;
				request.setAttribute("specNumber", specNumber);
				request.setAttribute("dateStart", LocalDate.now());
				request.getRequestDispatcher("jsp/addSpecification.jsp").forward(request, response);
			} else {
				int specificationId = Integer.parseInt(request.getParameter("id"));
				Specification specification = specDao.selectById(specificationId);
				request.setAttribute("specification", specification);
				if (request.getParameter("edit") != null) {
					request.getRequestDispatcher("jsp/editSpecification.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("jsp/editSpecification.jsp").forward(request, response);
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
				int agreementId = Integer.parseInt(request.getParameter("agreementId"));
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int specificationNumber = Integer.parseInt(request.getParameter("specNumber"));
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Specification specification = new Specification();
				specification.setAgreementId(agreementId);
				specification.setSpecificationNumber(specificationNumber);
				specification.setDateStart(dateStart);
				specDao.create(specification);
				response.sendRedirect("specification?edit=&id=" + specification.getId() + "&agreementId=" + agreementId);
			} else if (request.getParameter("edit") != null) {
				int specificationId = Integer.parseInt(request.getParameter("id"));
				int agreementId = Integer.parseInt(request.getParameter("agreementId"));
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int agreementTarifId = Integer.parseInt(request.getParameter("agreementTarifId"));
				int specificationNumber = Integer.parseInt(request.getParameter("specificationNumber"));
				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
				Date dateFinish = Date.valueOf(request.getParameter("dateFinish"));
				double specificationSum = Double.parseDouble(request.getParameter("specificationSum"));
				int configuringHours = Integer.parseInt(request.getParameter("configuringHours"));
				int programmingHours = Integer.parseInt(request.getParameter("programmingHours"));
				int architectingHours = Integer.parseInt(request.getParameter("architectingHours"));
				int companyId = Integer.parseInt(request.getParameter("companyId"));;
				Specification specification = specDao.selectById(specificationId);
				specification = BeanUtils.editSpecificationSetters(specification, agreementId, agreementTarifId,
						specificationNumber, dateStart, dateFinish, specificationSum, configuringHours,
						programmingHours, architectingHours, companyId);
						
				specDao.update(specification);
				response.sendRedirect("agreement?edit=&id=" + agreementId + "&spdId=" + spdId);
			} else if (request.getParameter("delete") != null) {								
//				int agreementId = Integer.parseInt(request.getParameter("id"));
//				Agreement agreement = agreementDao.selectById(agreementId);
//				List<AgreementTarif> tarifs = tarifDao.selectAllByAgreementId(agreementId);
//				int spdId = agreement.getSpdId();
//				for(AgreementTarif tarif : tarifs) {
//					tarifDao.delete(tarif);
//				}
//				agreementDao.delete(agreement);
//				response.sendRedirect("spd?id=" + spdId);
			} else {
				super.doPost(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	

}
