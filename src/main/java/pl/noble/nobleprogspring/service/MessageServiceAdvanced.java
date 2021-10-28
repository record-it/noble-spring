package pl.noble.nobleprogspring.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("advanced")
@Primary
public class MessageServiceAdvanced implements MessageServiceI{

    @Override
    public String getMessage(int id) {
        return "Advanced";
    }
}
