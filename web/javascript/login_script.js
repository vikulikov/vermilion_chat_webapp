
function sendUserData() {
    let email = document.getElementById("email").value;
    let pswd = document.getElementById("password").value;

    if (email == "" || pswd == "" ) {
        document.getElementById("invalid-user").innerText = "Please fill in all fields!";
        document.getElementById("invalid-user").classList.add("shown");
        console.log(email + password + "smth");
    }

    // let xhttp = new XMLHttpRequest();
    //
    // xhttp.onreadystatechange = function() {
    //     if (this.readyState === 4 && this.status === 200) {
    //         document.getElementById("invalid-user").innerText = this.responseText;
    //     }
    // };
    //
    // xhttp.open("POST", "", true);
    // xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    // xhttp.send("email=" + email + "&password=" + pswd);

}