<%@ page session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../header.jsp" />

<title>Companies</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Компании</b></span>
	</nav>

	<!-- 'Add Company' -->
	<!-- Button trigger modal -->
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAgreement">Новая компания</button>
	<!-- Modal -->
	<div class="modal fade" id="modalAgreement" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"><b>Новая компания</b></h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="company" method="post">
						<input type="hidden" name="add"> 
							<div class="form-group">
								<label for="title" class="col-sm-10 control-label"><b>Название</b></label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title"
										name="title" placeholder="Введите название">
								</div>
							</div>
							<div class="form-group">
								<label for="edrpou" class="col-sm-10 control-label"><b>ЕДРПОУ</b></label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="edrpou"
										name="edrpou" placeholder="Введите код ЕДРПОУ">
								</div>
							</div>
							<div class="form-group">
								<label for="inn" class="col-sm-10 control-label"><b>ИНН</b></label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="inn"
										name="inn" placeholder="Введите ИНН">
								</div>
							</div>
							<div class="form-group">
								<label for="vatCertificate" class="col-sm-10 control-label"><b>№ свидетельства плат. НДС</b></label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="vatCertificate"
										name="vatCertificate" placeholder="Введите № свид-ва НДС">
								</div>
							</div>
						<p>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
							<sec:csrfInput/>
							<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<p>

	<table class="table table-sm table-bordered table-hover">
		<thead class="thead-inverse">
			<tr align="center">
				<th>ID</th>
				<th>Название</th>
				<th>ЕДРПОУ</th>
				<th>Подробнее/Удалить</th>
			</tr>
		</thead>

		<c:forEach items="${companies}" var="company">
			<tr>
				<td onclick="goToAddress('${company.url}')">${company.id}</td>
				<td onclick="goToAddress('${company.url}')">${company.title}</td>
				<td onclick="goToAddress('${company.url}')">${company.edrpou}</td>
				<td>
					<div class="btn-group" role="group">
						<a class="btn btn-warning btn-sm" href="${company.url}" role="button">Подробнее</a>
						<form action="company" method="post">
							<input type="hidden" name="delete"> 
							<input type="hidden" name="id" value="${company.id}"> 
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