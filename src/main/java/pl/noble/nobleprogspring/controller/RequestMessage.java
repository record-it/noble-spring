package pl.noble.nobleprogspring.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.rmi.server.UID;

@Component
@RequestScope
public class RequestMessage {
    private final UID uid = new UID();
    public String requestMessage(){
        return uid.toString();
    }
}
