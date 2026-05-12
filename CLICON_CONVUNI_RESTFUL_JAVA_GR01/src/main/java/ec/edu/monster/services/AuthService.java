package ec.edu.monster.services;

import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.edu.monster.clients.rest.ApiException;
import ec.edu.monster.clients.rest.api.DefaultApi;
import ec.edu.monster.clients.rest.model.LoginRequest;
import ec.edu.monster.clients.rest.model.LoginResult;
import ec.edu.monster.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthService {
    private final DefaultApi client;
    private final ObjectMapper mapper;

    public CompletableFuture<User> Login(String username, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LoginRequest dto = new LoginRequest();
                dto.setUsername(username);
                dto.setPassword(password);

                Object responseObj = client.login(dto);
                LoginResult response = mapper.convertValue(responseObj, LoginResult.class);

                return new User(dto.getUsername().toString(), response.getSuccess());
            } catch (ApiException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado: " + ex.getMessage());
            }
        });
    }
}
