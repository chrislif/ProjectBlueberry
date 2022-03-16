<%-- 
    Document   : overview
    Created on : Mar 9, 2022, 1:53:15 PM
    Author     : dh687287
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/page/link/header.jsp"/>
<main>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
    <script type="text/javascript" src="page/project/overview.js"></script>
    <div class="mainContent">
        <h1>Projects Overview</h1>
        
        <div class="gridWrapper" id="projectTable">

        </div>
        
        <div class="subContent hidden" id="projectForm">
            <label for="projectName">Project Name: </label>
            <input type="text" name="projectName" id="projectNameText">
            <br>

            <button class="styledButton" id="projectCreateButton">Create Project</button>
            <button class="styledButton" id="projectCancelButton">Cancel</button>
        </div>
        
        <button class="styledButton" id="projectNewButton">New Project</button>
    </div>
</main>
<jsp:include page="/page/link/footer.jsp"/>
