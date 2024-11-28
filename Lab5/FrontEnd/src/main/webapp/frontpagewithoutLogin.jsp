<%-- 
    Document   : frontpagewithoutLogin
    Created on : Jun 10, 2024, 9:16:50 PM
    Author     : student
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Clothing Store. </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h2>Welcome to our Clothing Store. You are not logged in</h2>

    <h3>Search Clothing</h3>
    <form action="FrontEnd" method="post">
        <input type="hidden" name="pageName" value="search">
        <label for="query">Search:</label>
        <input type="text" id="query" name="query" required>
        <input type="submit" value="Search">
    </form>
</body>
</html>

