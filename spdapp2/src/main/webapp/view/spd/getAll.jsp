<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<title>SPD List</title>

<body>

	<form action="main" method="get">
		<button class="btn btn-primary">На главную</button>
	</form>
	<p>
	<form action="spd" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">Новый контрагент</button>
	</form>

	<h2 align="center">Список СПД</h2>

	<table class="table table-sm table-bordered table-stripped">
		<thead class="thead-inverse">
			<tr>
				<th align="center">ID</th>
				<th align="center">Alias</th>
				<th align="center">ИНН</th>
				<th align="center">Подробнее</th>
				<th align="center">Удалить</th>
			</tr>
		</thead>

		<c:forEach items="${spdList}" var="spd">
			<tr>
				<td>${spd.id}</td>
				<td>${spd.alias}</td>
				<td>${spd.inn}</td>
				<td>
					<form action="spd" method="get">
						<input type="hidden" name="id" value="${spd.id}"> 
						<button type="submit" class = "btn btn-success">Подробнее</button>
					</form>
				</td>
				<td>
					<form action="spd" method="post">
						<input type="hidden" name="delete"> 
						<input type="hidden" name="id" value="${spd.id}"> 
						<button type="submit" class = "btn btn-danger">Удалить</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
	
</body>
</html>