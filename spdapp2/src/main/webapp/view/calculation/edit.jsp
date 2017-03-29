<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../header.jsp" />

<title>Edit calculation</title>

<div class="container-fluid">
	
	<c:set var="specification" value="${calculation.specification}" />
	<c:set var="agreement" value="${specification.agreement}" />
	<c:set var="spd" value="${agreement.spd}" />
	
	<form class="form" role="form" action="calculation" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${calculation.id}">
	
		<nav class="breadcrumb">
			<div class="row">
				<div class="col">
					<a class="breadcrumb-item" href="spds">Список СПД</a> 
					<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
					<a class="breadcrumb-item" href="${agreement.url}">Договор <c:out value="${agreement.number}" /></a>
					<a class="breadcrumb-item" href="${specification.url}">Спецификация № <c:out value="${specification.specificationNumber}" /> от 
														<fmt:formatDate	value="${specification.dateStart}" pattern="dd.MM.yyyy" />г.</a>
					<span class="breadcrumb-item active"><b>Расчет № <c:out value="${calculation.partNumber}" /> за 
														<fmt:formatDate	value="${calculation.dateStart}" pattern="MMMM yyyy" />г.</b></span>
				</div>
			</div>
			<p>
			<div class="row">
				<div class="col">
					<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
					<a class="btn btn-danger" href="${specification.url}" role="button">Отмена</a>
				</div>
			</div>
		</nav>
		
		<p>
		
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>№ расчета</b></span>
					<input type="text" class="form-control" id="partNumber" name="partNumber"
						value="${calculation.partNumber}" readonly>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Сальдо начальное</b></span>
					<input type="text" class="form-control" id="openingBalance" name="openingBalance" 
						value="${calculation.openingBalance}" readonly>
				</div>
			</div>
			
			<div class="col"></div>
			<div class="col-4">
				<div class="form-group row">
					<label for="dateStart" class="col-4 col-form-label"><b>Период расчета</b></label>
					<div class="col-6">
						<input type="date" class="form-control" id="dateStart" name="dateStart"
							value="${calculation.dateStart}" >
					</div>
				</div>
			</div>
		</div>
		<p>
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Оклад</b></span>
	<!-- 				<label for="salaryRate"><b>Оклад</b></label> -->
					<input type="text" class="form-control" id="salaryRate" name="salaryRate"
						value=<c:out value="${calculation.salaryRate}"/>>			
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Комиссия банка</b></span>
	<!-- 				<label for="withdrawСashСomission"><b>Комиссия банка</b></label> -->
					<input type="text" class="form-control" id="withdrawCashComission" name="withdrawCashComission"
						value=<c:out value="${calculation.withdrawCashComission}"/>>			
				</div>
			</div>
		</div>
		<p>
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Премия</b></span>
<!-- 					<label for="premium"><b>Премия</b></label> -->
					<input type="text" class="form-control" id="premium" name="premium"
						value=<c:out value="${calculation.premium}"/>>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Ведение карты</b></span>
<!-- 					<label for="cardServiceFee"><b>Ведение карты</b></label> -->
					<input type="text" class="form-control" id="cardServiceFee" name="cardServiceFee"
						value=<c:out value="${calculation.cardServiceFee}"/>>
				</div>
			</div>
		
		</div>
		<p>
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
	<!-- 				<label for="esv"><b>ЕСВ</b></label> -->
					<span class="input-group-addon" id="basic-addon1"><b>ЕСВ</b></span>
					<input type="text" class="form-control" id="esv" name="esv"
						value=<c:out value="${calculation.esv}"/>>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
	<!-- 				<label for="accountServiceFee"><b>Ведение счета</b></label> -->
					<span class="input-group-addon" id="basic-addon1"><b>Ведение счета</b></span>
					<input type="text" class="form-control" id="accountServiceFee" name="accountServiceFee"
						value=<c:out value="${calculation.accountServiceFee}"/>>
				</div>
			</div>
		</div>
		<p>
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Аренда</b></span>
					<input type="text" class="form-control" id="rent" name="rent"
						value=<c:out value="${calculation.rent}"/>>
				</div>
			</div>
		</div>
		<p>
		<div class="row" >
			<div class="col-2">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Доплата к обороту</b></span>
					<input type="text" class="form-control" id="surcharge" name="surcharge"
						value=<c:out value="${calculation.surcharge}"/>>
				</div>
			</div>
		</div>
		
		
		
	</form>
	

	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />