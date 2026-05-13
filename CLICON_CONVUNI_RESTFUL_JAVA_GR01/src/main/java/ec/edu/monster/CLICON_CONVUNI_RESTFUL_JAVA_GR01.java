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
        GenericRestConsumer client = new GenericRestConsumer("http://10.40.24.51:8080/WS_CONVUNI_RESTFUL_JAVA_GR01");

        AuthService authService = new AuthService(client);
        LoginView loginView = new LoginView();

        AuthController authController = new AuthController(authService, loginView);

        User user = null;
        int maxLoginAttempts = 3;
        int currentLoginAttempt = 0;

        while (currentLoginAttempt < maxLoginAttempts) {
            currentLoginAttempt++;
            System.out
                    .println("\n--- Intento de inicio de sesión: " + currentLoginAttempt + " de " + maxLoginAttempts
                            + " ---");

            user = authController.runLogin();

            if (user != null && user.isAuth()) {
                System.out.println("¡Autenticación exitosa!");
                break;
            } else {
                System.out.println("Credenciales incorrectas o error en el servidor.");
                if (currentLoginAttempt < maxLoginAttempts) {
                    System.out.println("Por favor, intente de nuevo.");
                }
            }
        }

        if (user != null && user.isAuth()) {
            UnitConversionService unitConversionService = new UnitConversionService(client);
            UnitConversionView unitConversionView = new UnitConversionView();

            UnitConversionController unitConversionController = new UnitConversionController(
                    unitConversionService,
                    unitConversionView);

            unitConversionController.run();
        } else {
            System.out.println("\n[ !!! ] Se han agotado los 3 intentos. Saliendo del programa...");
        }
    }
}
