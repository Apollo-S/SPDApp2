<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../header.jsp" />

<c:set var="specification" value="${calculation.specification}" />
<c:set var="agreement" value="${specification.agreement}" />
<c:set var="spd" value="${agreement.spd}" />

<title>Расчет № <c:out value="${calculation.partNumber}" /> за <fmt:formatDate	value="${calculation.dateStart}" pattern="MMMM yyyy" />г. | <c:out value="${spd.alias}" /> | </title>

<div class="container-fluid">
	
	<input type="hidden" id="esvRate" value="${esvRate}">
	<input type="hidden" id="simpleTaxRate" value="${simpleTaxRate}">
	<input type="hidden" id="bankComissionRate" value="${bankComissionRate}">
	
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
			<div class="col-4">
				<div class="form-group row">
					<label for="partNumber" class="col-3 col-form-label"><b>№ расчета</b></label>
					<div class="col-4">
						<input type="text" style="font-weight: bold;" class="form-control text-center" id="partNumber" name="partNumber"
							value=<c:out value="${calculation.partNumber}"/> readonly>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="form-group row">
					<label for="dateStart" class="col-5 col-form-label"><b>Период расчета</b></label>
					<div class="col-5">
						<input type="date" class="form-control" id="dateStart" name="dateStart"
							value=<c:out value="${calculation.dateStart}"/>>
					</div>
				</div>
			</div>
			<div class="col"></div>
			<div class="col-3">
				<div class="form-group row">
					<label for="openingBalance" class="col-6 col-form-label"><b>Сальдо на начало</b></label>
					<div class="col-6">
						<input type="text" style="font-weight: bold;" class="form-control text-right" id="openingBalance" name="openingBalance" 
							value=<fmt:formatNumber value="${calculation.openingBalance}" minFractionDigits="2" maxFractionDigits="2"/> readonly>
					</div>
				</div>
			</div>
		</div>
		
		<p>
		
		<div class="row" >
			<div class="col-4">
				<div class="form-group row">
					<label for="salaryRate" class="col-3 col-form-label"><b>Оклад</b></label>
					<div class="col-7">
						<div class="input-group">
							<input type="text" class="form-control text-right" id="salaryRate" name="salaryRate"
								value=<fmt:formatNumber value="${calculation.salaryRate}" minFractionDigits="2" maxFractionDigits="2"/> >	
							<span class="input-group-btn">
								<button onclick="clearValue('salaryRate')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('salaryRate', ${calculation.salaryRate})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="form-group row">
					<label for="withdrawСashСomission" class="col-5 col-form-label"><b>Комиссия (<fmt:formatNumber type="percent" minFractionDigits="2" maxFractionDigits="2" value="${bankComissionRate}"/>)</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.withdrawCashComission}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="withdrawCashComission" />
							<c:set var="withdrawCashComission" value="${fn:replace(withdrawCashComission, ',', '.')}" />
							<input type="text" class="form-control text-right" id="withdrawCashComission" name="withdrawCashComission" onchange="calcBankCostSum()"
								value=<c:out value="${withdrawCashComission}"/>>	
							<span class="input-group-btn">
								<button onclick="clearValue('withdrawCashComission')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('withdrawCashComission', ${calculation.withdrawCashComission})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col"></div>
			<div class="col-3">
				<div class="form-group row">
					<label for="simpleTax" class="col-6 col-form-label text-left"><b>Единый налог <fmt:formatNumber type="percent" value="${simpleTaxRate}"/></b></label>
					<div class="col-6">
						<fmt:formatNumber value="${calculation.simpleTax}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="simpleTax" />
						<c:set var="simpleTax" value="${fn:replace(simpleTax, ',', '.')}" />
						<input type="text" style="font-weight: bold;" class="form-control text-right" id="simpleTax" name="simpleTax"
							value=<c:out value="${simpleTax}"/> readonly>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="row" >
			<div class="col-4">
				<div class="form-group row">
					<label for="premium" class="col-3 col-form-label"><b>Премия</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.premium}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="premium" />
							<c:set var="premium" value="${fn:replace(premium, ',', '.')}" />
							<input type="text" class="form-control text-right" id="premium" name="premium"
								value=<c:out value="${premium}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('premium')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('premium', ${calculation.premium})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="form-group row">
					<label for="cardServiceFee" class="col-5 col-form-label"><b>Ведение карты</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.cardServiceFee}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="cardServiceFee" />
							<c:set var="cardServiceFee" value="${fn:replace(cardServiceFee, ',', '.')}" />
							<input type="text" class="form-control text-right" id="cardServiceFee" name="cardServiceFee"
								value=<c:out value="${cardServiceFee}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('cardServiceFee')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('cardServiceFee', ${calculation.cardServiceFee})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row" >
			<div class="col-4">
				<div class="form-group row">
					<label for="esv" class="col-3 col-form-label"><b>ЕСВ</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.esv}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="esv" />
							<c:set var="esv" value="${fn:replace(esv, ',', '.')}" />
							<input type="text" class="form-control text-right" id="esv" name="esv"
								value=<c:out value="${esv}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('esv')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('esv', ${calculation.esv})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="form-group row">
					<label for="accountServiceFee" class="col-5 col-form-label"><b>Ведение счета</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.accountServiceFee}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="accountServiceFee" />
							<c:set var="accountServiceFee" value="${fn:replace(accountServiceFee, ',', '.')}" />
							<input type="text" class="form-control text-right" id="accountServiceFee" name="accountServiceFee"
								value=<c:out value="${accountServiceFee}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('accountServiceFee')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('accountServiceFee', ${calculation.accountServiceFee})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row" >
			<div class="col-4">
				<div class="form-group row">
					<label for="rent" class="col-3 col-form-label"><b>Аренда</b></label>
					<div class="col-7">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.rent}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="rent" />
							<c:set var="rent" value="${fn:replace(rent, ',', '.')}" />
							<input type="text" class="form-control text-right" id="rent" name="rent"
								value=<c:out value="${rent}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('rent')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('rent', ${calculation.rent})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-4">
				<div class="form-group row">
					<label for="bankCost" class="col-5 col-form-label"><b>Всего банк. затрат:</b></label>
					<div class="col-7">
						<div class="input-group">
							<input type="text" style="font-weight: bold;" class="form-control text-center" id="bankCost" name="bankCost" data-onload="calcBankCostSum()"
								value="" readonly>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<div class="row" >
			<div class="col-4">
				<div class="form-group row">
					<label for="surcharge" class="col-3 col-form-label"><b>Доплата </b></label>
					<div class="col-8">
						<div class="input-group">
							<fmt:formatNumber value="${calculation.surcharge}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="surcharge" />
							<c:set var="surcharge" value="${fn:replace(surcharge, ',', '.')}" />
							<input type="text" class="form-control text-right" id="surcharge" name="surcharge"
								value=<c:out value="${surcharge}"/>>
							<span class="input-group-btn">
								<button onclick="clearValue('surcharge')" 
									class="btn btn-secondary" type="button"><i class="fa fa-remove"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="getInitialValueBack('surcharge', ${calculation.surcharge})" 
									class="btn btn-secondary" type="button"><i class="fa fa-undo"></i></button>
							</span>
							<span class="input-group-btn">
								<button onclick="surchargeUpdate()" 
									class="btn btn-outline-success" type="button"><i class="fa fa-calculator"></i></button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="col-3">
				<button onclick="calculationUpdate()" class="btn btn-info btn-block" type="button""><= Расчитать =></button>
			</div>
			<div class="col"></div>
			<div class="col-3">
				<div class="form-group row">
					<label for="closingBalance" class="col-6 col-form-label"><b>Сальдо на конец</b></label>
					<div class="col-6">
						<fmt:formatNumber value="${calculation.closingBalance}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="closingBalance" />
						<c:set var="closingBalance" value="${fn:replace(closingBalance, ',', '.')}" />
						<input type="text" style="font-weight: bold;" class="form-control text-right" id="closingBalance" name="closingBalance" 
							value=<c:out value="${closingBalance}"/> readonly>
					</div>
				</div>
			</div>
		</div>
		<p>
		<div class="row" >
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>&sum; к перечислению</b></span>
					<fmt:formatNumber value="${calculation.turnover}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="turnover" />
					<c:set var="turnover" value="${fn:replace(turnover, ',', '.')}" />
					<input type="text" style="font-weight: bold;" class="form-control text-right" id="turnover" name="turnover"
						value=<c:out value="${turnover}"/> readonly>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>На руки</b></span>
					<fmt:formatNumber value="${calculation.moneyOnHand}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="moneyOnHand" />
					<c:set var="moneyOnHand" value="${fn:replace(moneyOnHand, ',', '.')}" />
					<input type="text" style="font-weight: bold;" class="form-control text-right" id="moneyOnHand" name="moneyOnHand"
						value=<c:out value="${moneyOnHand}"/> readonly>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>На карту СПД</b></span>
					<fmt:formatNumber value="${calculation.moneyTransfer}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="moneyTransfer" />
					<c:set var="moneyTransfer" value="${fn:replace(moneyTransfer, ',', '.')}" />
					<input type="text" style="font-weight: bold;" class="form-control text-right" id="moneyTransfer" name="moneyTransfer"
						value=<c:out value="${moneyTransfer}"/> readonly>
				</div>
			</div>
			<div class="col-3">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><b>Сумма снятия</b></span>
					<fmt:formatNumber value="${calculation.withdrawCash}" groupingUsed="false" minFractionDigits="2" maxFractionDigits="2" var="withdrawCash" />
					<c:set var="withdrawCash" value="${fn:replace(withdrawCash, ',', '.')}" />
					<input type="text" style="font-weight: bold;" class="form-control text-right" id="withdrawCash" name="withdrawCash"
						value=<c:out value="${withdrawCash}"/> readonly>
				</div>
			</div>
		</div>
		<p>
		
	</form>
	
	

	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />