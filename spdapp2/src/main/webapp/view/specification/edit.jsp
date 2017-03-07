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
		<input type="hidden" name="agreementId" value="${agreement.id}">
		<input type="hidden" name="spdId" value="${spd.id}">
	
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
					<a class="btn btn-danger" href="companies" role="button">Отмена</a>
				</div>
			</div>
		</nav>
	
	
		<p>
		
		<div class="row">
			<div class="col-1">
				<label for="specificationNumber"><b>№ п/п</b></label>
					<input type="text" class="form-control" id="specificationNumber"
						name="specificationNumber" placeholder="specificationNumber" value=<c:out value="${specification.specificationNumber}"/> readonly>
			</div>
			<div class="col-2">
				<label for="specificationSum"><b>Общая сумма</b></label>
					<input type="text" class="form-control" id="specificationSum"
						name="specificationSum" placeholder="" value=<c:out value="${specification.specificationSum}"/>>
			</div>
			<div class="col"></div>
			<div class="col-2">
				<label for="dateStart"><b>Дата создания</b></label>
					<input type="date" class="form-control" id="dateStart"
						name="dateStart" placeholder="Введите дату" value=<c:out value="${specification.dateStart}"/>>
			</div>
			<div class="col-2">
				<label for="dateFinish"><b>Дата закрытия</b></label>
					<input type="date" class="form-control" id="dateFinish"
						name="dateFinish" placeholder="" value=<c:out value="${specification.dateFinish}"/>>
			</div>
		</div>
		<p>
		<div class="row">
			<div class="col-2">
				<label for="configuringHours"><b>Конфигурирование (часы)</b></label>
				<input type="number" class="form-control" id="configuringHours"
					name="configuringHours" placeholder="" value=<c:out value="${specification.configuringHours}"/>>
			</div>

			<div class="col-2">
				<label for="programmingHours"><b>Программирование</b></label>
				<input type="number" class="form-control" id="programmingHours"
					name="programmingHours" placeholder="" value=<c:out value="${specification.programmingHours}"/>>
			</div>	
			<div class="col-2">
				<label for="architectingHours"><b>Архит. доработки [часы]</b></label>
				<input type="text" class="form-control" id="architectingHours"
					name="architectingHours" placeholder="" value=<c:out value="${specification.architectingHours}"/>>
			
			</div>	
		</div>	
	</form>
	
	<p>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
	<li class="nav-item"><a class="nav-link active" data-toggle="tab"
			href="#calculation" role="tab">Расчеты</a></li>
		<li class="nav-item"><a class="nav-link"
			data-toggle="tab" href="#jobName" role="tab">Список работ</a></li>
		
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
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h5 class="modal-title" id="exampleModalLabel">Новый расчет к спецификации № <c:out value="${specification.specificationNumber}"/></h5>
					</div>
					<div class="modal-body">
					<form action="specification" method="post">
						<input type="hidden" name="add"> 
						<input type="hidden" name="specificationId"	value="${specification.id}">
						<input type="hidden" name="spdId" value="${spd.id}">
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
	...
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