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

	<p>
	<p>
	<a class="btn btn-primary" href="listAllSPD" role="button">Вернуться к списку СПД</a>
	<p>
	<h1>
		СПД
		<c:out value="${spd.alias}" />
	</h1>
	<p>
		<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#main" role="tab">Основные данные</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#agreement" role="tab">Договоры</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#bankprops" role="tab">Банковские реквизиты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#payments" role="tab">Выплаты</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div class="tab-pane active" id="main" role="tabpanel">
			<p>
			<dl class="row">
				<dt class="col-sm-3">Полное ФИО:</dt>
  				<dd class="col-sm-9"><c:out	value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></dd>
			
				<dt class="col-sm-3">ИНН:</dt>
  				<dd class="col-sm-9"><c:out value="${spd.inn}" /></dd>
			
			</dl>
			
			<p>
			<ul>
				<li><b>Полное ФИО: </b> <c:out
						value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></li>
				<li><b>ИНН: </b> <c:out value="${spd.inn}" /></li>
				<li><b>Паспортные данные: </b> <c:out
						value="${empty spd.passport ? '-' : spd.passport}" /></li>
				<li><b>Адрес: </b> <c:if test="${not empty address.zip}">${address.zip}, </c:if><c:if test="${not empty address.country}">${address.country}</c:if><c:if test="${not empty address.region}">, ${address.region}</c:if><c:if test="${not empty address.city}">, ${address.city}</c:if><c:if test="${not empty address.street}">, ${address.street}</c:if><c:if test="${not empty address.building}">, буд. ${address.building}</c:if><c:if test="${not empty address.flat}">, кв. ${address.flat}</c:if></li>
				<li><b>Данные о регистрации: </b> <c:out
						value="${regInfo.description} від " /> <fmt:formatDate
						value="${regInfo.dated}" pattern="dd.MM.yyyy" />р.</li>
			</ul>
			<p>
			<table>
				<tr>
					<form action="spd" method="get">
						<input type="hidden" name="edit"> <input type="hidden"
							name="id" value="${spd.id}">
						<button type="submit" class="btn btn-warning">Редактировать данные</button>
					</form>
				</tr>
				<tr>
					<form action="spd" method="post">
						<input type="hidden" name="delete"> <input type="hidden"
							name="id" value="${spd.id}">
						<button type="submit" class="btn btn-danger">Удалить СПД (осторожно!)</button>
					</form>
				</tr>
			</table>
			<p>
		</div>
		<div class="tab-pane" id="agreement" role="tabpanel">
		<p>
			<form action="agreement" method="get">
				<input type="hidden" name="add"> 
				<input type="hidden" name="spdId" value="${spd.id}">
				<button type="submit" class="btn btn-success">Новый договор</button>
			</form>
		<p>
		<table class="table table-sm table-bordered">
				<thead class="thead-default">
					<tr>
						<th>Номер</th>
						<th>Дата</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${agreements}" var="agreement">
					<tr>
						<td valign="middle"><c:out value="${agreement.number}"/></td>
						<td valign="middle"><c:out value="${agreement.dateStart}"/></td>
						<td valign="middle">
							<table>
								<tr>
									<form action="agreement" method="get">
										<input type="hidden" name="edit"> 
										<input type="hidden" name="id" value="${agreement.id}">
										<input type="hidden" name="spdId" value="${agreement.spdId}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Подробнее</button>
									</form>
								</tr>
								<tr>
									<form action="agreement" method="post">
										<input type="hidden" name="delete"> 
										<input type="hidden" name="id" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
									</form>
								</tr>
								<tr>
									<form action="tarif" method="get">
										<input type="hidden" name="add"> 
										<input type="hidden" name="agreementId" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-success btn-sm">Новые ставки к договору</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>


		</div>
		<div class="tab-pane" id="bankprops" role="tabpanel">
		<p>
			<form action="account" method="get">
				<input type="hidden" name="add"> <input type="hidden"
					name="spdId" value="${spd.id}">
				<button type="submit" class="btn btn-success">Новый счет</button>
			</form>
		<p>
			<table class="table table-sm table-bordered">
				<thead class="thead-default">
					<tr>
						<th>Номер счета</th>
						<th>МФО</th>
						<th>Наименование банка</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${accounts}" var="account">
					<tr>
						<td valign="middle">${account.accountNumber}</td>
						<td valign="middle">${account.mfo}</td>
						<td valign="middle">${account.bankName}</td>
						<td valign="middle">
							<table>
								<tr>
									<form action="account" method="get">
										<input type="hidden" name="edit"> <input type="hidden"
											name="id" value="${account.id}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Редактировать</button>
									</form>
								</tr>
								<tr>
									<form action="account" method="post">
										<input type="hidden" name="delete"> <input
											type="hidden" name="id" value="${account.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
		<div class="tab-pane" id="payments" role="tabpanel">Выплаты...</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
