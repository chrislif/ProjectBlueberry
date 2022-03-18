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
    <script type="text/javascript" src="page/project/projectHome.js"></script>
    <script type="text/javascript" src="page/project/sprint.js"></script>
    <script type="text/javascript" src="page/project/story.js"></script>
    <script type="text/javascript">
        var project = JSON.parse('${project}');
    </script>
    <div class="mainContent">
        
        <div id="projectTitle">
        </div>

        <div class="subContent" id="projectOverview">
        </div>

        <div class="subContent" id="sprintOverview">
        </div>

        <div class="subContent hidden" id="sprintForm">
            <h2>Add A Sprint</h2><br>

            <label for="sprintNumber">Sprint #: </label>
            <select name="sprintNumber" id="sprintNumber">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select><br><br>

            <label for="sprintName">Sprint Name: </label>
            <input type="text" name="sprintName" id="sprintName"><br><br>

            <label for="sprintStartDate">Sprint Start Date: </label>
            <input type="date" id="sprintStartDate" name="sprintStartDate"><br><br>

            <label for="sprintEndDate">Sprint End Date: </label>
            <input type="date" id="sprintEndDate" name="sprintEndDate"><br><br>

            <button class="styledButton" id="sprintCreateButton">Create Sprint</button>
            <button class="styledButton" id="sprintCancelButton">Cancel</button>
        </div>

        <div class="subContent hidden" id="userStoryForm">
            <h2>Add A Story</h2><br>

            <label for="storyName">User Story Name: </label>
            <input type="text" name="storyName" id="newStoryName"> <br> <br>

            <label for="storySprintNum">Which Sprint: </label>
            <select name="storySprintNum" id="storySprintNum">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select> <br> <br>

            <label for="priorityLevel">Priority: </label>
            <select name="priorityLevel" id="storyPriorityLevel">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select> <br> <br>

            <button class="styledButton" id="storyCreateButton">Create Sprint</button>
            <button class="styledButton" id="storyCancelButton">Cancel</button>
        </div>

        <button class="styledButton" id="newSprintButton">New Sprint</button>
        <button class="styledButton" id="newStoryButton">New User Story</button>
        
        <div id="mainModal" class="modalBackground">
        </div>
    </div>

</main>
<jsp:include page="/page/link/footer.jsp"/>
