<#-- @ftlvariable name="user" type="java.util.Map<String, String>" -->
<#-- @ftlvariable name="chat_id" type="long" -->
<#-- @ftlvariable name="messages" type="java.util.List<Message>" -->
<#-- @ftlvariable name="counter_user.last_name" type="java.lang.String" -->
<#-- @ftlvariable name="counter_user.name" type="java.lang.String" -->
<#-- @ftlvariable name="counter_user.login" type="java.lang.String" -->
<#-- @ftlvariable name="counter_user" type="java.util.Map<String, Object>" -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <link rel="stylesheet" href="../css/main_layout_style.css"/>
    <link rel="stylesheet" href="../css/message_style.css"/>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto"/>
    <title>${counter_user.name} ${counter_user.last_name}-Messages</title>
</head>
<body>
    <div id="main-container">
        <#include "main_layout.ftl" />
        <div id="chat-container">
            <a href="/profile?login=${counter_user.login}">
                <p id="counter-user-full-name">${counter_user.name} ${counter_user.last_name}</p>
            </a>
            <div id="messages-window">
                <#list messages as message>
                    <#if message.getAuthorId() == user.id>
                        <div class="message">
                            <a href="/profile?login=${user.login}">${user.name} ${user.last_name}</a>
                            <p class="timestamp">${message.getCreationTime()}</p>
                            <p>${message.getContent()}</p>
                        </div>
                    <#else>
                    <div class="message anothers-message">
                        <a href="/profile?login=${counter_user.login}">${counter_user.name} ${counter_user.last_name}</a>
                        <p class="timestamp">${message.getCreationTime()}</p>
                        <p>${message.getContent()}</p>
                    </div>
                    </#if>
                <#else>
                    <p>There are still not any messages</p>
                </#list>
            </div>
            <div id="message-form">
                <input type="hidden" name="full_name" value="${user.name} ${user.last_name}">
                <input type="hidden" name="user_login" value="${user.login}">
                <input type="hidden" name="author_id" value="${user.id}">
                <input type="hidden" name="chat_id" value="${chat_id}">
                <textarea name="content" placeholder="What would you like to send?"></textarea>
                <button onclick="sendData()">Send Message</button>
            </div>
        </div>
    </div>

    <script src="../javascript/moment.min.js"></script>
    <script src="../javascript/message_script.js" type="text/javascript"></script>

</body>
</html>