package task.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import task.rest.Model.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        String URL = "http://94.198.50.185:7081/api/users";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        //Получение пользователей
        RequestEntity<User> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(URL));
        ResponseEntity<String> responseGetAllUsers = restTemplate.exchange(requestEntity, String.class);

        //Cохраняем сессию
        String sessionId = responseGetAllUsers.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        headers.set(HttpHeaders.COOKIE, sessionId);

        //Сохранение пользователя
        User user = new User(3, "James", "Brown", (byte) 11);
        RequestEntity<User> requestEntitySaveUser = RequestEntity.post(new URI(URL)).headers(headers).body(user);
        ResponseEntity<String> responseSaveUser = restTemplate.exchange(requestEntitySaveUser, String.class);
        String firstCode = responseSaveUser.getBody();

        //Изменение пользователя
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> responseGetUserbyId3= restTemplate.exchange(URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {});
        List<User> userList = responseGetUserbyId3.getBody();

        User updateUser = null;
        for (User u : userList) {
            if (u.getId() == 3) {
                updateUser = u;
                break;
            }
        }

        updateUser.setName("Thomas");
        updateUser.setLastName("Shelby");

        RequestEntity<User> requestEntityUpdateUser = RequestEntity.put(new URI(URL)).headers(headers).body(updateUser);
        ResponseEntity<String> responseUpdateUser= restTemplate.exchange(requestEntityUpdateUser, String.class);
        String secondCode = responseUpdateUser.getBody();

        //Удаление пользователя
        ResponseEntity<String> responseDeleteUser = restTemplate.exchange(URL + "/3", HttpMethod.DELETE, entity, String.class);
        String tretiyCode = responseDeleteUser.getBody();

        System.out.println(firstCode + secondCode + tretiyCode);
    }
}