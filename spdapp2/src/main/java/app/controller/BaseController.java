package app.controller;

public abstract class BaseController {
	
	static final String PARAM_ADD = "add";
	static final String PARAM_DELETE = "delete";
	static final String PARAM_EDIT = "edit";

	static final String REQUEST_MAPPING_ABOUT = "/about";
	static final String REQUEST_MAPPING_AGREEMENT = "/agreement";
	static final String REQUEST_MAPPING_AGREEMENT_TARIF = "/agreementTarif";
	static final String REQUEST_MAPPING_BLANK = "/";
	static final String REQUEST_MAPPING_CALCULATION = "/calculation";
	static final String REQUEST_MAPPING_COMPANY = "/company";
	static final String REQUEST_MAPPING_COMPANIES = "/companies";
	static final String REQUEST_MAPPING_COMPANY_ADDRESS = "/companyAddress";
	static final String REQUEST_MAPPING_COMPANY_DIRECTOR = "/companyDirector";
	static final String REQUEST_MAPPING_JOB = "/job";
	static final String REQUEST_MAPPING_LOGIN = "/login";
	static final String REQUEST_MAPPING_MAIN = "/main";
	static final String REQUEST_MAPPING_PRINTPDF_CERT = "/printpdf/cert";
	static final String REQUEST_MAPPING_PRINTPDF_SPEC = "/printpdf/spec";
	static final String REQUEST_MAPPING_REGISTER = "/register";
	static final String REQUEST_MAPPING_SPD = "/spd";
	static final String REQUEST_MAPPING_SPDS = "/spds";
	static final String REQUEST_MAPPING_SPD_ACCOUNT = "/account";
	static final String REQUEST_MAPPING_SPD_PAYMENT = "/payment";
	static final String REQUEST_MAPPING_SPECIFICATION = "/specification";
	static final String REQUEST_MAPPING_SPECIFICATIONPAYMENT = "/specificationpayment";
	static final String REQUEST_MAPPING_USERS = "/users";
	static final String REQUEST_MAPPING_USER = "/user";

	static final String ROLE_ADMIN = "ROLE_ADMIN";
	static final String ROLE_USER = "ROLE_USER";

}
