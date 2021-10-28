package pl.noble.nobleprogspring.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service("simple")
public class MessageService implements MessageServiceI{
    public List<String> messages = List.of("Hello","Welcome", "Hi");

    @Override
    public String getMessage(int id) {
        return messages.get(id);
    }
}
