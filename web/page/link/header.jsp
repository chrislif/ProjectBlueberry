<%-- 
    Document   : header
    Created on : Mar 3, 2022, 4:21:28 PM
    Author     : chris
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blueberry Developer Management System</title>
        <link rel="stylesheet" href="page/link/style.css">
    </head>
    <body>
        <header>
            <h1>Blueberry Dev Manager</h1>
        </header>
        <nav>
            <div class="navbar">
                <ul class="navlist">
                    <div class="navSection">
                        <li>
                            <form action="navigation" method="post">
                                    <input type="hidden" name="url" value="/index.jsp">
                                <input type="submit" value="Home" class="navbutton primaryColor">
                            </form>
                        </li>
                        <c:if test="${currentUser != null}">
                            <li>
                                <form action="navigation" method="post">
                                    <input type="hidden" name="url" value="/page/project/overview.jsp">
                                    <input type="submit" value="Overview" class="navbutton primaryColor">
                                </form>
                            </li>
                        </c:if>
                    </div>
                    <div class="navSection">
                        <c:if test="${currentUser != null}">
                            <li>
                                <form action="Private" method="post">
                                    <input type="hidden" name="action" value="logout">
                                    <input type="submit" value="Logout" class="navbutton">
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${currentUser == null}">
                            <li>
                                <form action="navigation" method="post">
                                    <input type="hidden" name="url" value="/page/auth/login.jsp">
                                    <input type="submit" value="Login" class="navbutton">
                                </form>
                            </li>
                        </c:if>
                    </div>
                </ul>
            </div>
        </nav>
