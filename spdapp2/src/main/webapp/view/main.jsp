<%@ page session="false" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<title>Main Page</title>

<div class="container-fluid">

	<nav class="breadcrumb">
		<span class="breadcrumb-item active"><b>Главная</b></span>
	</nav>
	
	<a class="btn btn-primary" href="getAllSPD" role="button">Список СПД</a>

</div>

<!-- footer -->
<jsp:include page="footer.jsp" />