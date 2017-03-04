<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../header.jsp" />

<title><c:out value="${company.title}" /></title>

<div class="container-fluid">

	<form class="form" role="form" action="company" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${company.id}">

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="companies">Компании</a>
					<span class="breadcrumb-item active"><b><c:out value="${company.title}" /></b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="companies" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
		<p>

<div class="container-fluid">		

	<div class="form-group row">
		<label for="title" class="col-sm-2 col-form-label"><b>Название</b></label>
		<div class="col-3">
			<input type="text" class="form-control" id="title"
				name="title" placeholder="Введите номер счета" value="${fn:replace(company.title, '"', '&quot;')}" >  
		</div>
	</div>
	<div class="form-group row">	
		<label for="edrpou" class="col-sm-2 col-form-label"><b>ЕДРПОУ</b></label>
		<div class="col-3">
			<input type="text" class="form-control" id="edrpou"
				name="edrpou" placeholder="Введите наименование банка" value=<c:out value="${company.edrpou}"/> >
		</div>
	</div>
</div>

	<div class="row">
		<div class="form-group">
			<label for="inn" class="col-sm-10 control-label"><b>ИНН</b></label>
			<div class="col-sm-12">
				<input type="text" class="form-control" id="inn"
					name="inn" placeholder="Введите ИНН" value="${company.inn}">
			</div>
		</div>	
		<div class="form-group">
			<label for="vatCertificate" class="col-sm-12 control-label"><b>№ свидетельства плат. НДС</b></label>
			<div class="col-sm-12">
				<input type="text" class="form-control" id="vatCertificate"
					name="vatCertificate" placeholder="Введите свидетельства" value="${company.vatCertificate}">
			</div>
		</div>	
	</div>
	
	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />