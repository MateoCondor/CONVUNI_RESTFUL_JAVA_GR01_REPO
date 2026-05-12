package ec.edu.monster.controllers;

import javax.swing.SwingUtilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.edu.monster.clients.rest.api.DefaultApi;
import ec.edu.monster.services.AuthService;
import ec.edu.monster.services.UnitConversionService;
import ec.edu.monster.views.ILoginView;
import ec.edu.monster.views.IUnitConversionView;
import ec.edu.monster.views.UnitConversionView;

public class AuthController {
    private final ILoginView view;
    private final AuthService service;
    private final DefaultApi client;
    private final ObjectMapper mapper;

    public AuthController(ILoginView view, AuthService service, DefaultApi client, ObjectMapper mapper) {
        this.view = view;
        this.service = service;
        this.client = client;
        this.mapper = mapper;
        this.view.setSubmitListener(e -> login());
    }

    private void login() {
        String username = view.getUsername();
        String password = view.getPassword();
        view.resetForm();

        // Ejecución asíncrona para no bloquear la interfaz de usuario
        service.Login(username, password)
                .thenAccept(user -> {
                    // Volvemos al hilo de la UI (EDT) para cambios visuales
                    SwingUtilities.invokeLater(() -> {
                        if (!user.isAuth()) {
                            view.showErrorMessage("Usuario o contraseña incorrectos");
                            return;
                        }

                        UnitConversionService unitConversionService = new UnitConversionService(client, mapper);

                        IUnitConversionView unitConversionView = new UnitConversionView();

                        unitConversionView.setLogoutListener(ev -> {
                            view.showView();
                            unitConversionView.hideView();
                        });

                        new UnitConversionController(unitConversionView, unitConversionService);

                        unitConversionView.showView();

                        view.hideView();
                    });
                })
                .exceptionally(ex -> {
                    // Manejo de errores de red o del servidor SOAP
                    SwingUtilities.invokeLater(() -> {
                        String message = (ex.getCause() != null) ? ex.getCause().getMessage() : ex.getMessage();
                        view.showErrorMessage(message);
                    });
                    return null;
                });
    }
}