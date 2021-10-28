package pl.recordit.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "Hello";
    }

    @GetMapping("/find")
    public void find(Long id){

    }
}
