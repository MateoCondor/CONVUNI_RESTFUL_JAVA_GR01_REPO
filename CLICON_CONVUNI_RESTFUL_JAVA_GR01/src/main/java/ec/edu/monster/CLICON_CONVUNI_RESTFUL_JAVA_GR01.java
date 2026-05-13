package ec.edu.monster;

import ec.edu.monster.api.GenericRestConsumer;
import ec.edu.monster.controllers.AuthController;
import ec.edu.monster.controllers.UnitConversionController;
import ec.edu.monster.models.User;
import ec.edu.monster.services.AuthService;
import ec.edu.monster.services.UnitConversionService;
import ec.edu.monster.views.LoginView;
import ec.edu.monster.views.UnitConversionView;

public class CLICON_CONVUNI_RESTFUL_JAVA_GR01 {

    public static void main(String[] args) {
        GenericRestConsumer client = new GenericRestConsumer("http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01");

        AuthService authService = new AuthService(client);
        LoginView loginView = new LoginView();

        AuthController authController = new AuthController(authService, loginView);

        User user = authController.runLogin();

        if (user.isAuth()) {
            UnitConversionService unitConversionService = new UnitConversionService(client);
            UnitConversionView unitConversionView = new UnitConversionView();

            UnitConversionController unitConversionController = new UnitConversionController(
                    unitConversionService,
                    unitConversionView);

            unitConversionController.run();
        }
    }
}
