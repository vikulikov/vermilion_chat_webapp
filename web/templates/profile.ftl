<#-- @ftlvariable name="guest_type" type="java.lang.String" -->
<#-- @ftlvariable name="email" type="java.lang.String" -->
<#-- @ftlvariable name="gender" type="java.lang.String" -->
<#-- @ftlvariable name="last_name" type="java.lang.String" -->
<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="login" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/profile_style.css">
    <link rel="stylesheet" href="../css/main_layout_style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto"/>
    <meta charset="UTF-8">
    <title>${login}-Vermilion</title>
</head>

<body>
    <div id="pseudo-body">
        <#include "main_layout.ftl">
        <div id="main-container" class="container">
            <div id="left-side" class="column-view">
                <div id="photo-container" class="column-view">
                    <img id="avatar" src="../img/avatar_default.jpg" />
                    <form>
                        <#if guest_type == "owner">
                            <button formmethod="post" formaction="edit">Edit Profile</button>
                        <#elseif guest_type == "observer">
                            <button formmethod="post" formaction="useraction" name="action" value="addfriend">Add to friends</button>
                            <button formmethod="post" formaction="useraction" name="action" value="message">Send Message</button>
                        </#if>
                    </form>
                </div>
            </div>

            <div id="right-side" class="column-view">
                <div id="info-container" class="column-view">
                    <h1>${name} ${last_name}</h1>

                    <div class="hidden"></div>

                    <label for="info">Information</label>
                    <div id="info" class="column-view">
                        <label for="gender">Gender</label>
                        <p id="gender">${gender}</p> <br>

                        <label for="E-mail">E-mail</label>
                        <p id="E-mail">${email}</p> <br>

                        <label for="Birthday">Birthday</label>
                        <p id="Birthday">${birthday}</p> <br>
                    </div>
                </div>
                <div id="friends-container">
                    <label for="friends-info">Friends</label>
                    <div id="friends-info">
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#if guest_type == "stranger">
        <script type="text/javascript" src="../javascript/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="../javascript/login_script.js"></script>
    </#if>

</body>
</html>