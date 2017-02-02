<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add new bank props</title>
</head>

<body>
	<h1 align="center">СПД <c:out value="${spd.alias}"/> | Добавление нового счета</h1>
	<p>
	<form class="form" role="form" action="account" method="post">
		<input type="hidden" name="add">
		
		<table border="0" width="50%">
			
			<tr>
				<td valign="top">
					<div class="form-group">
						<input type="hidden" class="form-control" id="spdId"
							name="spdId" value="${spd.id}">
					</div>
					<div class="form-group">
						<label for="accountNumber" class="col-sm-10 control-label">Номер счета</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="accountNumber"
								name="accountNumber" placeholder="Введите номер счета">
						</div>
					</div>
					<div class="form-group">
						<label for="bankName" class="col-sm-6 control-label">Наименование банка</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="bankName"
								name="bankName" placeholder="Введите наименование банка">
						</div>
					</div>
					<div class="form-group">
						<label for="mfo" class="col-sm-10 control-label">МФО</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mfo"
								name="mfo" placeholder="Введите МФО">
						</div>
					</div>
			</tr>
		</table>
		<!-- ---------------------------------------------------------------------------------------- -->
		<div class="form-group">
			<label for="button" class="col-sm-10 control-label"></label>
			<div class="col-sm-offset-10 col-sm-10">
				<input type="submit" class="btn btn-success" id="button"
					value="Сохранить"> <br> <a href="geAllSPD">Вернуться
					к списку СПД</a>
			</div>
		</div>
	</form>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</body>
</html>