<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div class="tab-pane active" id="main" role="tabpanel">
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
			<table>
				<tr>
					<form action="spd" method="get">
						<input type="hidden" name="edit"> <input type="hidden"
							name="id" value="${spd.id}">
						<button type="submit" class="btn btn-warning">Редактировать данные</button>
					</form>
				</tr>
				<tr>
					<form action="spd" method="post">
						<input type="hidden" name="delete"> <input type="hidden"
							name="id" value="${spd.id}">
						<button type="submit" class="btn btn-danger">Удалить СПД (осторожно!)</button>
					</form>
				</tr>
			</table>
			<p>
		</div>
		
<!-- 	Tab panel 'Договоры' -->
		<div class="tab-pane" id="agreement" role="tabpanel">
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
							<table border="0" width="70%">
								<tr>
									<td valign="top">
										<div class="form-group">
											<label for="number" class="col-sm-10 control-label">Номер</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="number"
													name="number" placeholder="№ договора">
											</div>
										</div>
										<div class="form-group">
											<label for="dateStart" class="col-sm-6 control-label">Дата</label>
											<div class="col-sm-10">
												<input type="date" class="form-control" id="dateStart"
													name="dateStart" placeholder="Дата договора">
											</div>
										</div>
								</tr>
							</table>
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
							<table>
								<tr>
									<form action="agreement" method="get">
										<input type="hidden" name="id" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Подробнее</button>
									</form>
								</tr>
								<tr>
									<form action="agreement" method="post">
										<input type="hidden" name="delete"> 
										<input type="hidden" name="id" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить (осторожно!)</button>
									</form>
								</tr>
								<tr>
									<form action="tarif" method="get">
										<input type="hidden" name="add"> 
										<input type="hidden" name="agreementId" value="${agreement.id}">
										<button type="submit" class="btn btn-outline-success btn-sm">Новые ставки к договору</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>


		</div>
		<div class="tab-pane" id="bankprops" role="tabpanel">
		<p>
			<form action="account" method="get">
				<input type="hidden" name="add"> <input type="hidden"
					name="spdId" value="${spd.id}">
				<button type="submit" class="btn btn-success">Новый счет</button>
			</form>
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
						<td valign="middle">${account.accountNumber}</td>
						<td valign="middle">${account.mfo}</td>
						<td valign="middle">${account.bankName}</td>
						<td valign="middle">
							<table>
								<tr>
									<form action="account" method="get">
										<input type="hidden" name="edit"> 
										<input type="hidden"
											name="id" value="${account.id}">
										<button type="submit" class="btn btn-outline-warning btn-sm">Редактировать</button>
									</form>
								</tr>
								<tr>
									<form action="account" method="post">
										<input type="hidden" name="delete"> <input
											type="hidden" name="id" value="${account.id}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Удалить</button>
									</form>
								</tr>
							</table>
						</td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
		<div class="tab-pane" id="payments" role="tabpanel">
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
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h5 class="modal-title" id="exampleModalLabel">Выплата | Добавление</h5>
						</div>
						<div class="modal-body">
						<form action="payment" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="spdId" value="${spd.id}">
							<table border="0" width="50%">
								<tr>
									<td valign="top">
										<div class="form-group">
											<label for="specNumber" class="col-sm-10 control-label">Номер спецификации (п/п)</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="specNumber" name="specNumber" value="${specNumber}">
											</div>
										</div>
										<div class="form-group">
											<label for="dateStart" class="col-sm-10 control-label">Дата спецификации</label>
											<div class="col-sm-10">
												<input type="date" class="form-control" id="dateStart" name="dateStart"
													placeholder="Введите дату спецификации" value="${dateStart}">
											</div>
										</div>
									</td>
								</tr>
							</table>
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
					<th>Дата действия</th>
					<th></th>
				</tr>
			</thead>
			<c:forEach items="${spd.payments}" var="payment">
			<c:set var="paymentType" value="${payment.paymentType}" />
				<tr>
					<td valign="middle">${paymentType.title}</td>
					<td valign="middle">${payment.value}</td>
					<td valign="middle">${payment.dateStart}</td>
					<td valign="middle">
						<table>
							<tr>
								<form action="account" method="get">
									<input type="hidden" name="edit"> <input type="hidden"
										name="id" value="${payment.id}">
									<button type="submit" class="btn btn-outline-warning btn-sm">Редактировать</button>
								</form>
							</tr>
							<tr>
								<form action="account" method="post">
									<input type="hidden" name="delete"> <input
										type="hidden" name="id" value="${payment.id}">
									<button type="submit" class="btn btn-outline-danger btn-sm">Удалить</button>
								</form>
							</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>

		</div>
		
	</div>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />