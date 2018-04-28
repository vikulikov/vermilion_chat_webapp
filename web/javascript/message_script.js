function updateScroll() {
    let element = document.getElementById("messages-window");
    element.scrollTop = element.scrollHeight;
}

document.onload = function () {updateScroll();};

const uri = location.pathname;
const webSocket = new WebSocket("ws://localhost:8080" + uri);

webSocket.onopen = function() {
    console.log("Connected with server...");
};

webSocket.onclose = function() {
    console.log("Disconnected...");
};

webSocket.onmessage = function(message) {
    let messageInfo = JSON.parse(message.data);
    let currentTime = moment().format('MMMM DD, YYYY, h:mm:ss');
    let counterUserLogin = messageInfo.login;
    let fullName = messageInfo.fullName;
    let content = messageInfo.content;


    let messageWindow = document.getElementById("messages-window");

    let message_container = document.createElement("div");
    message_container.className = "message anothers-message";
    message_container.innerHTML =
        "<a href=\"/profile?login=" + counterUserLogin + "\">" + fullName + "</a>\n\
        <p class=\"timestamp\">" + currentTime + "</p>\n\
        <p>" + content + "</p>";

    messageWindow.appendChild(message_container);
    updateScroll();
};

webSocket.onerror = function() {
    console.log("Something went wrong!");
};

function sendData() {
    let authorId = document.getElementsByName("author_id")[0].value;
    let chatId = document.getElementsByName("chat_id")[0].value;
    let content_area = document.getElementsByName("content")[0];
    let content = content_area.value;
    let login = document.getElementsByName("user_login")[0].value;
    let fullName = document.getElementsByName("full_name")[0].value;
    let currentTime = moment().format('MMMM DD, YYYY, h:mm:ss');
    let messageWindow = document.getElementById("messages-window");
    let message = document.createElement("div");
    message.className = "message";
    message.innerHTML =
        "<a href=\"/profile?login=" + login + "\">" + fullName + "</a>\n\
        <p class=\"timestamp\">" + currentTime + "</p>\n\
        <p>" + content + "</p>";

    messageWindow.appendChild(message);
    updateScroll();

    let data = JSON.stringify({
        authorId: authorId,
        chatId: chatId,
        content: content
    });
    webSocket.send(data);
    content_area.value = "";

}