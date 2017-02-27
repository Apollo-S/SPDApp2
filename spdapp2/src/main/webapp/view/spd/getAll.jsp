<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp" />

<title>SPD List</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="main">Главная</a>
		<span class="breadcrumb-item active"><b>Список СПД</b></span>
	</nav>
	
	<form action="spd" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">Новый контрагент</button>
	</form>
	
	<p>

	<table class="table table-sm table-bordered table-stripped">
		<thead class="thead-inverse">
			<tr>
				<th align="center">ID</th>
				<th align="center">ФИО</th>
				<th align="center">ИНН</th>
				<th align="center">Подробнее/Удалить</th>
			</tr>
		</thead>

		<c:forEach items="${spdList}" var="spd">
			<tr>
				<td>${spd.id}</td>
				<td>${spd.alias}</td>
				<td>${spd.inn}</td>
				<td>
					<div class="btn-group" role="group">
						<a class="btn btn-success" href="${spd.url}" role="button">Подробнее</a>
						<form action="spd" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${spd.id}"> 
							<button type="submit" class="btn btn-danger">Удалить</button>
						</form>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>

<!-- footer -->
<jsp:include page="../footer.jsp" />