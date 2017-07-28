<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>Register in SPDApp2</title>

<div class="container-fluid">
	
	<div class="wrapper">
		<c:url value="/register" var="registerVar" />
		<form class="form-signin" action="${registerVar}" method="POST">
			<h2 class="form-signin-heading text-center">Регистрация</h2>
			<br>
			<div class="row">
				<div class="col">
					<label for="firstName">Имя</label>
					<input name="firstName" class="form-control" />
				</div>
				<div class="col">
					<label for="lastName">Фамилия</label>
					<input name="lastName" class="form-control" />
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<label for="username">Логин</label>
					<input name="username" class="form-control" />
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<label for="email">Email</label>
					<input name="email" class="form-control" />
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Пароль</label>
					<input type="password" name="password" class="form-control" />
				</div>
			</div>
			
			<br>
			<sec:csrfInput/>
			<button id="btn-save" class="btn btn-lg btn-primary btn-block" type="submit">Зарегистрироваться</button>
		</form>
	</div>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />