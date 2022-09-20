package org.example;

import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {
    private RestTemplate restTemplate;
    private String cookies;

    private String answer;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> allUsers(){
        ResponseEntity<List<User>> responseEntity=restTemplate.exchange(URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<User>>() {});
        List<User>allUsers=responseEntity.getBody();
        HttpHeaders headers = new HttpHeaders();
        headers=responseEntity.getHeaders();
        cookies=headers.getFirst("Set-Cookie");
        cookies=cookies.substring(0, cookies.indexOf(';'));
        return allUsers;
    }
    public void addUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity requestEntity = new HttpEntity(user, headers);
        ResponseEntity <String> responseEntity=restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        answer=responseEntity.getBody();
    }

    public void updateUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity requestEntity = new HttpEntity(user, headers);
        ResponseEntity <String> responseEntity=restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        answer+=responseEntity.getBody();
    }
    public void deleteUser(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Cookie", cookies);
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity <String> responseEntity=restTemplate.exchange(URL+"/"+id, HttpMethod.DELETE, requestEntity, String.class);
        answer+=responseEntity.getBody();
        System.out.println(answer);
        //        restTemplate.delete(URL+"/"+id);
    }
}
