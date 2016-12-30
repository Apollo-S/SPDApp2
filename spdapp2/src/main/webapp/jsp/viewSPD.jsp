<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${spd.alias}</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">

<body>

	<h1>
		СПД
		<c:out value="${spd.alias}" />
	</h1>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#main" role="tab">Основные данные</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#agreement" role="tab">Договора</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#bankprops" role="tab">Банковские реквизиты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#payments" role="tab">Выплаты</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane active" id="main" role="tabpanel">
			<ul>
				<li><b>Полное ФИО: </b> <c:out
						value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></li>
				<li><b>ИНН: </b> <c:out value="${spd.inn}" /></li>
				<li><b>Паспортные данные: </b> <c:out
						value="${empty spd.passport ? '-' : spd.passport}" /></li>
				<li><b>Адрес: </b> <c:if test="${not empty address.zip}">${address.zip}, </c:if>
					<c:if test="${not empty address.country}">${address.country}</c:if>
					<c:if test="${not empty address.region}">, ${address.region}</c:if>
					<c:if test="${not empty address.city}">, ${address.city}</c:if> <c:if
						test="${not empty address.street}">, ${address.street}</c:if> <c:if
						test="${not empty address.building}">, буд. ${address.building}</c:if>
					<c:if test="${not empty address.flat}">, кв. ${address.flat}</c:if></li>
				<li><b>Данные о регистрации: </b> <c:out
						value="${regInfo.description} від " /> <fmt:formatDate
						value="${regInfo.dated}" pattern="dd.MM.yyyy" />р.</li>
			</ul>
		</div>
		<div class="tab-pane" id="agreement" role="tabpanel">Договора...</div>
		<div class="tab-pane" id="bankprops" role="tabpanel">Банковские реквизиты...</div>
		<div class="tab-pane" id="payments" role="tabpanel">Выплаты...</div>
	</div>

	<table>
		<tr>
			<form action="listAllSPD" method="get">
				<button type="submit" class="btn btn-default">Вернуться к
					списку СПД</button>
			</form>
		</tr>
		<tr>
			<form action="spd" method="get">
				<input type="hidden" name="edit"> <input type="hidden"
					name="id" value="${spd.id}">
				<button type="submit" class="btn btn-warning">Редактировать</button>
			</form>
		</tr>
		<tr>
			<form action="spd" method="post">
				<input type="hidden" name="delete"> <input type="hidden"
					name="id" value="${spd.id}">
				<button type="submit" class="btn btn-danger">Удалить</button>
			</form>
		</tr>
	</table>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
