<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>Log in SPDApp2</title>

<div class="container-fluid">
	
	<div class="wrapper">
		<c:url value="/login" var="loginVar" />
		<form class="form-signin" action="${loginVar}" method="POST">
			<h2 class="form-signin-heading text-center">Войти в систему</h2>
			<br>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">
					<i class="fa fa-user-circle" style="font-size:24px;"></i>
				</span> 
				<input type="text" name="custom_username" class="form-control" placeholder="User" 
					aria-describedby="basic-addon1"/>
			</div>
			<p>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">
					<i class="	fa fa-key" style="font-size:24px;"></i>
				</span> 
				<input type="password" name="custom_password" class="form-control" placeholder="Password" 
					aria-describedby="basic-addon1"/>
			</div>
			<br>
			<sec:csrfInput/>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success text-center" role="alert">
					Пользователь успешно вышел из системы
				</div>
			</c:if>
			<c:if test="${param.error != null}">
				<div class="alert alert-danger text-center" role="alert">
					Неверный логин и/или пароль
				</div>
			</c:if>
			<button id="btn-save" class="btn btn-lg btn-primary btn-block" type="submit">Войти</button>
		</form>
	</div>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />