<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<html>
<head>
    <title>${contact.name}</title>
</head>
<body>
    <h1>${contact.name}</h1>
    <ul>
        <li>${contact.address.street}</li>
        <li>${address.city}, ${contact.address.state} ${contact.address.zip}</li>
    </ul>
    <a href="contact?edit&id=${contact.id}"> edit contact</a> | <a href="contacts">back to contact list</a>
</body>
</html>
