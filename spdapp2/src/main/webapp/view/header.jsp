<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/> -->
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">

</head>

<body style="background-color: #FFFFFF;">

	<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse" style="background-color: #191970;"> <!-- colour: MidnightBlue -->
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="main">SPDApp2</a>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="nav navbar-nav">
				<a class="nav-item nav-link" href="main">Главная</a> 
				<li class="nav-item dropdown">
			        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			          Справочники
			        </a>
			        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
			          <a class="dropdown-item" href="taxes">Налоги</a>
			          <a class="dropdown-item" href="spds">Список СПД</a>
			          <a class="dropdown-item" href="companies">Компании</a>
			          <a class="dropdown-item" href="agreements">Договоры с СПД</a>
			          <a class="dropdown-item" href="#">Something else here</a>
			        </div>
		      	</li>
				<a class="nav-item nav-link" href="features">Features</a> 
				<a class="nav-item nav-link disabled" href="#">Disabled</a>
			</div>
		</div>
	</nav>
	
	<p class="p4">