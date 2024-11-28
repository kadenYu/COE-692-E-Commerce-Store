<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="com.mycompany.lab2.ItemsBought"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Your Past Orders</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2, h3 {
            color: #333;
        }

        .container {
            margin: 20px;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .extend-button, .home-button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }

        .extend-button:hover, .home-button:hover {
            background-color: #0056b3;
        }

        .sold-out {
            color: red;
            font-weight: bold;
        }

        .message {
            margin-top: 20px;
            font-size: 18px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Welcome back, <%=session.getAttribute("uname")%>!</h2>
        <h3>Your Past Orders</h3>
        <% 
            ArrayList<ItemsBought> books = (ArrayList<ItemsBought>) request.getAttribute("ItemsBoughtInfo");
            if (books != null && !books.isEmpty()) { 
        %>
        <form action="Extend" method="get">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Due Date</th>
                    <th>Extend</th>
                </tr>
                <% for (ItemsBought book : books) { %>
                <tr>
                    <td><%= book.getBookName() %></td>
                    <td><%= book.getBookAuthor() %></td>
                    <td><%= new SimpleDateFormat("yyyy-MM-dd").format(book.getDueDate()) %></td>
                    <td>
                        <% if (book.isAvailable()) { %>
                            <input type="hidden" name="bookName" value="<%= book.getBookName() %>">
                            <button type="submit" class="extend-button">Extend</button>
                        <% } else { %>
                            <span class="sold-out">The item is sold out</span>
                        <% } %>
                    </td>
                </tr>
                <% } %>
            </table>
        </form>
        <% } else { %>
            <p class="message">You haven't ordered from us yet :(</p>
        <% } %>
        <button class="home-button" onclick="location.href='home.jsp'">Home</button>
        <button class="home-button" onclick="location.href='Logout'">Logout</button> <!-- Logout button -->
    </div>
</body>
</html>
