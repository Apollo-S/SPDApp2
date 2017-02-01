package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SPDRepository;
import dao.impl.AgreementDAOImpl;
import dao.impl.SPDDAOImpl;
import dao.impl.SpecificationDAOImpl;
import entity.Agreement;
import entity.SPD;
import entity.Specification;
import utils.BeanUtil;

@WebServlet("/specification")
public class SpecificationController extends HttpServlet {
	private static final String ATTRIBUTE_SPECIFICATION = "specification";
	private static final String ATTRIBUTE_AGREEMENT = "agreement";
	private static final String ATTRIBUTE_SPD = "spd";
	private static final long serialVersionUID = 1L;
	private static final String PARAM_ID = "id";	
	private static final String PARAM_AGREEMENT_ID = "agreementId";
	private static final String PARAM_SPD_ID = "spdId";
	private static final String PARAM_DATE_FINISH = "dateFinish";
	private static final String PARAM_DATE_START = "dateStart";
	private static final String ATTRIBUTE_NEXT_GEN_SPEC_NUMBER = "specNumber";
	private static final String PARAM_DELETE = "delete";
	private static final String PARAM_EDIT = "edit";
	private static final String PARAM_ADD = "add";
	private static final String PAGE_EDIT = "jsp/editSpecification.jsp";
	private static final String PAGE_ADD = "jsp/addSpecification.jsp";
	private final SPDDAOImpl spdDao = new SPDDAOImpl();
	private final AgreementDAOImpl agreementDao = new AgreementDAOImpl();
	private final SpecificationDAOImpl specDao = new SpecificationDAOImpl();
	
	private BeanUtil beanUtils = new BeanUtil();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
			Agreement agreement = agreementDao.selectById(agreementId);
			int spdId = agreement.getSpdId();
			SPD spd = spdDao.selectById(spdId);
			request.setAttribute(ATTRIBUTE_SPD, spd);
			request.setAttribute(ATTRIBUTE_AGREEMENT, agreement);
			if (request.getParameter(PARAM_ADD) != null) {
				int nextGeneratedSpecNumber = (specDao.getLastSpecificationNumberByAgreementId(agreementId)) + 1;
				request.setAttribute(ATTRIBUTE_NEXT_GEN_SPEC_NUMBER, nextGeneratedSpecNumber);
				request.setAttribute(PARAM_DATE_START, LocalDate.now());
				request.getRequestDispatcher(PAGE_ADD).forward(request, response);
			} else {
				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
				Specification specification = specDao.selectById(specificationId);
				request.setAttribute(ATTRIBUTE_SPECIFICATION, specification);
				if (request.getParameter(PARAM_EDIT) != null) {
					request.getRequestDispatcher(PAGE_EDIT).forward(request, response);
				} else {
					request.getRequestDispatcher(PAGE_EDIT).forward(request, response);
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
			if (request.getParameter(PARAM_ADD) != null) {
				int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
				int spdId = Integer.parseInt(request.getParameter(PARAM_SPD_ID));
				int specificationNumber = Integer.parseInt(request.getParameter(ATTRIBUTE_NEXT_GEN_SPEC_NUMBER));
				Date dateStart =  Date.valueOf(request.getParameter(PARAM_DATE_START));
				Specification specification = new Specification();
				specification.setAgreementId(agreementId);
				specification.setSpecificationNumber(specificationNumber);
				specification.setDateStart(dateStart);
				specDao.create(specification);
				response.sendRedirect("specification?edit=&id=" + specification.getId() + "&agreementId=" + agreementId);
			} else if (request.getParameter(PARAM_EDIT) != null) {
				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
				int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
				int spdId = Integer.parseInt(request.getParameter("spdId"));
				int agreementTarifId = Integer.parseInt(request.getParameter("agreementTarifId"));
				int specificationNumber = Integer.parseInt(request.getParameter("specificationNumber"));
				Date dateStart = beanUtils.requestedDateFormatter(request.getParameter(PARAM_DATE_START));
				Date dateFinish = beanUtils.requestedDateFormatter(request.getParameter(PARAM_DATE_FINISH));
				double specificationSum = Double.parseDouble(request.getParameter("specificationSum"));
				int configuringHours = Integer.parseInt(request.getParameter("configuringHours"));
				int programmingHours = Integer.parseInt(request.getParameter("programmingHours"));
				int architectingHours = Integer.parseInt(request.getParameter("architectingHours"));
				int companyId = Integer.parseInt(request.getParameter("companyId"));;
				Specification specification = specDao.selectById(specificationId);
				specification = beanUtils.editSpecificationSetters(specification, agreementId, agreementTarifId,
						specificationNumber, dateStart, dateFinish, specificationSum, configuringHours,
						programmingHours, architectingHours, companyId);
						
				specDao.update(specification);
				response.sendRedirect("agreement?edit=&id=" + agreementId + "&spdId=" + spdId);
			} else if (request.getParameter(PARAM_DELETE) != null) {	
				Specification specification = new Specification();
				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
				int spdId = Integer.parseInt(request.getParameter(PARAM_SPD_ID));
				specification = specDao.selectById(specificationId);
				int agreementId = specification.getAgreementId();
				specDao.delete(specification);
				response.sendRedirect("agreement?edit=&id=" + agreementId + "&spdId=" + spdId);
			} else {
				super.doPost(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	

}
