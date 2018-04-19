$(document).ready(function() {

    $("#login_form").on("submit", function(event) {
        event.preventDefault();

        let email = $("#email").val();
        let pswd = $("#password").val();
        if (email === "" || pswd === "") {
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

