package yte.intern.springweb;

import org.junit.Test;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import yte.intern.springweb.dto.Comment;
import yte.intern.springweb.dto.Post;

import java.net.URI;
import java.util.Arrays;

public class RestTemplateTest {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Test
    public void tumPostlariGetir() {
        URI postsUri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("posts")
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity.get(postsUri).build();
        ResponseEntity<Post[]> response = restTemplate.exchange(requestEntity, Post[].class);
        System.out.println(Arrays.asList(response.getBody()));
    }

    @Test
    public void id2OlanPostuGetir(){
        URI post2 = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("posts/2")
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity.get(post2).build();
        ResponseEntity<Post> response = restTemplate.exchange(requestEntity, Post.class);
        System.out.println(Arrays.asList(response.getBody()));


    }
    @Test
    public void postEkle(){
        RequestEntity<Post> requestEntity = RequestEntity.post(URI.create("https://jsonplaceholder.typicode.com/posts"))
        .body(new Post(1L,null,"başlık", "abcdefghıijklmn"));
        ResponseEntity<Post> exchange = restTemplate.exchange(requestEntity, Post.class);
        System.out.println(Arrays.asList(exchange.getBody()));


    }

    @Test
    public void userIdilePostlarıGetir(){
        URI userId2 = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("posts/")
                .queryParam("userId", "2")
                .build()
                .toUri();
        RequestEntity<Void> requestEntity = RequestEntity.get(userId2).build();
        ResponseEntity<Post[]> response = restTemplate.exchange(requestEntity, Post[].class);
        System.out.println(Arrays.asList(response.getBody()));


    }
    @Test
    public void commentGüncelle(){
        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("comments", "5")
                .build()
                .toUri();
        var requestEntity = RequestEntity.put(uri).body(new Comment(1l, 3l,"gbg", "hjh","jhjh"));
        var response = restTemplate.exchange(requestEntity, Comment.class);
        System.out.println(response.getBody());
    }

    @Test
    public void postuSil(){
        URI uri = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .pathSegment("posts", "1")
                .build()
                .toUri();
        var requestEntity = RequestEntity.delete(uri).build();
        var response = restTemplate.exchange(requestEntity, Void.class);
        System.out.println(response.getBody());


    }

}
