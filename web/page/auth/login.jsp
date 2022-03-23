<%-- 
    Document   : login
    Created on : Mar 7, 2022, 1:23:06 PM
    Author     : dh687287
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script type="text/javascript" src="page/auth/auth.js"></script>
    <div class="mainContent">
        <h1>Login Here</h1>
        <div class="subContent">
            <form action="Authorize" method="post">

                <label for="email">Email: </label>
                <br>
                <input type="text" name="email">
                <br>
                <br>

                <label for="password">Password: </label>
                <br>
                <input type="password" name="password">
                <br>
                <input type="submit" class="styledButton" value="Login">
            </form>

            <form action="navigation" method="post">
                <input type="hidden" name="url" value="/page/auth/register.jsp">
                <input type="submit" class="styledButton" value="Register Here">
            </form>
        </div>
        
        <div class="subContent hidden" id="errorDisplay">
            <ul>
                <c:forEach items="${errorList}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</main>
<jsp:include page="/page/link/footer.jsp"/>
