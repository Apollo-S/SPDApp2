package app.controller;
//package controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import entity.Account;
//import entity.Agreement;
//import entity.AgreementTarif;
//import entity.SPD;
//import entity.Specification;
//import repository.AgreementRepository;
//import repository.AgreementTarifRepository;
//import repository.SPDRepository;
//
//@Controller
//public class AgreementController {
//
//	@Autowired(required = true)
//	private AgreementRepository agreementRepository;
////	@Autowired(required = true)
//	private SPDRepository spdRepository;
//	
//	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.GET)
//	public String getAddAgreement(@RequestParam int spdId, Model model) {
//		model.addAttribute("spd", spdRepository.findOne(spdId));
//		return "agreement/add";
//	}
//	
//	@RequestMapping(value = "/agreement", params = "edit", method = RequestMethod.GET)
//	public String getEditAgreement(@RequestParam int id, Model model) {
//		model.addAttribute("account", agreementRepository.findOne(id));
//		return "agreement/edit";
//	}
//	
//	@RequestMapping(value = "/agreement", params = "add", method = RequestMethod.POST)
//	public String postAddAgreement(@RequestParam int spdId, Model model) {
//		model.addAttribute("spd", spdRepository.findOne(spdId));
//		return "agreement/add";
//	}
//	
//	
////	@Override
////	protected void doGet(HttpServletRequest request, HttpServletResponse response)
////			throws ServletException, IOException {
////		request.setCharacterEncoding("UTF-8");
////		try {
////			int spdId = Integer.parseInt(request.getParameter("spdId"));
////			SPD spd = spdDao.selectById(spdId);
////			request.setAttribute("spd", spd);
////			if (request.getParameter("add") != null) {
////				request.getRequestDispatcher("jsp/addAgreement.jsp").forward(request, response);
////			} else {
////				int agreementId = Integer.parseInt(request.getParameter("id"));
////				Agreement agreement = agreementDao.selectById(agreementId);
////				List<AgreementTarif> tarifs = tarifRepository.selectAllByAgreementId(agreementId);
////				List<Specification> specifications = specDao.selectAllByAgreementId(agreementId);
////				int specNumber = specDao.getLastSpecificationNumberByAgreementId(agreementId);
////				request.setAttribute("agreement", agreement);
////				request.setAttribute("tarifs", tarifs);
////				request.setAttribute("specifications", specifications);
////				request.setAttribute("specNumber", specNumber + 1);
////				request.setAttribute("dateStart", LocalDate.now());
////				if (request.getParameter("edit") != null) {
////					request.getRequestDispatcher("jsp/editAgreement.jsp").forward(request, response);
////				} else {
////					request.getRequestDispatcher("jsp/editAgreement.jsp").forward(request, response);
////				}
////			}
////		} catch (Exception e) {
////			throw new ServletException(e);
////		}
////	}
////
////	@Override
////	protected void doPost(HttpServletRequest request, HttpServletResponse response)
////			throws ServletException, IOException {
////		request.setCharacterEncoding("UTF-8");
////
////			if (request.getParameter("add") != null) {
////				int spdId = Integer.parseInt(request.getParameter("spdId"));
////				String number = request.getParameter("number");
////				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
////				Agreement agreement = new Agreement(spdId, number, dateStart);
////				agreementDao.create(agreement);
////				response.sendRedirect("agreement?id=" + agreement.getId() + "&spdId=" + spdId);
////			} else if (request.getParameter("edit") != null) {
////				int spdId = Integer.parseInt(request.getParameter("spdId"));
////				int agreementId = Integer.parseInt(request.getParameter("id"));
////				String number = request.getParameter("number");
////				Date dateStart =  Date.valueOf(request.getParameter("dateStart"));
////				Agreement agreement = agreementDao.selectById(agreementId);
////				agreement.setSpdId(spdId);
////				agreement.setNumber(number);
////				agreement.setDateStart(dateStart);
////				agreementDao.update(agreement);
////				response.sendRedirect("spd?id=" + spdId);
////			} else if (request.getParameter("delete") != null) {								
////				int agreementId = Integer.parseInt(request.getParameter("id"));
////				Agreement agreement = agreementDao.selectById(agreementId);
////				List<AgreementTarif> tarifs = tarifRepository.selectAllByAgreementId(agreementId);
////				int spdId = agreement.getSpdId();
////				for(AgreementTarif tarif : tarifs) {
////					tarifRepository.delete(tarif);
////				}
////				agreementDao.delete(agreement);
////				response.sendRedirect("spd?id=" + spdId);
////			} else {
////				super.doPost(request, response);
////			}
////		} catch (SQLException e) {
////			throw new ServletException(e);
////		}
////	}
//
//}
