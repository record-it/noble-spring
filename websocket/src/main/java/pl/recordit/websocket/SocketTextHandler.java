package pl.recordit.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketTextHandler extends TextWebSocketHandler {

    private final ChatService service;

    public SocketTextHandler(ChatService service) {
        this.service = service;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        service.broadcast(message.getPayload(), session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        service.registerSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        service.uregisterSession(session);
    }

}
