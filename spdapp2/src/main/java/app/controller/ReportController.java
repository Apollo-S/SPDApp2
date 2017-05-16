package app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.registry.infomodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import app.entity.SPD;
import app.entity.Specification;
import app.repository.SPDRepository;
import app.repository.SpecificationRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired(required = true)
	private SpecificationRepository specRepository;

	@RequestMapping(value = "/specification/printpdf", method = RequestMethod.GET)
	public ModelAndView generatePdfReport(@RequestParam int id, ModelAndView modelAndView) {
		logger.info("<<-------------- generate PDF report ---------->>");
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		logger.info("<<-------------- parameterMap created ---------->>");
		Specification specification = specRepository.findOne(id);
		List<Specification> specifications = new ArrayList<>();
		specifications.add(specification);
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(specifications);
		logger.info("<<-------------- jrDataSource created ---------->>");
		parameterMap.put("dataSource", jrDataSource);
		logger.info("<<////////////////// jrDataSource put into parameterMap //////////////////>>");
		// pdfReport bean has been declared in the jasper-views.xml file
		modelAndView = new ModelAndView("pdfReport", parameterMap);
		return modelAndView;
	}

//	@RequestMapping(value = "helloReport4", method = RequestMethod.GET)
//	public ModelAndView getRpt4(ModelMap modelMap, ModelAndView modelAndView) {
//		modelMap.put("datasource", getWidgets());
//		modelMap.put("format", "pdf");
//		modelAndView = new ModelAndView("rpt_HelloWorld", modelMap);
//		return modelAndView;
//	}

}
