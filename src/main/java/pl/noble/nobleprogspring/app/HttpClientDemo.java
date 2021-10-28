package pl.noble.nobleprogspring.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.noble.nobleprogspring.model.Post;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HttpClientDemo {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI("https://jsonplaceholder.typicode.com/posts/1"))
                .build();
        final HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        final Post post = mapper.readValue(send.body(), Post.class);
        System.out.println(post);
        final CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        System.out.println();
        response.whenCompleteAsync((res, error) ->{
            try {
                Post newPost = mapper.readValue(res.body(), Post.class);
                System.out.println(newPost);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(1000);
    }
}
