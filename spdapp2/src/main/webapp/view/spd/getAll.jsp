<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<jsp:include page="../header.jsp" />

<title>Предприниматели</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Список СПД</b></span>
	</nav>
	
	<form action="spd" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">
			<i class="fa fa-plus" ></i><c:out value=" Новый контрагент" />
		</button>
	</form>
	
	<p>

	<table class="table table-sm table-bordered table-hover">
		<thead class="thead-inverse">
			<tr>
				<th class="text-center">ID</th>
				<th class="text-center">ФИО</th>
				<th class="text-center">ИНН</th>
				<th class="text-center">Действия</th>
			</tr>
		</thead>

		<c:forEach items="${spds}" var="spd">
			<tr>
				<td class="text-center" onclick="goToAddress('${spd.url}')">${spd.id}</td>
				<td class="text-center" onclick="goToAddress('${spd.url}')">${spd.alias}</td>
				<td class="text-center" onclick="goToAddress('${spd.url}')">${spd.inn}</td>
				<td class="text-center">
					<div class="btn-group" role="group">
						<a class="btn btn-warning btn-sm" href="${spd.url}" role="button">Подробнее</a>
						<form action="spd" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${spd.id}"> 
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