package ec.edu.monster.services;

import java.util.concurrent.CompletableFuture;

import ec.edu.monster.api.GenericRestConsumer;
import ec.edu.monster.dto.LoginRequest;
import ec.edu.monster.dto.LoginResponse;
import ec.edu.monster.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthService {
    private final GenericRestConsumer consumer;

    public CompletableFuture<User> Login(String username, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                LoginRequest dto = new LoginRequest(username, password);

                LoginResponse response = consumer.post("/resources/conversion/login", dto, LoginResponse.class);

                return new User(dto.username(), response.success());
            } catch (RuntimeException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado: " + ex.getMessage());
            }
        });
    }
}
