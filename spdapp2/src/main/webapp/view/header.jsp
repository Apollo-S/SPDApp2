<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-datepicker3.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/font-awesome.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/styles.css" />" rel="stylesheet">

</head>

<body style="background-color: #FFFFFF;">



	<nav class="navbar fixed-top navbar-toggleable-md navbar-inverse" style="background-color: #191970;"> <!-- colour: MidnightBlue -->
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<c:url value="/" var="urlValue"></c:url>
		<a class="navbar-brand" href="${urlValue}">SPDApp2</a>
		
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<c:url value="/main" var="urlMain"></c:url>
					<a class="nav-item nav-link" href="${urlMain}">Главная</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Справочники
					</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="taxes">Налоги (не работает)</a>
						<a class="dropdown-item" href="spds">Список СПД</a>
						<a class="dropdown-item" href="companies">Компании</a>
						<a class="dropdown-item" href="agreements">Договоры с СПД</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link disabled" href="#">About</a>
				</li>
			</ul>
		
			<sec:authorize access="authenticated" var="authenticated" />
			<c:choose>
				<c:when test="${authenticated}">
					<sec:authentication property="name" var="userName"/>
					<span class="navbar-text">
						<c:out value="${userName}" /> | 
						<a id ="logout" href="#">Logout</a>
					</span>
					<form id="logout-form" action="<c:url value="/logout"/>" method="post">
						<sec:csrfInput/>
					</form>
				</c:when>
				<c:otherwise>
					<span class="navbar-text">
						<a href="<spring:url value="/login"/>">Login</a>
					</span>
				</c:otherwise>
			</c:choose>
		</div>
	</nav>
	
	<p class="p4">