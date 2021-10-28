package pl.recordit.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/")
    public ResponseEntity<Object> home(){
        Map<String ,Object> map = new HashMap<>();
        map.put("message", "Hello");
        return ResponseEntity.ok(map);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok("Login");
    }
}
