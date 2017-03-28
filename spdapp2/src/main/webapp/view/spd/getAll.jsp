<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../header.jsp" />

<title>SPD List</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Список СПД</b></span>
	</nav>
	
	<form action="spd" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">Новый контрагент</button>
	</form>
	
	<p>

	<table class="table table-sm table-bordered table-hover">
		<thead class="thead-inverse">
			<tr>
				<th>ID</th>
				<th>ФИО</th>
				<th>ИНН</th>
				<th>Подробнее/Удалить</th>
			</tr>
		</thead>

		<c:forEach items="${spds}" var="spd">
			<tr>
				<td onclick="goToAddress('${spd.url}')">${spd.id}</td>
				<td onclick="goToAddress('${spd.url}')">${spd.alias}</td>
				<td onclick="goToAddress('${spd.url}')">${spd.inn}</td>
				<td>
					<div class="btn-group" role="group">
						<a class="btn btn-warning btn-sm" href="${spd.url}" role="button">Подробнее</a>
						<form action="spd" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${spd.id}"> 
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