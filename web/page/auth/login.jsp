<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <div class="mainContent">
        <h1>Login Here</h1>
        <div class="subContent">
            <form action="Authorize" method="POST">
                <label for="email">Email: </label>
                <br>
                <input type="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="email">
                <br>
                <br>

                <label for="password">Password: </label>
                <br>
                <input type="password" name="password">
                <br>
                <input type="submit" class="styledButton" value="Login">
            </form>

            <form action="Navigation" method="POST">
                <input type="hidden" name="url" value="/page/auth/register.jsp">
                <input type="submit" class="styledButton" value="Register Here">
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
