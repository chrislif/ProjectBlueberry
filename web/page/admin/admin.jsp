
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script type="text/javascript" src="page/admin/admin.js"></script>
    <div class="mainContent">
        <h2>Accounts</h2>
        <div class="gridWrapper" id="accountTable">

        </div>
        <div id="mainModal" class="modalBackground">
        </div>
    </div>

</main>
<jsp:include page="/page/link/footer.jsp"/>
