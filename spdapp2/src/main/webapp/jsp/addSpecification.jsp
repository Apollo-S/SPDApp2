<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add new specification</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">

<body>
	<h1 align="center">СПД <c:out value="${spd.alias}"/> => Добавление новой спецификации <br>к договору № <c:out value="${agreement.number}"/> </h1>
	<p>
	<form class="form" role="form" action="specification" method="post">
		<input type="hidden" name="add">
		<input type="hidden" name="agreementId" value="${agreement.id}">
		<input type="hidden" name="spdId" value="${spd.id}">
		<table border="0" width="50%">
			
			<tr>
				<td valign="top">

					<div class="form-group">
						<label for="specNumber" class="col-sm-10 control-label">Номер
							п/п</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="specNumber"
								name="specNumber" value="${specNumber}" disabled>
						</div>
					</div>

					<div class="form-group">
						<label for="dateStart" class="col-sm-10 control-label">Дата спецификации</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateStart"
								name="dateStart" placeholder="Введите дату спецификации" value="${dateStart}">
						</div>
					</div>
				
			</tr>
		</table>
		<!-- ---------------------------------------------------------------------------------------- -->
		<div class="form-group">
			<label for="button" class="col-sm-10 control-label"></label>
			<div class="col-sm-offset-10 col-sm-10">
				<input type="submit" class="btn btn-success" id="button"
					value="Сохранить"> <br> <a href="listAllSPD">Вернуться
					к списку СПД</a>
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