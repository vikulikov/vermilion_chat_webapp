<#-- @ftlvariable name="guest_type" type="java.lang.String" -->
<#if guest_type == "stranger">
    <header>
        <div id="logo">
            <h1>Vermilion</h1>
        </div>
        <div id="header-buttons">
        </div>
    </header>

    <div id="sidebar">
        <nav>
            <form id="login_form">
                <label for="login">Login</label>
                <input type="email" name="email" id="email">
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
                <input type="submit" value="Log In">
                <button formaction="registration" formmethod="post">Registration</button>
            </form>
            <p id="invalid-user"></p>
        </nav>
    </div>
<#else>
    <header>
        <div id="logo">
            <h1>Vermilion</h1>
        </div>
        <div id="header-buttons">
            <form id="exit" action="/useraction" method="post">
                <button name="action" value="delete">Log out</button>
            </form>
        </div>
    </header>

    <div id="sidebar">
        <nav>
            <a href="profile" class="sidebar-link">My Profile</a>
            <a href="#" class="sidebar-link">Edit Profile</a>
            <a href="#" class="sidebar-link">Friends</a>
            <a href="#" class="sidebar-link">Messages</a>
            <a href="/users" class="sidebar-link">All Users</a>
        </nav>
    </div>
</#if>

