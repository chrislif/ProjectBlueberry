<%-- 
    Document   : projectHome
    Created on : Mar 7, 2022, 1:33:00 PM
    Author     : dh687287
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script type="text/javascript" src="page/project/overview.js"></script>
    <div class="mainContent">
        <h1>Project Home</h1>
        
        <div class="subContent">
            <p>
                <c:out value="${projectID}"/>
            </p>
        </div>
    </div>
</main>
<jsp:include page="/page/link/footer.jsp"/>
