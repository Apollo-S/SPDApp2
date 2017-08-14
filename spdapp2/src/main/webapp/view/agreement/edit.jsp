<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../header.jsp" />

<title>Edit agreement</title>

<div class="container-fluid">

	<c:set var="spd" value="${agreement.spd}"/>

	<form class="form" role="form" action="agreement" method="post">
		<input type="hidden" name="edit"> 
		<input type="hidden" name="id" value="${agreement.id}"> 

		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Список СПД</a> 
					<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
					<span class="breadcrumb-item active"><b>Договор № <c:out value="${agreement.number}" /></b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<sec:csrfInput/>
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="${spd.url}" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
		<p>
		
		<div class="row">
			<div class="col-4">
				<label for="number"><b>Номер</b></label>
				<input type="text" class="form-control" id="number" name="number" placeholder="Введите номер договора"
						value="${agreement.number}">
			</div>
			<div class="col-2">
				<label for="date"><b>Дата</b></label>
				<input type="date" class="form-control" id="dateStart" name="dateStart" placeholder="Введите дату"
						value="${agreement.dateStart}">
			</div>
			<div class="col-2"></div>
			<div class="col-2">
				<label for="company"><b>Компания-наниматель</b></label>
				<select name="company_id" class="form-control">
					<c:forEach var="company" items="${companies}">
  						<option value="${company.id}">${company.title}</option>
  					</c:forEach>
				</select>
			</div>
		</div>
	</form>
	
	<p>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
	<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#spec" role="tab">Спецификации</a></li>
		<li class="nav-item"><a class="nav-link"
			data-toggle="tab" href="#tarif" role="tab">Ставки</a></li>
		
	</ul>
	<p>
	
	<!-- Tab panes -->
	<div class="tab-content">
	
	<!-- Specification Tab -->
	<div class="tab-pane active" id="spec" role="tabpanel">

		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSpec">Добавить спецификацию</button>
		<!-- Modal -->
		<div class="modal fade bd-example-modal-sm" id="modalSpec" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-sm" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Новая спецификация</h5>
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form action="specification" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="agreementId"	value="${agreement.id}">
							
							<div class="row">
								<div class="col">
									<label for="specificationNumber"><b>№ п/п</b></label>
								</div>
								<div class="col">
									<input type="text" class="form-control" id="specificationNumber" name="specificationNumber" 
										value="${specificationNumber}">
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col">
									<label for="dateStart"><b>Дата</b></label>
								</div>
								<div class="col">
									<input type="date" class="form-control" id="dateStart" name="dateStart"
										placeholder="" value="${dateStart}">
								</div>
							</div>
							<p>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
								<sec:csrfInput/>
								<input type="submit" class="btn btn-primary" id="button" value="Добавить">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<p>
		<table class="table table-sm table-bordered table-hover">
			<thead class="thead-default">
				<tr>
					<th>#</th>
					<th>Нач. дата</th>
					<th>Кон. дата</th>
					<th>Сумма</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${agreement.specifications}" var="specification">
				<tr>
					<td onclick="goToAddress('${specification.url}')">${specification.specificationNumber}</td>
					<td onclick="goToAddress('${specification.url}')">${specification.dateStart}</td>
					<td onclick="goToAddress('${specification.url}')">${specification.dateFinish}</td>
					<td onclick="goToAddress('${specification.url}')">${specification.specificationSum}</td>
					<td>
						<div class="btn-group" role="group">
							<a class="btn btn-warning btn-sm" href="${specification.url}" role="button">Подробнее</a>
							<form action="specification" method="post">
								<input type="hidden" name="delete">
								<input type="hidden" name="id" value="${specification.id}">
<%-- 									<input type="hidden" name="spdId" value="${spd.id}"> --%>
								<sec:csrfInput/>
								<button type="submit" class="btn btn-danger btn-sm">Удалить (осторожно!)</button>
							</form>
						</div>		
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- Tarif Tab -->
	<div class="tab-pane" id="tarif" role="tabpanel">
	
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalTarif">Добавить ставки</button>
		<!-- Modal -->
		<div class="modal fade bd-example-modal-lg" id="modalTarif" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Новые ставки к Договору № ${agreement.number}</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="agreementTarif" method="post">
								<input type="hidden" name="add">
								<input type="hidden" name="agreementId" value="${agreement.id}"/>
								
								<div class="row">
									<div class="col">
										<label for="configuring"><b>Конфигурирование, грн</b></label>
										<input type="text" class="form-control" id="configuring" name="configuring">
									</div>
									<div class="col">
										<label for="programming"><b>Программирование, грн</b></label>
										<input type="text" class="form-control" id="programming" name="programming">
									</div>
									<div class="col">
										<label for="architecting"><b>Архит. доработки</b></label>
										<input type="text" class="form-control" id="architecting" name="architecting">
									</div>
								</div>
								<p>
								<div class="row">
									<div class="col"></div>
									<div class="col-4">
										<label for="dateStart" ><b>Действуют с</b></label>
										<input type="date" class="form-control" id="dateStart" name="dateStart">
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
				<thead class="thead-default">
					<tr>
						<th>Конфигурирование</th>
						<th>Программирование</th>
						<th>Архит. доработки</th>
						<th>Начало действия</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${agreement.tarifs}" var="tarif">
					<tr>
						<c:set var="openModal" value="$('#modalTarifEdit${tarif.id}').modal('show')" />
						<td onclick="${openModal}">${tarif.configuring}</td>
						<td onclick="${openModal}">${tarif.programming}</td>
						<td onclick="${openModal}">${tarif.architecting}</td>
						<td onclick="${openModal}">${tarif.dateStart}</td>
						<td>
							<div class="btn-group" role="group">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#modalTarifEdit${tarif.id}">Редактировать ставки</button>
								<!-- Modal -->
								<div class="modal fade bd-example-modal-lg" id="modalTarifEdit${tarif.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Редактирование ставок к Договору № ${agreement.number}</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form action="agreementTarif" method="post">
													<input type="hidden" name="edit">
													<input type="hidden" name="id" value="${tarif.id}"/>
													
													<div class="row">
														<div class="col">
															<label for="configuring"><b>Конфигурирование, грн</b></label>
															<input type="text" class="form-control" id="configuring" name="configuring" 
																value="${tarif.configuring}" >
														</div>
														<div class="col">
															<label for="programming"><b>Программирование, грн</b></label>
															<input type="text" class="form-control" id="programming" name="programming" 
																value="${tarif.programming}" >
														</div>
														<div class="col">
															<label for="architecting"><b>Архит. доработки</b></label>
															<input type="text" class="form-control" id="architecting" name="architecting" 
																value="${tarif.architecting}" >
														</div>
													</div>
													<p>
													<div class="row">
														<div class="col"></div>
														<div class="col-4">
															<label for="dateStart" ><b>Действуют с</b></label>
															<input type="date" class="form-control" id="dateStart" name="dateStart" 
																value="${tarif.dateStart}" >
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
								<form action="agreementTarif" method="post">
									<input type="hidden" name="delete"> 
									<input type="hidden" name="id" value="${tarif.id}">
									<sec:csrfInput/>
									<button type="submit" class="btn btn-danger btn-sm">Удалить (осторожно!)</button>
								</form>
							</div>
					
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />
