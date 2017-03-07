<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:include page="../header.jsp" />

<title><c:out value="${company.title}" /></title>

<div class="container-fluid">

	<form class="form" role="form" action="company" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${company.id}">

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="companies">Компании</a>
					<span class="breadcrumb-item active"><b><c:out value="${company.title}" /></b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="companies" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
		<p>	
			
		<div class="row">
			<div class="col-3">
				<label for="title"><b>Название</b></label>
				<input type="text" class="form-control" id="title" name="title" 
					placeholder="Введите номер счета" value="${fn:replace(company.title, '"', '&quot;')}" >  
			</div>
			<div class="col-3">
				<label for="edrpou"><b>ЕДРПОУ</b></label>
				<input type="text" class="form-control" id="edrpou" name="edrpou" 
					placeholder="Введите наименование банка" value=<c:out value="${company.edrpou}"/> >
			</div>
			<div class="col-3">
				<label for="inn"><b>ИНН</b></label>
				<input type="text" class="form-control" id="inn" name="inn" 
					placeholder="Введите ИНН" value="${company.inn}">
			</div>
			<div class="col-3">
				<label for="vatCertificate"><b>№ свидетельства плат. НДС</b></label>
				<input type="text" class="form-control" id="vatCertificate"	name="vatCertificate" 
					placeholder="Введите № свидетельства" value="${company.vatCertificate}">
			</div>
		</div>
	
	</form>

	<!-- Nav tabs -->
	<p>
	
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#address" role="tab">Адрес</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#director" role="tab">Директор</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#agreements" role="tab">Связанные договоры</a></li>
	</ul>

		<p>

		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade show active" id="address" role="tabpanel">	
				<p>
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCompanyAddressAdd">Новый адрес</button>
				<!-- Modal -->
				<div class="modal fade" id="modalCompanyAddressAdd" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Новый адрес</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="companyAddress" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="companyId" value="${company.id}"> 
									<div class="row">
										<div class="col-sm">
											<label for="presentation" class="col-sm"><b>Представление</b></label> 
											<input type="text" class="form-control" id="presentation"
												name="presentation" placeholder="Введите адрес">
										</div>
									</div>
									<p>
									<div class="row">
										<div class="col-sm">
											<label for="dateStart" class="col-sm"><b>Дата начала действия</b></label> 
											<input type="date" class="form-control" id="dateStart"
												name="dateStart" placeholder="Введите дату начала действия">
										</div>
									</div>
									<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
									<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<p>
			<table class="table table-sm table-bordered">
				<thead class="thead-default">
					<tr>
						<th>ID</th>
						<th>Адрес</th>
						<th>Дата начала</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${company.addresses}" var="address">
					<tr>
						<td><c:out value="${address.id}"/></td>
						<td><c:out value="${address.presentation}"/></td>
						<td><c:out value="${address.dateStart}"/></td>
						<td>
							<div class="btn-group" role="group">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#modalCompanyAddressEdit${address.id}">Изменить</button>
								<!-- Modal -->
								<div class="modal fade" id="modalCompanyAddressEdit${address.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel"><c:out value="${company.title}"/> | Редактировать адрес</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form action="companyAddress" method="post">
													<input type="hidden" name="edit"> 
													<input type="hidden" name="id" value="${address.id}"> 
													<div class="row">
														<div class="col-sm">
															<label for="presentation" class="col-sm"><b>Представление</b></label> 
															<input type="text" class="form-control" id="presentation"
																name="presentation" placeholder="Введите адрес" value="${address.presentation}">
														</div>
													</div>
													<p>
													<div class="row">
												
														<div class="col-6">
															<label for="dateStart" class="col-sm"><b>Дата начала действия</b></label> 
															<input type="date" class="form-control" id="dateStart"
																name="dateStart" placeholder="Введите дату начала действия" value="${address.dateStart}">
														</div>
													</div>
													<p>	
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary" data-dismiss="modal">Отмена</button>
														<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
								<form action="companyAddress" method="post">
									<input type="hidden" name="delete"> 
									<input type="hidden" name="id" value="${address.id}">
									<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="tab-pane fade" id="director" role="tabpanel">...</div>
		<div class="tab-pane fade" id="agreements" role="tabpanel">...</div>
	</div>












</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />