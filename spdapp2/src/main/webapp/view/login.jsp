<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>Log in SPDApp2</title>

<div class="container-fluid">
	
	<div class="row">
		<h1>Войти в систему</h1>
	</div>
	<c:url value="/login" var="loginVar" />
	<form id="appointment-form" action="${loginVar}" method="POST">
		<div class="form-group">
			<label for="make">Пользователь</label>
			<input name="custom_username" class="form-control" />
		</div>
		<div class="form-group">
			<label for="model">Пароль</label>
			<input type="password" name="custom_password" class="form-control" />
		</div>
		<sec:csrfInput/>
		<c:if test="${param.logout != null}">
			<p>Пользователь успешно вышел из системы.<p>
		</c:if>
		<c:if test="${param.error != null}">
			<p>Неверный логин и/или пароль.<p>
		</c:if>
		
		<button type="submit" id="btn-save" class="btn btnprimary">Войти</button>
	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />