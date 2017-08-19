<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Пользователи</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Пользователи</b></span>
	</nav>
	
	<form action="user" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">
			<i class="fa fa-plus" ></i><c:out value=" Новый пользователь" />
		</button>
	</form>
	
	<p>

	<table class="table table-sm table-bordered table-hover">
		<thead class="thead-inverse">
			<tr>
				<th class="text-center">Логин</th>
				<th class="text-center">Права доступа</th>
				<th class="text-center">Действия</th>
			</tr>
		</thead>

		<c:forEach items="${users}" var="user">
			<tr>
				<td class="text-center" onclick="goToAddress('${user.url}')">${user.username}</td>
				<td class="text-center" onclick="goToAddress('${user.url}')">${user.role}</td>
				<td class="text-center">
					<div class="btn-group" role="group">
						<a class="btn btn-warning btn-sm" href="${user.url}" role="button">Подробнее</a>
						<form action="user" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${user.id}"> 
							<sec:csrfInput/>
							<button type="submit" class="btn btn-danger btn-sm">Удалить</button>
						</form>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />