<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<jsp:include page="header.jsp" />

<title>Error</title>

<div class="container-fluid">
	
	<div class="jumbotron">
		<h1 class="display-3 text-center">Oops!!! Error...</h1>
		<hr class="my-4">
		
		<div class="row">
			<div class="col text-center">
				<button class="btn btn-danger" onclick="goBack()">Налево</button>
				<strong> = Сюда пойдешь - назад прийдешь, направо пойдешь - к СПД в лапы попадешь</strong>
				<button class="btn btn-danger" href="spds" role="button">Направо</button>
			</div>
		</div>
		
		<p class="lead text-center">
			<button class="btn btn-danger" onclick="goBack()">Налево</button>
			<strong>Налево пойдешь - назад попадешь, </strong>
		</p>
		<p class="lead text-center">
			<strong>Направо пойдешь - к СПД в лапы попадешь, </strong>
			<button class="btn btn-danger" onclick="goBack()">Направо</button>
		</p>
		<p class="lead">
			<a class="btn btn-primary btn-lg" onclick="goBack()" href="#" role="button">Просмотреть всех СПД</a>
		</p>
	</div>
	


	<div class="row">
		<div class="col text-center">
		Oops!!! Error...
	
	Return back => <button class="btn btn-danger" onclick="goBack()">Go Back</button>
		</div>
		
	
	</div>
	

</div> <!-- .container-fluid -->
	
<!-- footer -->
<jsp:include page="footer.jsp" />