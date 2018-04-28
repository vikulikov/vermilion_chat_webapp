<%--
  Created by IntelliJ IDEA.
  User: vikulikov
  Date: 02.04.18
  Time: 22:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" language="java" import="java.sql.*"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/registration_style.css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" />

    <title>Vermilion - Registration</title>
</head>

<body>

    <form action="registration" method="post" class="forms">

        <h1>Sign Up</h1>
        <p>Fill up the form to create an account.</p>

        <hr>

        <label for="login">Login</label>
        <input type="text" name="login" id="login" required autofocus />
        <p class="error_message" id="login_error"></p>

        <label for="email">E-mail</label>
        <input type="email" name="email" id="email" placeholder="example@kulikov.com" required />
        <p class="error_message" id="email_error"></p>

        <label for="pass">Password</label>
        <input type="password" name="password" id="pass" required />
        <p class="error_message" id="password_error"></p>

        <label for="first_name">First Name</label>
        <input type="text" name="first_name" id="first_name" required />
        <p class="error_message" id="name_error"></p>

        <label for="last_name">Last Name</label>
        <input type="text" name="last_name" id="last_name" required />
        <p class="error_message" id="last_name_error"></p>

        <label for="gender">Gender</label>
        <select size="1" name="gender" id="gender">
            <option value="0">Male</option>
            <option value="1">Female</option>
        </select>
        <p class="error_message" id="gender_error"></p>

        <label for="birthday">Birthday</label>
        <input type="date" name="birthday" id="birthday" required/>
        <p class="error_message" id="birthday_error"></p>

        <button type="submit" id="reg_button">Register</button>

    </form>

</body>
</html>
