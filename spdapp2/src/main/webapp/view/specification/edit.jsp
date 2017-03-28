<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<title>Edit specification</title>

<div class="container-fluid">

	<c:set var="agreement" value="${specification.agreement}" />
	<c:set var="spd" value="${agreement.spd}" />
	
	<form class="form" role="form" action="specification" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${specification.id}">
		
		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Список СПД</a> 
					<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
					<a class="breadcrumb-item" href="${agreement.url}">Договор <c:out value="${agreement.number}" /></a> 
					<span class="breadcrumb-item active"><b>Спецификация № <c:out value="${specification.specificationNumber}" /> от 
														<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="${agreement.url}" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
	
		<p>
		
		<div class="row" >
			<div class="col-1">
				<label for="specificationNumber"><b>№ п/п</b></label>
				<input type="text" class="form-control" id="specificationNumber" name="specificationNumber"
					value="${specification.specificationNumber}" readonly>
			</div>
			<div class="col-2">
				<label for="specificationSum"><b>Общая сумма</b></label>
				<input type="text" class="form-control" id="specificationSum" name="specificationSum" 
					value="${specification.specificationSum}" readonly>
			</div>
			<div class="col"></div>
			<div class="col-2">
				<label for="dateStart"><b>Дата создания</b></label>
				<input type="date" class="form-control" id="dateStart" name="dateStart"
					value="${specification.dateStart}" >
			</div>
			<div class="col-2">
				<label for="dateFinish"><b>Дата закрытия</b></label>
				<input type="date" class="form-control" id="dateFinish" name="dateFinish"
					value=<c:out value="${specification.dateFinish}"/>>
			</div>
		</div>
		<p>
		<div class="row">
			<div class="col-3">
				<label for="configuringHours"><b>Конфигурирование</b></label>
				<label id="configuringHoursTarif"><b>${currentTarif.configuring}</b></label>
				
			</div>
			<div class="col-3">
				<label for="programmingHours"><b>Программирование</b></label>
				<label id="programmingHoursTarif"><b>${currentTarif.programming}</b></label>
				
			</div>
			<div class="col-3">
				<label for="architectingHours"><b>Архит. доработки</b></label>
				<input type="hidden" id="architectingHoursTarif" value="${currentTarif.architecting}"/>
			</div>
		</div>
		
		<div class="row">
			<div class="col-3">
				<div class="input-group">
					<input onchange="calcSpecificationSum()" type="number" class="form-control" id="configuringHours"
						name="configuringHours" value=<c:out value="${specification.configuringHours}"/>>
					<span class="input-group-addon"><b>* ${currentTarif.configuring} грн/ч</b></span>
				</div>
			</div>
			
			<div class="col-3">
				<div class="input-group">
					<input onchange="calcSpecificationSum()" type="number" class="form-control" id="programmingHours"
						name="programmingHours" value=<c:out value="${specification.programmingHours}"/>>
					<span class="input-group-addon"><b>* ${currentTarif.programming} грн/ч</b></span>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<input onchange="calcSpecificationSum()" type="number" class="form-control" id="architectingHours"
						name="architectingHours" value=<c:out value="${specification.architectingHours}"/>>
					<span class="input-group-addon"><b>* ${currentTarif.architecting} грн/ч</b></span>
				</div>
			</div>
		</div>
	</form>
	<p>
		<div class="row">
			<div class="col">
				<label id="sum" data-onload="calcSpecificationSum();" onclick="calcSpecificationSum()" ></label>
			</div>
		</div>
	<p>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#calculation" role="tab">Расчеты</a></li>
		<li class="nav-item"><a class="nav-link" data-toggle="tab" 
			href="#jobName" role="tab">Список работ</a></li>
	</ul>
	<p>
	
	<!-- Tab panes -->
	<div class="tab-content">
	
		<div class="tab-pane active" id="calculation" role="tabpanel">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCalculation">Добавить расчет</button>
			<!-- Modal -->
			<div class="modal fade" id="modalCalculation" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Новый расчет к спецификации № <c:out value="${specification.specificationNumber}"/></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="specification" method="post">
								<input type="hidden" name="add"> 
								<input type="hidden" name="specificationId"	value="${specification.id}">
								
								<table border="0" width="50%">
									<tr>
										<td valign="top">
											<div class="form-group">
												<label for=calculationNumber class="col-sm-10 control-label">Номер расчета (п/п)</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="calculationNumber" name="calculationNumber" value="${calculationNumber}">
												</div>
											</div>
											<div class="form-group">
												<label for="dateStart" class="col-sm-10 control-label">Дата расчета</label>
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
						<th>#</th>
						<th>Период</th>
						<th>Сальдо нач.</th>
						<th>Сальдо кон.</th>
						<th>Сумма</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach items="${specification.calculations}" var="calculation">
					<tr>
						<td onclick="goToAddress('${calculation.url}')">${calculation.partNumber}</td>
						<td onclick="goToAddress('${calculation.url}')"><fmt:formatDate pattern="MMMM yyyy" value="${calculation.dateStart}"/></td>
						<td onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" pattern="0.00" value="${calculation.openingBalance}"/></td>
						<td onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" pattern="0.00" value="${calculation.closingBalance}"/></td>
						<td onclick="goToAddress('${calculation.url}')"><fmt:formatNumber type="number" pattern="#,##0.00" value="${calculation.turnover}"/></td>
						<td>
							<div class="btn-group" role="group">
									<a class="btn btn-warning btn-sm" href="${calculation.url}" role="button">Подробнее</a>
									<form action="specification" method="post">
										<input type="hidden" name="delete">
										<input type="hidden" name="id" value="${calculation.id}">
										<button type="submit" class="btn btn-danger btn-sm">Удалить (осторожно!)</button>
									</form>
							</div>		
						</td>
					</tr>
				</c:forEach>
				<thead class="thead-default">
					<tr>
						<th></th>
						<th>Итого:</th>
						<th></th>
						<th></th>
						<th><fmt:formatNumber type="number" pattern="#,##0.00" value="${specification.calculationsTotalAmount}" /></th>
						<th></th>
					</tr>
				</thead>
			</table>
		</div>
	
		<div class="tab-pane" id="jobName" role="tabpanel">

			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalJobName">Добавить работу</button>

			<!-- Modal -->
			<div class="modal fade" id="modalJobName" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h5 class="modal-title" id="exampleModalLabel">Добавить работу</h5>
						</div>
						<div class="modal-body">
						<form action="specification" method="post">
							<input type="hidden" name="add"> 
							<input type="hidden" name="agreementId"	value="${agreement.id}">
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
		...
	</div>
	</div>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />