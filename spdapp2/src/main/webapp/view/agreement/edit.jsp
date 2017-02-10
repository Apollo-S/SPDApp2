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

<body>
	<c:set var="spd" value="${agreement.spd}" />>
	<p>
	<form class="form" role="form" action="agreement" method="post">
		<input type="hidden" name="edit"> 
		<input type="hidden" name="id" value="${agreement.id}"> 
		<input type="hidden" name="spdId" value="${spd.id}">

		<table>
			<tr>
				<a class="btn btn-primary" href="getAllSPD" role="button">Вернуться к списку СПД</a>
			</tr>
			<tr>
				<input type="submit" class="btn btn-success" id="button" value="Сохранить и вернуться к СПД ${spd.alias}">
			</tr>
			<tr>
				<a class="btn btn-primary" href="spd?id=${spd.id}" role="button">Вернуться без изменений</a>
			</tr>
		</table>

		<p>
		<h1 align="center">
			СПД
			<c:out value="${spd.alias}" />
			| Просмотр договора
		</h1>

		<p>
		<table border="0" width="50%">
			<tr>
				<td valign="top">
					<div class="form-group">
						<label for="number" class="col-sm-10 control-label">Номер</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="number" name="number" placeholder="Введите номер договора"
								value="${agreement.number}">
						</div>
					</div>
					<div class="form-group">
						<label for="dateStart" class="col-sm-6 control-label">Дата</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateStart" name="dateStart" placeholder="Введите дату"
								value="${agreement.dateStart}">
						</div>
					</div>
			</tr>
		</table>
	</form>
	
	<p>

		<!-- Nav tabs -->
		<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
				href="#spec" role="tab">Спецификации</a></li>
			<li class="nav-item"><a class="nav-link"
				data-toggle="tab" href="#tarif" role="tab">Ставки</a></li>
			
		</ul>
		<p>
		
<!-- 		Specification Tab -->
		<!-- Tab panes -->
		<div class="tab-content">
		
		<div class="tab-pane active" id="spec" role="tabpanel">

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSpec">Добавить спецификацию</button>

			<!-- Modal -->
			<div class="modal fade" id="modalSpec" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Новая спецификация</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<form action="specification" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="agreementId"	value="${agreement.id}">
							<input type="hidden" name="spdId" value="${spd.id}">
							<table border="0" width="50%">
								<tr>
									<td valign="top">
										<div class="form-group">
											<label for="specNumber" class="col-sm-10 control-label">Номер спецификации (п/п)</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="specNumber" name="specNumber" value="${specNumber}">
											</div>
										</div>
										<div class="form-group">
											<label for="dateStart" class="col-sm-10 control-label">Дата спецификации</label>
											<div class="col-sm-10">
												<input type="date" class="form-control" id="dateStart" name="dateStart"
													placeholder="Введите дату спецификации" value="${dateStart}">
											</div>
										</div>
									</td>
								</tr>
							</table>
						<p>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
							<input type="submit" class="btn btn-primary" id="button" value="Добавить">
						</div>
					</form>
					</div>
				</div>
			</div>
		</div>

			<p>
			<table class="table table-sm table-bordered">
				<thead class="thead-default">
					<tr>
						<th>#</th>
						<th>Нач. дата</th>
						<th>Кон. дата</th>
						<th>Сумма</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${agreement.specifications}" var="specification">
					<tr>
						<td valign="middle">${specification.specificationNumber}</td>
						<td valign="middle">${specification.dateStart}</td>
						<td valign="middle">${specification.dateFinish}</td>
						<td valign="middle">${specification.specificationSum}</td>
						<td>
							<table>
								<tr>
									<form action="specification" method="get">
										<input type="hidden" name="edit">
										<input type="hidden" name="id" value="${specification.id}">
										<input type="hidden" name="agreementId" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Подробнее</button>
									</form>
								</tr>
								<tr>
									<form action="specification" method="post">
										<input type="hidden" name="delete">
										<input type="hidden" name="id" value="${specification.id}">
										<input type="hidden" name="spdId" value="${spd.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
<!-- 		Tarif Tab -->
			<div class="tab-pane" id="tarif" role="tabpanel">
			<p>

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalTarif">Добавить ставки</button>

			<!-- Modal -->
		
			<div class="modal fade" id="modalTarif" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Новые ставки к Договору № ${agreement.number}</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="tarif" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="spdId" value="${spd.id}">
								<table border="0" width="120%">
									<tr>
										<td valign="top">
											<div class="form-group">
												<input type="hidden" class="form-control" id="agreementId" name="agreementId"
													value=<c:out value="${agreement.id}"/>>
											</div>
											<div class="form-group">
												<label for="configuring" class="col-sm-10 control-label"><b>Конфигурирование</b></label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="configuring" name="configuring"
														placeholder="Введите сумму (конфигурирование)">
												</div>
											</div>
											<div class="form-group">
												<label for="programming" class="col-sm-10 control-label"><b>Программирование</b></label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="programming" name="programming"
														placeholder="Введите сумму (программирование)">
												</div>
											</div>
											<div class="form-group">
												<label for="architecting" class="col-sm-10 control-label"><b>Архит. доработки</b></label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="architecting" name="architecting"
														placeholder="Введите сумму (арх. доработки)">
												</div>
											</div>
											<div class="form-group">
												<label for="dateStart" class="col-sm-6 control-label"><b>Дата начала действия</b></label>
												<div class="col-sm-10">
													<input type="date" class="form-control" id="dateStart" name="dateStart" placeholder="Введите дату"
														value="${dateStart}">
												</div>
											</div>
									</tr>
								</table>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
									<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

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
				<c:forEach items="${agreement.tarifs}" var="tarif">
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
										<input type="hidden" name="spdId" value="${spd.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<!-- ---------------------------------------------------------------------------------------- -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

</body>
</html>