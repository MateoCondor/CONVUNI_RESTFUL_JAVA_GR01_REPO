/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.monster;

import org.openapitools.jackson.nullable.JsonNullableModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ec.edu.monster.clients.rest.ApiClient;
import ec.edu.monster.clients.rest.api.DefaultApi;
import ec.edu.monster.controllers.AuthController;
import ec.edu.monster.services.AuthService;
import ec.edu.monster.views.LoginView;

/**
 *
 * @author alexa
 */
public class CLIESC_CONVUNI_RESTFUL_JAVA_GR01 {

    public static void main(String[] args) {

        try {
            ApiClient apiClient = new ApiClient();
            apiClient.updateBaseUri("http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01");

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new JsonNullableModule());

            DefaultApi client = new DefaultApi(apiClient);

            AuthService authService = new AuthService(client, mapper);

            java.awt.EventQueue.invokeLater(() -> {
                try {
                    LoginView loginView = new LoginView();
                    new AuthController(loginView, authService, client, mapper);
                    loginView.setVisible(true);
                    loginView.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception ex) {
            System.err.println("Error al conectar con el servicio SOAP: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
