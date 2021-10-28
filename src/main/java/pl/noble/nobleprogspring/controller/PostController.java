package pl.noble.nobleprogspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.noble.nobleprogspring.model.Post;

@RestController
@Slf4j
public class PostController {
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts/";
    private RestTemplate template = new RestTemplate();
    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id){
        return template.getForObject(String.format("%s%d",POST_URL, id), Post.class);
    }

    @GetMapping("/posts/send")
    public Post sendPost(){
        HttpEntity<Post> request = new HttpEntity<>(Post.builder()
                .body("TEST")
                .title("TESTOWY")
                .userId(2)
                .build());
        return template.postForObject(POST_URL, request, Post.class);
    }

    @PostMapping(value = "/posts/form")
    public ResponseEntity<Post> addFromForm(@ModelAttribute Post post){
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/form")
    public void sendForm(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("userId","1");
        params.add("title","TEST");
        params.add("body","TEST");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        final ResponseEntity<Post> stringResponseEntity = template.postForEntity("http://localhost:9000/posts/form", request, Post.class);
        log.info(stringResponseEntity.toString());
    }
}
