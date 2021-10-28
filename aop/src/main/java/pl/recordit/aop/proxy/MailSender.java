package pl.recordit.aop.proxy;

public class MailSender implements Sender{
    @Override
    public String send(String message) {
        return "Mail sender: " + message;
    }
}
