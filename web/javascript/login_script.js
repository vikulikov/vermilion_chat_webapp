$(document).ready(function() {

    $("#my_form").on("submit", function(event) {
        event.preventDefault();

        var email = $("#email").val();
        var pswd = $("#password").val();
        console.log(email + pswd);
        if ((email === "" || pswd === "") || (email == null || pswd == null)) {
            $("#invalid-user").text("Please fill in all fields!");
            return;
        }

        $.ajax({
            url:"",
            type:"POST",
            dataType:'json',
            data: {email: email, password: pswd},
            success: function(data){
                if (data.redirect) {
                    window.location.href = data.redirect;
                } else {
                    $("#invalid-user").text(data.message);
                }
            }

        });

    });

});

