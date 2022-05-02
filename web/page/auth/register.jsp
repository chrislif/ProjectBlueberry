<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <div class="mainContent">

    <h1>Register Here!</h1>
    
    <div class="subContent">
        <form action="Register" method="POST">
            <label for="email">Email: </label>
            <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="Please enter an email" name="email">
            <br>
            <label for="password">Password: </label>
            <input type="password" name="password"> 
            <br>
            <label for="passwordCheck">Retype Password: </label>
            <input type="password" name="passwordCheck"> 
            <br>
            <label for="username">Account Name: </label>
            <input type="text" name="accountName">
            <br>
            <input type="submit" class="styledButton" value="Register">
        </form>
    </div>
    
    <div class="subContent" id="errorDisplay">
        <ul>
            <c:forEach items="${errorList}" var="error">
                <li><span>${error}</span></li>
            </c:forEach>
        </ul>
    </div>
            
    </div>
</main>
<jsp:include page="/page/link/footer.jsp"/>