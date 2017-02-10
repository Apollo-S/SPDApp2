package app.controller;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.entity.Specification;
import app.repository.SpecificationRepository;



@Controller
public class SpecificationController {

	@Autowired(required = true)
	private SpecificationRepository specRepository;
	
	
	@RequestMapping(value = "/specification", params = "edit", method = RequestMethod.GET)
	public String getEditSpecification(@RequestParam int id, Model model) {
		Specification specification = specRepository.findOne(id);
		model.addAttribute("specification", specification);
		return "specification/edit";
	}
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		try {
//			int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
//			Agreement agreement = agreementDao.selectById(agreementId);
//			int spdId = agreement.getSpdId();
//			SPD spd = spdDao.selectById(spdId);
//			request.setAttribute(ATTRIBUTE_SPD, spd);
//			request.setAttribute(ATTRIBUTE_AGREEMENT, agreement);
//			if (request.getParameter(PARAM_ADD) != null) {
//				int nextGeneratedSpecNumber = (specDao.getLastSpecificationNumberByAgreementId(agreementId)) + 1;
//				request.setAttribute(ATTRIBUTE_NEXT_GEN_SPEC_NUMBER, nextGeneratedSpecNumber);
//				request.setAttribute(PARAM_DATE_START, LocalDate.now());
//				request.getRequestDispatcher(PAGE_ADD).forward(request, response);
//			} else {
//				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
//				Specification specification = specDao.selectById(specificationId);
//				request.setAttribute(ATTRIBUTE_SPECIFICATION, specification);
//				if (request.getParameter(PARAM_EDIT) != null) {
//					request.getRequestDispatcher(PAGE_EDIT).forward(request, response);
//				} else {
//					request.getRequestDispatcher(PAGE_EDIT).forward(request, response);
//				}
//			}
//		} catch (Exception e) {
//			throw new ServletException(e);
//		}
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		try {
//			if (request.getParameter(PARAM_ADD) != null) {
//				int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
//				int spdId = Integer.parseInt(request.getParameter(PARAM_SPD_ID));
//				int specificationNumber = Integer.parseInt(request.getParameter(ATTRIBUTE_NEXT_GEN_SPEC_NUMBER));
//				Date dateStart =  Date.valueOf(request.getParameter(PARAM_DATE_START));
//				Specification specification = new Specification();
//				specification.setAgreementId(agreementId);
//				specification.setSpecificationNumber(specificationNumber);
//				specification.setDateStart(dateStart);
//				specDao.create(specification);
//				response.sendRedirect("specification?edit=&id=" + specification.getId() + "&agreementId=" + agreementId);
//			} else if (request.getParameter(PARAM_EDIT) != null) {
//				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
//				int agreementId = Integer.parseInt(request.getParameter(PARAM_AGREEMENT_ID));
//				int spdId = Integer.parseInt(request.getParameter("spdId"));
//				int agreementTarifId = Integer.parseInt(request.getParameter("agreementTarifId"));
//				int specificationNumber = Integer.parseInt(request.getParameter("specificationNumber"));
//				Date dateStart = beanUtils.requestedDateFormatter(request.getParameter(PARAM_DATE_START));
//				Date dateFinish = beanUtils.requestedDateFormatter(request.getParameter(PARAM_DATE_FINISH));
//				double specificationSum = Double.parseDouble(request.getParameter("specificationSum"));
//				int configuringHours = Integer.parseInt(request.getParameter("configuringHours"));
//				int programmingHours = Integer.parseInt(request.getParameter("programmingHours"));
//				int architectingHours = Integer.parseInt(request.getParameter("architectingHours"));
//				int companyId = Integer.parseInt(request.getParameter("companyId"));;
//				Specification specification = specDao.selectById(specificationId);
//				specification = beanUtils.editSpecificationSetters(specification, agreementId, agreementTarifId,
//						specificationNumber, dateStart, dateFinish, specificationSum, configuringHours,
//						programmingHours, architectingHours, companyId);
//						
//				specDao.update(specification);
//				response.sendRedirect("agreement?edit=&id=" + agreementId + "&spdId=" + spdId);
//			} else if (request.getParameter(PARAM_DELETE) != null) {	
//				Specification specification = new Specification();
//				int specificationId = Integer.parseInt(request.getParameter(PARAM_ID));
//				int spdId = Integer.parseInt(request.getParameter(PARAM_SPD_ID));
//				specification = specDao.selectById(specificationId);
//				int agreementId = specification.getAgreementId();
//				specDao.delete(specification);
//				response.sendRedirect("agreement?edit=&id=" + agreementId + "&spdId=" + spdId);
//			} else {
//				super.doPost(request, response);
//			}
//		} catch (Exception e) {
//			throw new ServletException(e);
//		}
//	}
//	
//	

}
