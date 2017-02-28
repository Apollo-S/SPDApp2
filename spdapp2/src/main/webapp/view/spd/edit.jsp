<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<jsp:include page="../header.jsp" />

<title>Edit | СПД <c:out value="${spd.alias}" /></title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<a class="breadcrumb-item" href="main">Главная</a>
		<a class="breadcrumb-item" href="getAllSPD">Список СПД</a> 
<%-- 		<a class="breadcrumb-item" href="${spd.url}">СПД <c:out	value="${spd.alias}" /></a>  --%>
		<span class="breadcrumb-item active"><b>СПД <c:out value="${spd.alias}" /> => Редактирование</b></span>
	</nav>
		
	<p>

	<form class="form" role="form" action="spd" method="post">
		<input type="hidden" name="edit"> 
		<input type="hidden" name="id" value="${spd.id}">
		
		<input type="submit" class="btn btn-success" id="button" value="Записать"> 	
		<a class="btn btn-danger" href="${spd.url}" role="button">Отмена</a>

		<p>
		<table border="0" width="50%">
			<tr align="left">
				<th>Основные данные</th>
				<th>Адрес деятельности</th>
				<th>Информация о гос. регистрации</th>
			</tr>
			<tr>
			<c:set var="address" value="${spd.address}" />
				<c:set var="registrationInfo" value="${spd.registrationInfo}" />
				<td valign="top">
					<div class="form-group">
						<label for="surname" class="col-sm-10 control-label"><b>Фамилия</b></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="surname" name="surname" placeholder="Введите фамилию"
								value="${spd.surname}">
						</div>
					</div>
					<div class="form-group">
						<label for="firstname" class="col-sm-6 control-label"><b>Имя</b></label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Введите имя"
								value="${spd.firstname}">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-10 control-label">Отчество</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="lastname" name="lastname" placeholder="Введите отчество"
								value="${spd.lastname}">
						</div>
					</div>
					<div class="form-group">
						<label for="alias" class="col-sm-10 control-label">Короткое ФИО</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="alias" name="alias" placeholder="Введите короткое ФИО"
								value="${spd.alias}">
						</div>
					</div>
					<div class="form-group">
						<label for="inn" class="col-sm-10 control-label">ИНН</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="inn" name="inn" placeholder="Введите идентификационный номер"
								value="${spd.inn}">
						</div>
					</div>
					<div class="form-group">
						<label for="passport" class="col-sm-10 control-label">Паспорт</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="passport" name="passport" placeholder="Введите паспортные данные"
								value="${spd.passport}">
						</div>
					</div>
				</td>
				<td valign="top">
					<div class="form-group">
						<label for="country" class="col-sm-10 control-label">Страна</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="country" name="country" placeholder="Страна"
								value="${address.country}">
						</div>
					</div>
					<div class="form-group">
						<label for="region" class="col-sm-10 control-label">Область</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="region" name="region" placeholder="Область" value="${address.region}">
						</div>
					</div>
					<div class="form-group">
						<label for="city" class="col-sm-10 control-label">Населенный пункт</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="city" name="city" placeholder="Населенный пункт"
								value="${address.city}">
						</div>
					</div>
					<div class="form-group">
						<label for="street" class="col-sm-10 control-label">Улица</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="street" name="street" placeholder="Улица" value="${address.street}">
						</div>
					</div>
					<div class="form-group">
						<label for="building" class="col-sm-10 control-label">Номер дома</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="building" name="building" placeholder="Номер дома"
								value="${address.building}">
						</div>
					</div>
					<div class="form-group">
						<label for="flat" class="col-sm-10 control-label">Квартира</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="flat" name="flat" placeholder="Квартира" value="${address.flat}">
						</div>
					</div>
					<div class="form-group">
						<label for="zip" class="col-sm-10 control-label">Индекс</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="zip" name="zip" placeholder="Индекс" value="${address.zip}">
						</div>
					</div>
				</td>
				<td valign="top">
					<div class="form-group">
						<label for="description" class="col-sm-10 control-label">Описание</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="description" name="description" placeholder="Описание"
								value="${registrationInfo.description}">
						</div>
					</div>
					<div class="form-group">
						<label for="dated" class="col-sm-10 control-label">Дата</label>
						<div class="col-sm-10">
							<input type="date" class="form-control" id="dated" name="dated" placeholder="Дата" value="${registrationInfo.dated}">
						</div>
					</div>
				</td>
			</tr>
		</table>
		<table>
			<tbody>
				<tr>

				</tr>
				<tr>
<!-- 					<div class="form-group"> -->
<!-- 						<label for="button" class="col-sm-10 control-label"></label> -->
<!-- 						<div class="col-sm-offset-10 col-sm-10"> -->
<!-- 							<input type="submit" class="btn btn-success" id="button" value="Сохранить изменения"> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</tr>
			</tbody>
		</table>
	</form>

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="../footer.jsp" />