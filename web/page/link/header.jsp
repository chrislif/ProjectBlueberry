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
        
    </head>
    <body>
        <header>
            
        </header>
        <nav>
            <div class="navbar">
                <ul class="navlist">
                    <li>
                        <form action="public" method="post">
                            <input type="hidden" name="action" value="toHome">
                            <input type="submit" value="Test" class="navbutton">
                        </form>
                    </li>
                </ul>
            </div>
        </nav>