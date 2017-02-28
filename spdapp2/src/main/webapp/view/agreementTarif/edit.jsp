<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../header.jsp" />

<title>Edit agreement tarif</title>

<div class="container-fluid">

	<c:set var="agreement" value="${agreementTarif.agreement}"/>
	<c:set var="spd" value="${agreement.spd}"/>

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="main">Главная</a>
		<a class="breadcrumb-item" href="getAllSPD">Список СПД</a> 
		<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
		<a class="breadcrumb-item" href="${agreement.url}">Договор <c:out value="${agreement.number}" /></a> 
		<span class="breadcrumb-item active"><b>Тарифы от <c:out value="${agreementTarif.dateStart}" /></b></span>
	</nav>

	<p>

	<form class="form" role="form" action=agreementTarif method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${agreementTarif.id}">
		<input type="hidden" name="agreementId" value="${agreement.id}">
		<input type="hidden" name="spdId" value="${spd.id}">
		
		<div class="form-group">
			<label for="button" class="col-sm-10 control-label"></label>
			<div class="col-sm-offset-10 col-sm-10">
			<input type="submit" class="btn btn-success" id="button"
					value="Сохранить"> <br> 
			</div>
		</div>

		<table border="0" width="50%">			
			<tr>
				<td valign="top">
					<div class="form-group">
						
					</div>
					<div class="form-group">
						<label for="configuring" class="col-sm-10 control-label">Конфигурирование</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="configuring"
								name="configuring" placeholder="Введите сумму (конфигурирование)" value=<c:out value="${agreementTarif.configuring}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="programming" class="col-sm-10 control-label">Программирование</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="programming"
								name="programming" placeholder="Введите сумму (программирование)" value=<c:out value="${agreementTarif.programming}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="architecting" class="col-sm-10 control-label">Архит. доработки</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="architecting"
								name="architecting" placeholder="Введите сумму (арх. доработки)" value=<c:out value="${agreementTarif.architecting}"/>>
						</div>
					</div>
					<div class="form-group">
						<label for="dateStart" class="col-sm-6 control-label">Дата</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dateStart"
								name="dateStart" placeholder="Введите дату" value=<c:out value="${agreementTarif.dateStart}"/>>
						</div>
					</div>
			</tr>
		</table>
		<!-- ---------------------------------------------------------------------------------------- -->
		
	</form>
	
</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />