<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<title>Edit calculation</title>

<div class="container-fluid">
	
	<form class="form" role="form" action="calculation" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${calculation.id}">
	
		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Список СПД</a> 
					<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
					<a class="breadcrumb-item" href="${agreement.url}">Договор <c:out value="${agreement.number}" /></a>
					<a class="breadcrumb-item" href="${specification.url}"><b>Спецификация № <c:out value="${specification.specificationNumber}" /> от 
														<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</b></a>
					<span class="breadcrumb-item active"><b>Расчет № <c:out value="${calculation.partNumber}" /> за 
														<fmt:formatDate	value="${calculation.dateStart}" pattern="MMMM yyyy" />г.</b></span>
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
	
	</form>
	

	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />