<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="../header.jsp" />

<title>Договоры с СПД</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Договоры</b></span>
	</nav>
	
	<form action="agreement" method="get">
		<input type="hidden" name="add"> 
		<button type="submit" class = "btn btn-success">Новый договор !!!</button>
	</form>
	
	<p>

	<table class="table table-sm table-bordered table-hover">
		<thead class="thead-inverse">
			<tr align="center">
				<th>ID</th>
				<th>СПД</th>
				<th>Номер договора</th>
				<th>Дата</th>
				<th>Подробнее/Удалить</th>
			</tr>
		</thead>

		<c:forEach items="${agreements}" var="agreement">
			<c:set var="spd" value="${agreement.spd}"/>
			<tr>
				<td>${agreement.id}</td>
				<td>${spd.alias}</td>
				<td>${agreement.number}</td>
				<td>${agreement.dateStart}</td>
				<td>
					<div class="btn-group" role="group">
						<a class="btn btn-warning btn-sm" href="${agreement.url}" role="button">Подробнее</a>
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