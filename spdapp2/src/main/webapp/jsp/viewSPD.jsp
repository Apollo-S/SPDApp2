<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h1>${spd.alias}</h1>
	<ul>
		<li><b>Фамилия, имя, отчество:</b> ${spd.surname} ${spd.firstname}	${spd.lastname}</li>
		<li><b>ИНН:</b> ${spd.inn}</li>
		<li><b>Паспортные данные:</b> ${spd.passport}</li>
	</ul>
	
		<form action="spd" method="get">
			<input type="hidden" name="edit"> 
			<input type="hidden" name="id" value="${spd.id}">
			<button type="submit" class = "btn btn-warning">Редактировать</button>
		</form>
		<form action="listAllSPD" method="get">
			<button type="submit" class = "btn btn-default">Вернуться к списку СПД</button>
		</form>
		<form action="spd" method="post">
			<input type="hidden" name="delete"> 
			<input type="hidden" name="id" value="${spd.id}">
			<button type="submit" class = "btn btn-danger">Удалить</button>
		</form>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
