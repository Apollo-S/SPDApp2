<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit agreement</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">

<body>
	<h1 align="center">СПД <c:out value="${spd.alias}"/> | Просмотр договора</h1>
	<p>
	<form class="form" role="form" action="agreement" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${agreement.id}">
		<table border="0" width="50%">
			
			<tr>
				<td valign="top">
					<div class="form-group">
						<input type="hidden" class="form-control" id="spdId"
							name="spdId" value="${agreement.spdId}">
					</div>
					<div class="form-group">
						<label for="number" class="col-sm-10 control-label">Номер</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="number"
								name="number" placeholder="Введите номер договора" value="${agreement.number}">
						</div>
					</div>
					<div class="form-group">
						<label for="dateStart" class="col-sm-6 control-label">Дата</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateStart"
								name="dateStart" placeholder="Введите дату" value="${agreement.dateStart}">
						</div>
					</div>
			</tr>
		</table>
		<p>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
			<li class="nav-item"><a class="nav-link active"
				data-toggle="tab" href="#tarif" role="tab">Ставки</a></li>
			<li class="nav-item"><a class="nav-link" data-toggle="tab"
				href="#spec" role="tab">Спецификации</a></li>
		</ul>
		<p>
		<!-- Tab panes -->
		
		<div class="tab-content">
			<div class="tab-pane active" id="tarif" role="tabpanel">
				<p>
					
			<a href="tarif?add=&agreementId=${agreement.id}">Добавить тариф</a>
				
				<p>
				<table class="table table-sm table-bordered">
				<thead class="thead-default">
					<tr>
						<th>Конфигурирование</th>
						<th>Программирование</th>
						<th>Архит. доработки</th>
						<th>Начало действия</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${tarifs}" var="tarif">
					<tr>
						<td valign="middle">${tarif.configuring}</td>
						<td valign="middle">${tarif.programming}</td>
						<td valign="middle">${tarif.architecting}</td>
						<td valign="middle">${tarif.dateStart}</td>
						<td>
						<table>
								<tr>
									<form action="tarif" method="get">
										<input type="hidden" name="edit"> 
										<input type="hidden" name="id" value="${tarif.id}">
										<input type="hidden" name="agreementId" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Подробнее</button>
									</form>
								</tr>
								<tr>
									<form action="tarif" method="post">
										<input type="hidden" name="delete"> 
										<input type="hidden" name="id" value="${tarif.id}">
										<input type="hidden" name="spdId" value="${agreement.spdId}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
									</form>
								</tr>
							</table>
						
						</td>
					</tr>
				</c:forEach>
			</table>
			</div>
			<div class="tab-pane" id="spec" role="tabpanel">...</div>
		</div>




		<!-- ---------------------------------------------------------------------------------------- -->
		<div class="form-group">
			<label for="button" class="col-sm-10 control-label"></label>
			<div class="col-sm-offset-10 col-sm-10">
				<input type="submit" class="btn btn-success" id="button"
					value="ОК"> 
					<p>
					<a href=<c:out value="spd?id=${spd.id}"/>>Вернуться к текущему СПД</a>
					<p>
					<a href="listAllSPD">Вернуться к списку СПД</a>
			</div>
		</div>
	</form>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>