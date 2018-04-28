<#-- @ftlvariable name="chats" type="java.util.ArrayList<Chat>" -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>messages-Vermilion</title>
</head>
<body>

    <div id="main-container">
        <#include "main_layout.ftl">

        <div id="chats-container">
            <#list chats as chat>


            <#else>
                <h2>You still do not have any chats with someone!</h2>
            </#list>
        </div>
    </div>

</body>
</html>