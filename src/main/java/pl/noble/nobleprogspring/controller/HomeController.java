package pl.noble.nobleprogspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.noble.nobleprogspring.service.MessageService;
import pl.noble.nobleprogspring.service.MessageServiceI;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    @Qualifier("simple")
    private MessageServiceI service;

    @Autowired
    private RequestMessage requestMessage;

    @Autowired
    private List<MessageServiceI> services;

    @Resource
    private String appName;

    @GetMapping("/services")
    public void services(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.getWriter().println(services);
    }

    @GetMapping("/")
    public void home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello From Servlet! " + appName);
    }

    @GetMapping("/messages")
    public void message(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);
        response.getWriter().println(service.getMessage(id) + requestMessage.requestMessage());
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name, Model model){
        model.addAttribute("name", name);
        return "index";
    }
}
