package pl.recordit.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatService {
    private Set<WebSocketSession> sessionSet = Collections.synchronizedSet(new HashSet<>());

    public void registerSession(WebSocketSession session){
        sessionSet.add(session);
    }

    public void uregisterSession(WebSocketSession session){
        sessionSet.remove(session);
    }

    public void broadcast(String message, WebSocketSession origin){
        sessionSet.stream()
                .filter(s ->s != origin)
                .forEach(s -> {
                    try {
                        s.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
