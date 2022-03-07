<%-- 
    Document   : register
    Created on : Mar 7, 2022, 1:23:28 PM
    Author     : dh687287
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <h1>Register Here!</h1>
    
    <div>
        <form action="public" method="post">
            <input type="hidden" name="action" value="register">

            <label for="email">Email: </label>
            <input type="text" name="email">
            <br>
            <label for="password">Password: </label>
            <input type="password" name="password"> 
            <br>
            <label for="passwordCheck">Retype Password: </label>
            <input type="password" name="passwordCheck"> 
            <br>
            <label for="username">Username: </label>
            <input type="text" name="username">
            <br>
            <input type="submit" value="Register">
        </form>
    </div>
    
    <div class="gridSubContent" id="errorDisplay">
        <ul>
            <c:forEach items="${errorList}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
</main>
<jsp:include page="/page/link/footer.jsp"/>