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
					<label for="username"><strong>Логин</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">
							<i class="fa fa-user-circle" style="font-size:24px;"></i>
						</span> 
						<input name="username" class="form-control" />
					</div>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<label for="password"><strong>Пароль</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">
							<i class="fa fa-key" style="font-size:24px;"></i>
						</span> 
						<input type="password" name="password" class="form-control" />
					</div>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<label for="firstName"><strong>Имя</strong></label>
					<input name="firstName" class="form-control" />
				</div>
				<div class="col">
					<label for="lastName"><strong>Фамилия</strong></label>
					<input name="lastName" class="form-control" />
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<label for="email"><strong>Email</strong></label>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="input-group">
						<span class="input-group-addon" id="basic-addon1">
							<i class="fa fa-at" style="font-size:27px;"></i>
						</span> 
						<input name="email" class="form-control" />
					</div>
				</div>
			</div>
			<br>
			<sec:csrfInput/>
			<button id="btn-save" class="btn btn-lg btn-primary btn-block" type="submit"><i class="fa fa-user-plus"></i> Зарегистрироваться</button>
		</form>
	</div>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />