package pl.recordit.aop.proxy;

import java.lang.reflect.Proxy;

interface Adder{
    int add(int b, int a);
}

public class ProxyDemo {
    public static void main(String[] args) {
        Adder superAdder = (a, b) -> a + b;

        Sender sender = new MailSender();
        Sender proxySender = (Sender) Proxy.newProxyInstance(
                Sender.class.getClassLoader(),
                new Class[]{Sender.class},
                (proxy, method, arg) ->{

                    final Object ret = method.invoke(sender, arg);
                    String result = (String) ret;
                    result = "From proxy " + result;
                    return result;
                }
        );
        System.out.println(proxySender.send("Hello"));
    }
}
