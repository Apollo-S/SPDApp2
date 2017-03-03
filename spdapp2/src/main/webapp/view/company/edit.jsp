<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../header.jsp" />

<title><c:out value="${company.title}" /></title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="companies">Компании</a>
		<span class="breadcrumb-item active"><b><c:out value="${company.title}" /></b></span>
	</nav>

	<p>

	<form class="form" role="form" action="company" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${company.id}">
		
		<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
		<a class="btn btn-danger" href="${spd.url}" role="button">Отмена</a>
	
		<p>
			
			<div class="form-group">
				<label for="title" class="col-sm-10 control-label"><b>Название</b></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title"
						name="title" placeholder="Введите номер счета" value="${company.title}">
				</div>
			</div>
			<div class="form-group">
				<label for="edrpou" class="col-sm-6 control-label"><b>ЕДРПОУ</b></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="edrpou"
						name="edrpou" placeholder="Введите наименование банка" value=<c:out value="${company.edrpou}"/> >
				</div>
			</div>
			<div class="form-group">
				<label for="inn" class="col-sm-10 control-label"><b>ИНН</b></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="inn"
						name="inn" placeholder="Введите МФО" value="${company.inn}">
				</div>
			</div>	
			<div class="form-group">
				<label for="vatCertificate" class="col-sm-10 control-label"><b>№ свидетельства плат. НДС</b></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="vatCertificate"
						name="vatCertificate" placeholder="Введите МФО" value="${company.vatCertificate}">
				</div>
			</div>	

	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />