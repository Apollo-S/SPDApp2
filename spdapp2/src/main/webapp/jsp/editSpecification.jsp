<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit specification</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">

<body>
	<p>
	
	<form class="form" role="form" action="specification" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${specification.id}">
		<input type="hidden" name="agreementId" value="${agreement.id}">
		<input type="hidden" name="spdId" value="${agreement.spdId}">

	<table>
		<tr>
			<a class="btn btn-primary" href="listAllSPD" role="button">Вернуться к списку СПД</a>
		</tr>
		<tr>
			<input type="submit" class="btn btn-success" id="button" value="Записать и вернуться к Договору">
		</tr>
		<tr>
			<a class="btn btn-primary" href="agreemnt?id=${agreement.id}" role="button">Закрыть без изменений</a>
		</tr>
	</table>
	
	<p>
	<h1 align="center">Спецификация № <c:out value="${specification.specificationNumber}"/> к договору № <c:out value="${agreement.number}"/> </h1>
	<p>

		<table border="0" width="50%">			
			<tr>
				<td valign="top">
					<div class="form-group">
						<label for="specificationNumber" class="col-sm-10 control-label">specificationNumber</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="specificationNumber"
								name="specificationNumber" placeholder="specificationNumber" value=<c:out value="${specification.specificationNumber}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="agreementTarifId" class="col-sm-10 control-label">Тарифная сетка Id</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="agreementTarifId"
								name="agreementTarifId" placeholder="Введите сумму (конфигурирование)" value=<c:out value="${specification.agreementTarifId}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="specificationSum" class="col-sm-10 control-label">Сумма</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="specificationSum"
								name="specificationSum" placeholder="Введите сумму (программирование)" value=<c:out value="${specification.specificationSum}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="companyId" class="col-sm-10 control-label">companyId</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="companyId"
								name="companyId" placeholder="Введите сумму (арх. доработки)" value=<c:out value="${specification.companyId}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="dateStart" class="col-sm-6 control-label">Дата начала</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateStart"
								name="dateStart" placeholder="Введите дату" value=<c:out value="${specification.dateStart}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="dateFinish" class="col-sm-6 control-label">Дата окончания</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateFinish"
								name="dateFinish" placeholder="Введите дату" value=<c:out value="${specification.dateFinish}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="configuringHours" class="col-sm-10 control-label">configuringHours</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="configuringHours"
								name="configuringHours" placeholder="Введите сумму (программирование)" value=<c:out value="${specification.configuringHours}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="programmingHours" class="col-sm-10 control-label">programmingHours</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="programmingHours"
								name="programmingHours" placeholder="Введите сумму (программирование)" value=<c:out value="${specification.programmingHours}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="architectingHours" class="col-sm-10 control-label">architectingHours</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="architectingHours"
								name="architectingHours" placeholder="Введите сумму (программирование)" value=<c:out value="${specification.architectingHours}"/>>
						</div>
					</div>
			</tr>
		</table>
		</form>
		<!-- ---------------------------------------------------------------------------------------- -->
		

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>