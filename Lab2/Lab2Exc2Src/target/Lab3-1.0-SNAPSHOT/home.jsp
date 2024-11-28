<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Our Store</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1, h2 {
            color: #343a40;
        }

        .container {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 300px;
        }

        .welcome-img,
        .footer-img {
            margin: 20px 0;
        }

        .footer-img img {
            max-width: 100%;
            height: auto;
        }

        .search-bar {
            margin-top: 20px;
        }

        input[type="search"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            margin-top: 20px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Our Store</h1>
        <div class="welcome-img">
            <img src="resources/welcome.jpeg" alt="Welcome">
        </div>
        <div class="search-bar">
            <h2>Search for an Item</h2>
            <form action="SearchItem" method="get">
                <input type="search" name="ItemName" placeholder="Enter item name">
                <input type="submit" value="Search">
            </form>
        </div>
    </div>
    <div class="footer-img">
        <img src="resources/Cstore.jpeg" alt="Store">
    </div>
</body>
</html>
