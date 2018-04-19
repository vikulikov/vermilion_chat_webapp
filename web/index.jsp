<%--
  Created by IntelliJ IDEA.
  User: vikulikov
  Date: 27.03.18
  Time: 0:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="css/login_style.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" />

    <title>Vermilion - Login</title>
</head>
<body>

    <header>
        <form action="" method="post" id="my_form">

            <label for="email">E-mail:</label>
            <input class="input_field" type="email" id="email" name="email" placeholder="email@example.com" />

            <label for="password">Password:</label>
            <input class="input_field" type="password" id="password" name="password"/>

            <button type="submit">Log In</button>
        </form>

        <nav>
            <div class="pseudo_nav_bar">
                <a href="registration">Registration</a>
                <a href="restoring">Restore password</a>
            </div>
        </nav>

        <p class="error_message" id="invalid-user"></p>

    </header>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="javascript/login_script.js"></script>

    <a href="client/WebSocketClient.html">Тест вебсокета</a>

</body>
</html>
