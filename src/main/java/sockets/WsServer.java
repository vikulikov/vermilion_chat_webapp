package sockets;


import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WsServer {

    @OnOpen
    public void onOpen() {
        System.out.println("Open Connection...");
    }

    @OnClose
    public void onClose() {
        System.out.println("Close Connection...");
    }

    @OnMessage
    public String onMessage(String message) {

        System.out.println("Message from client: " + message);

        return "Message sent from server: " + message;
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }


}
