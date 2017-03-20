<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../header.jsp" />

<title>${spd.alias}</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="spds">Список СПД</a> 
		<span class="breadcrumb-item active"><b>СПД <c:out value="${spd.alias}" /></b></span>
	</nav>
	
	<p>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#main" role="tab">Основные данные</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#agreement" role="tab">Договоры</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#bankprops" role="tab">Банковские реквизиты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab"
			href="#payments" role="tab">Выплаты</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
	
		<div class="tab-pane fade show active" id="main" role="tabpanel">
			<p>
			<ul>
				<c:set var="address" value="${spd.address}" />
				<c:set var="registrationInfo" value="${spd.registrationInfo}" />
				<li><b>Полное ФИО: </b> <c:out
						value="${spd.surname} ${spd.firstname} ${spd.lastname}" /></li>
				<li><b>ИНН: </b> <c:out value="${spd.inn}" /></li>
				<li><b>Паспортные данные: </b> <c:out
						value="${empty spd.passport ? '-' : spd.passport}" /></li>
				<li><b>Адрес: </b> <c:if test="${not empty address.zip}">${address.zip}, </c:if><c:if test="${not empty address.country}">${address.country}</c:if><c:if test="${not empty address.region}">, ${address.region}</c:if><c:if test="${not empty address.city}">, ${address.city}</c:if><c:if test="${not empty address.street}">, ${address.street}</c:if><c:if test="${not empty address.building}">, буд. ${address.building}</c:if><c:if test="${not empty address.flat}">, кв. ${address.flat}</c:if></li>
				<li><b>Данные о регистрации: </b> <c:out
						value="${registrationInfo.description} від " /> <fmt:formatDate
						value="${registrationInfo.dated}" pattern="dd.MM.yyyy" />р.</li>
			</ul>
			<p>
			<div class="btn-group" role="group">
				<form action="spd" method="get">
					<input type="hidden" name="edit"> <input type="hidden" name="id" value="${spd.id}">
					<button type="submit" class="btn btn-warning">Редактировать данные</button>
				</form>
				<form action="spd" method="post">
					<input type="hidden" name="delete"> <input type="hidden" name="id" value="${spd.id}">
					<button type="submit" class="btn btn-danger">Удалить СПД (осторожно!)</button>
				</form>
			</div>
		</div>
		
		<p>
		
		<div class="tab-pane fade" id="agreement" role="tabpanel">
			<p>
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAgreement">Новый договор</button>
			<!-- Modal -->
			<div class="modal fade" id="modalAgreement" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">СПД <c:out value="${spd.alias}"/> | Новый договор</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="agreement" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="spdId" value=<c:out value="${spd.id}"/>>
								<div class="row">
									<div class="col">
										<label for="number"><b>Номер</b></label>
										<input type="text" class="form-control" id="number" name="number" 
											placeholder="№ договора">
										</div>
								</div>
								<p>
								<div class="row">
									<div class="col-6">
									<label for="dateStart"><b>Дата</b></label>
										<input type="date" class="form-control" id="dateStart"
											name="dateStart" placeholder="Дата договора">
									</div>
								</div>
								<p>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
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
						<th>Номер</th>
						<th>Дата</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${spd.agreements}" var="agreement">
					<tr>
						<td valign="middle"><c:out value="${agreement.number}"/></td>
						<td valign="middle"><c:out value="${agreement.dateStart}"/></td>
						<td valign="middle">
							<div class="btn-group" role="group">
								<a class="btn btn-outline-warning btn-sm" href="${agreement.url}" role="button">Подробнее</a>
								<form action="agreement" method="post">
									<input type="hidden" name="delete"> 
									<input type="hidden" name="id" value="${agreement.id}">
									<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<p>
		
		<div class="tab-pane fade" id="bankprops" role="tabpanel">
		
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAccount">Новый счет</button>
			<!-- Modal -->
			<div class="modal fade" id="modalAccount" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">СПД <c:out value="${spd.alias}"/> | Новый счет</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="account" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="spdId" value=<c:out value="${spd.id}"/>>
								<div class="row">
									<div class="col">
										<label for="bankName"><b>Наименование банка</b></label>
										<input type="text" class="form-control" id="bankName"
											name="bankName" placeholder="Введите наименование банка">
									</div>
								</div>
								<p>	
								<div class="row">
									<div class="col-6">
										<label for="accountNumber"><b>Номер счета</b></label>
										<input type="text" class="form-control" id="accountNumber"
											name="accountNumber" placeholder="Введите номер счета">
									</div>
									<div class="col-6">
										<label for="mfo"><b>МФО</b></label>
										<input type="text" class="form-control" id="mfo"
											name="mfo" placeholder="Введите МФО">
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
						<th>Номер счета</th>
						<th>МФО</th>
						<th>Наименование банка</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${spd.accounts}" var="account">
					<tr>
						<td>${account.accountNumber}</td>
						<td>${account.mfo}</td>
						<td>${account.bankName}</td>
						<td>
							<div class="btn-group" role="group">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-outline-warning btn-sm" data-toggle="modal" data-target="#modalSPDAccountEdit${account.id}">Редактировать</button>
								<!-- Modal -->
								<div class="modal fade" id="modalSPDAccountEdit${account.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel"><c:out value="${spd.alias}"/> | Редактирование счета</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form action="account" method="post">
													<input type="hidden" name="edit"> 
													<input type="hidden" name="id" value="${account.id}"> 
													<div class="row">
														<div class="col">
															<label for="bankName"><b>Наименование банка</b></label>
															<input type="text" class="form-control" id="bankName"
																name="bankName" placeholder="Введите наименование банка" value="${fn:replace(account.bankName, '"', '&quot;')}">
														</div>
													</div>
													<p>	
													<div class="row">
														<div class="col-6">
															<label for="accountNumber"><b>Номер счета</b></label>
															<input type="text" class="form-control" id="accountNumber"
																name="accountNumber" placeholder="Введите номер счета" value="${account.accountNumber}">
														</div>
														<div class="col-6">
															<label for="mfo"><b>МФО</b></label>
															<input type="text" class="form-control" id="mfo"
																name="mfo" placeholder="Введите МФО" value="${account.mfo}">
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
								<form action="account" method="post">
									<input type="hidden" name="delete"> 
									<input type="hidden" name="id" value="${account.id}">
									<button type="submit" class="btn btn-outline-danger btn-sm">Удалить</button>
								</form>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<div class="tab-pane fade" id="payments" role="tabpanel">
			<p>
			<p>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalPayment">Добавить...</button>
			<p>
			<!-- Modal -->
			<div class="modal fade" id="modalPayment" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Выплата | Добавление</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
						<form action="payment" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="spdId" value="${spd.id}">
							
							<div class="row">
								<div class="col">
									<label for="value"><b>Тип выплаты</b></label>
									<select class="form-control" name="payment_type_id">
										<c:forEach items="${paymentTypes}" var="paymentType">
											<option value="${paymentType.id}">${paymentType.title}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col-6">
									<label for="value"><b>Сумма</b></label>
									<input type="text" class="form-control" id="value" placeholder="Введите сумму"
										name="value">
								</div>
							</div>
							<p>
							<div class="row">
								<div class="col">
									<label for="dateStart"><b>Действует с</b></label>
									<input type="date" class="form-control" id="dateStart" name="dateStart"
										placeholder="" >
								</div>
								<div class="col">			
									<label for="dateFinish"><b>Действует по</b></label>
									<input type="date" class="form-control" id="dateFinish" name="dateFinish"
										placeholder="" >
								</div>
							</div>			
							<p>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
								<input type="submit" class="btn btn-primary" id="button" value="Добавить">
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
						<th>Наименование</th>
						<th>Сумма</th>
						<th>Действует с</th>
						<th>Действует по</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${spd.payments}" var="payment">
				<c:set var="paymentType" value="${payment.paymentType}" />
					<tr>
						<td>${paymentType.title}</td>
						<td>
							<c:choose>
								<c:when test="${paymentType.isPercent == true}" >
									<fmt:formatNumber type="percent" minFractionDigits="2" value="${payment.value}" /> 
								</c:when>
								<c:otherwise>
									<c:out value="${payment.value}" />
								</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${payment.dateStart}" /></td>
						<td><fmt:formatDate pattern="dd.MM.yyyy" value="${payment.dateFinish}" /></td>
						<td>
							<div class="btn-group" role="group">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-outline-warning btn-sm" data-toggle="modal" data-target="#modalPaymentEdit${payment.id}">Редактировать</button>
								<p>
								<!-- Modal -->
								<div class="modal fade" id="modalPaymentEdit${payment.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Выплата | Редактирование</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
											<form action="payment" method="post">
												<input type="hidden" name="edit">
												<input type="hidden" name="id" value="${payment.id}">
												
												<div class="row">
													<div class="col">
														<label for="value"><b>Тип выплаты</b></label>
														<select class="form-control" name="payment_type_id">
															<c:forEach var="paymentType" items="${paymentTypes}">
																<c:choose>
																	<c:when test="${paymentType == payment.paymentType}">
																		<option value="${paymentType.id}" selected>${paymentType.title}</option>
																	</c:when>
																	<c:otherwise>
																		<option value="${paymentType.id}">${paymentType.title}</option>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														</select>
													</div>
												</div>
												<p>
												<div class="row">
													<div class="col-6">
														<label for="value"><b>Сумма</b></label>
															<input type="text" class="form-control" id="value" placeholder="Введите сумму"
																name="value" value="${payment.value}" >
													</div>
												</div>
												<p>
												<div class="row">
													<div class="col">			
														<label for="dateStart"><b>Действует с</b></label>
														<input type="date" class="form-control" id="dateStart" name="dateStart"
															placeholder="" value="${payment.dateStart}">
													</div>
													<div class="col">			
														<label for="dateFinish"><b>Действует по</b></label>
														<input type="date" class="form-control" id="dateFinish" name="dateFinish"
															placeholder="" value="${payment.dateFinish}">
													</div>
												</div>			
												<p>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary" data-dismiss="modal">Закрыть</button>
													<input type="submit" class="btn btn-primary" id="button" value="Сохранить">
												</div>
											</form>
											</div>
										</div>
									</div>
								</div>
								<p>
								<form action="payment" method="post">
									<input type="hidden" name="delete">
									<input type="hidden" name="id" value="${payment.id}">
									<button type="submit" class="btn btn-outline-danger btn-sm">Удалить</button>
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