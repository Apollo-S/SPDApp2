<%@ page session="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Add new SPD</title>
</head>

<link href="css/bootstrap.min.css" rel="stylesheet">

<body>
	<h1 align="center">Добавление нового СПД</h1>
	<p>
	<form class="form" role="form" action="viewSPD" method="post">

		<input type="hidden" name="add">

		<div class="form-group">
			<label for="surname" class="col-sm-10 control-label">Фамилия</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="surname" name="surname"
					placeholder="Введите фамилию">
			</div>
		</div>

		<div class="form-group">
			<label for="firstname" class="col-sm-6 control-label">Имя</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="firstname"
					name="firstname" placeholder="Введите имя">
			</div>
		</div>

		<div class="form-group">
			<label for="lastname" class="col-sm-10 control-label">Отчество</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="lastname"
					name="lastname" placeholder="Введите отчество">
			</div>
		</div>

		<div class="form-group">
			<label for="inn" class="col-sm-10 control-label">ИНН</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" id="inn" name="inn"
					placeholder="Введите идентификационный номер">
			</div>
		</div>

		<div class="form-group">
		<label for="button" class="col-sm-10 control-label"></label>
			<div class="col-sm-offset-10 col-sm-10">
				<input type="submit" class="btn btn-success" id="button" value="Сохранить">
				<br>
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