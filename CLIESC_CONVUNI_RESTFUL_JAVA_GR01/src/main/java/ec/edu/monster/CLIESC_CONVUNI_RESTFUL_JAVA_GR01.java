package ec.edu.monster;

import ec.edu.monster.api.GenericRestConsumer;
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
            GenericRestConsumer client = new GenericRestConsumer("http://localhost:8080/WS_CONVUNI_RESTFUL_JAVA_GR01");

            AuthService authService = new AuthService(client);

            java.awt.EventQueue.invokeLater(() -> {
                try {
                    LoginView loginView = new LoginView();
                    new AuthController(loginView, authService, client);
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
