<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<jsp:include page="../header.jsp" />

<title>Edit bank props</title>

<div class="container-fluid">

	<c:set var="spd" value="${account.spd}"/>

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="main">Главная</a>
		<a class="breadcrumb-item" href="getAllSPD">Список СПД</a> 
		<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a> 
		<span class="breadcrumb-item active"><b>Расчетный счет № <c:out value="${account.accountNumber}" /></b></span>
	</nav>

	<p>

	<form class="form" role="form" action="account" method="post">
		<input type="hidden" name="edit">
		<input type="hidden" name="id" value="${account.id}">
		
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
						<input type="hidden" class="form-control" id="spdId"
							name="spdId" value="${spd.id}">
					</div>
					<div class="form-group">
						<label for="accountNumber" class="col-sm-10 control-label">Номер счета</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="accountNumber"
								name="accountNumber" placeholder="Введите номер счета" value="${account.accountNumber}">
						</div>
					</div>
					<div class="form-group">
						<label for="bankName" class="col-sm-6 control-label">Наименование банка</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="bankName"
								name="bankName" placeholder="Введите наименование банка" value=<c:out value="${account.bankName}"/> >   <!-- // TODO Пропадают символы в кавычках -->
						</div>
					</div>
					<div class="form-group">
						<label for="mfo" class="col-sm-10 control-label">МФО</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="mfo"
								name="mfo" placeholder="Введите МФО" value="${account.mfo}">
						</div>
					</div>
			</tr>
		</table>
		<!-- ---------------------------------------------------------------------------------------- -->
		
	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />