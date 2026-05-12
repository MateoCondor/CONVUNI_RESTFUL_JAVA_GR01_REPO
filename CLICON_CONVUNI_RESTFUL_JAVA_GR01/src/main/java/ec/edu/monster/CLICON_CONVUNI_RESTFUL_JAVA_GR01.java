package ec.edu.monster;

import org.openapitools.jackson.nullable.JsonNullableModule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import ec.edu.monster.clients.rest.ApiClient;
import ec.edu.monster.clients.rest.api.DefaultApi;
import ec.edu.monster.controllers.AuthController;
import ec.edu.monster.controllers.UnitConversionController;
import ec.edu.monster.models.User;
import ec.edu.monster.services.AuthService;
import ec.edu.monster.services.UnitConversionService;
import ec.edu.monster.views.LoginView;
import ec.edu.monster.views.UnitConversionView;

public class CLICON_CONVUNI_RESTFUL_JAVA_GR01 {

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        apiClient.updateBaseUri("http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01");

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new JsonNullableModule());

        DefaultApi client = new DefaultApi(apiClient);

        AuthService authService = new AuthService(client, mapper);
        LoginView loginView = new LoginView();

        AuthController authController = new AuthController(authService, loginView);

        User user = authController.runLogin();

        if (user.isAuth()) {
            UnitConversionService unitConversionService = new UnitConversionService(client, mapper);
            UnitConversionView unitConversionView = new UnitConversionView();

            UnitConversionController unitConversionController = new UnitConversionController(
                    unitConversionService,
                    unitConversionView);

            unitConversionController.run();
        }
    }
}
