<#-- @ftlvariable name="user.name" type="java.lang.String" -->
<#-- @ftlvariable name="user.last_name" type="java.lang.String" -->
<#-- @ftlvariable name="user.login" type="java.lang.String" -->
<#-- @ftlvariable name="guest_type" type="java.lang.String" -->
<#-- @ftlvariable name="users_info" type="java.util.List<Map<String, Object>>" -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../css/main_layout_style.css">
    <link rel="stylesheet" href="../css/users_style.css">
    <title>All Users-Vermilion</title>
</head>
<body>
    <div id="main-container">
        <#include "main_layout.ftl">
        <div id="user-container">
            <#list users_info as user>
                <div class="user">
                    <a href="/profile?login=${user.login}">
                        <img class="avatar" src="../img/avatar_default.jpg" >
                    </a>
                    <a href="/profile?login=${user.login}"><h2>${user.name} ${user.last_name}</h2></a>
                    <#if guest_type != "stranger">
                        <form class="actions" action="/useraction" method="post">
                            <input type="hidden" name="counter_login" value="${user.login}"/>
                            <button name="action" value="add_friend">Add friend</button>
                            <button formaction="/messages">Write message</button>
                        </form>
                    </#if>
                </div>
            <#else>
                <p>For some reason there is no any user!</p>
            </#list>
        </div>
    </div>
</body>
</html>