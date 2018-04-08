$(document).ready(function() {

    $("#my_form").on("submit", function(event) {
        event.preventDefault();

        var email = $("#email").val();
        var pswd = $("#password").val();
        if ((email === "" || pswd === "") || (email == null || pswd == null)) {
            $("#invalid-user").text("Please fill in all fields!");
            return;
        }

        $.post("",
            {
                email: email,
                password: pswd
            },
            function(message, status) {
                console.log("Response status: " + status);
                $("#invalid-user").text(message);
            });

    });

});

