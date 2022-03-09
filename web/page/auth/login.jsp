<%-- 
    Document   : login
    Created on : Mar 7, 2022, 1:23:06 PM
    Author     : dh687287
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <h1>Login Here</h1>
    <h1>${errorList}</h1>
    <form action="public" method="post">
        <input type="hidden" name="action" value="authorize">
        
        <label for="email">Email: </label>
        <input type="text" name="email">
        <br>
        <label for="password">Password: </label>
        <input type="password" name="password">
        <br>
        <input type="submit" value="Login">
    </form>
    
    <form action="public" method="post">
        <input type="hidden" name="action" value="toRegister">
        <input type="submit" value="Register Here">
    </form>
</main>
<jsp:include page="/page/link/footer.jsp"/>
