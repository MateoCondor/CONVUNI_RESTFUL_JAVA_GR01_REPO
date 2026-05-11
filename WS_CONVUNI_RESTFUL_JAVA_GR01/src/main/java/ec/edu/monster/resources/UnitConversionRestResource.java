package ec.edu.monster.resources;

import ec.edu.monster.controller.AuthenticationController;
import ec.edu.monster.controller.ConversionController;
import ec.edu.monster.model.ConversionRequest;
import ec.edu.monster.model.ConversionResponse;
import ec.edu.monster.model.LoginRequest;
import ec.edu.monster.model.LoginResponse;
import ec.edu.monster.service.AuthenticationService;
import ec.edu.monster.service.UnitConversionService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("conversion")
public class UnitConversionRestResource {

    private static final AuthenticationService AUTHENTICATION_SERVICE = new AuthenticationService();
    private static final AuthenticationController AUTH_CONTROLLER =
            new AuthenticationController(AUTHENTICATION_SERVICE);
    private static final ConversionController CONVERSION_CONTROLLER =
            new ConversionController(new UnitConversionService());

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) {
        String username = request == null ? null : request.getUsername();
        String password = request == null ? null : request.getPassword();
        LoginResponse response = AUTH_CONTROLLER.login(username, password);
        return Response.ok(response).build();
    }

    @POST
    @Path("convertLength")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertLength(ConversionRequest request) {
        double value = request == null ? 0 : request.getValue();
        String fromUnit = request == null ? null : request.getFromUnit();
        String toUnit = request == null ? null : request.getToUnit();
        ConversionResponse response = CONVERSION_CONTROLLER.convertLength(value, fromUnit, toUnit);
        return Response.ok(response).build();
    }

    @POST
    @Path("convertMass")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertMass(ConversionRequest request) {
        double value = request == null ? 0 : request.getValue();
        String fromUnit = request == null ? null : request.getFromUnit();
        String toUnit = request == null ? null : request.getToUnit();
        ConversionResponse response = CONVERSION_CONTROLLER.convertMass(value, fromUnit, toUnit);
        return Response.ok(response).build();
    }

    @POST
    @Path("convertTemperature")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertTemperature(ConversionRequest request) {
        double value = request == null ? 0 : request.getValue();
        String fromUnit = request == null ? null : request.getFromUnit();
        String toUnit = request == null ? null : request.getToUnit();
        ConversionResponse response = CONVERSION_CONTROLLER.convertTemperature(value, fromUnit, toUnit);
        return Response.ok(response).build();
    }

    @GET
    @Path("healthCheck")
    @Produces(MediaType.TEXT_PLAIN)
    public Response healthCheck() {
        return Response.ok("REST conversion service is running.").build();
    }
}
