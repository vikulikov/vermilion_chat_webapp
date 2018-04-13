<#-- @ftlvariable name="last_name" type="java.lang.String" -->
<#-- @ftlvariable name="name" type="java.lang.String" -->
<#-- @ftlvariable name="login" type="java.lang.String" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../css/profile_style.css">
    <meta charset="UTF-8">
    <title>${login}-Vermilion</title>
</head>

<body>
    <div id="main-container" class="container">


        <div id="left-side" class="column_view">
            <div id="photo-container">
                <img id="avatar" src="../img/avatar_default.png" />
            </div>
        </div>


        <div id="right-side" class="column_view">
            <div id="info-container" class="column_view">
                <h1>${name} ${last_name}</h1>

                <div class="hidden"></div>


                <label for="info">Information</label>
                <div id="info" class="column_view">
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
</body>
</html>