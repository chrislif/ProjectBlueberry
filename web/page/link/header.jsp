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
                            <form action="Navigation" method="POST">
                                <input type="hidden" name="url" value="/index.jsp">
                                <input type="submit" value="Home" class="navbutton primaryColor">
                            </form>
                        </li>
                        <c:if test="${currentUser != null}">
                            <li>
                                <form action="Navigation" method="POST">
                                    <input type="hidden" name="url" value="/page/project/overview.jsp">
                                    <input type="submit" value="Overview" class="navbutton primaryColor">
                                </form>
                            </li>

                            <li>
                                <form action="Navigation" method="POST">
                                    <input type="hidden" name="url" value="/page/admin/admin.jsp">
                                    <input type="submit" value="Admin Controls" class="navbutton primaryColor">
                                </form>
                            </li>
                        </c:if>
                    </div>
                    <div class="navSection">
                        <c:if test="${currentUser != null}">
                            <li>
                                <form action="Authorize" method="GET">
                                    <input type="submit" value="Logout" class="navbutton">
                                </form>
                            </li>
                        </c:if>
                        <c:if test="${currentUser == null}">
                            <li>
                                <form action="Navigation" method="POST">
                                    <input type="hidden" name="url" value="/page/auth/login.jsp">
                                    <input type="submit" value="Login" class="navbutton">
                                </form>
                            </li>
                        </c:if>
                    </div>
                </ul>
            </div>
        </nav>
