package ec.edu.monster.services;

import java.util.concurrent.CompletableFuture;

import ec.edu.monster.api.GenericRestConsumer;
import ec.edu.monster.dto.UnitConversionRequest;
import ec.edu.monster.dto.UnitConversionResponse;
import ec.edu.monster.models.LengthConversion;
import ec.edu.monster.models.MassConversion;
import ec.edu.monster.models.TemperatureConversion;
import ec.edu.monster.models.UnitConversionResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnitConversionService {
    private final GenericRestConsumer consumer;
    private static final String BASE_PATH = "/resources/conversion/";

    public CompletableFuture<UnitConversionResult> convertMass(MassConversion conversion) {
        return callConversionApi(
                BASE_PATH + "convertMass",
                new UnitConversionRequest(conversion.getFrom().value, conversion.getTo().value, conversion.getValue()));
    }

    public CompletableFuture<UnitConversionResult> convertLength(LengthConversion conversion) {
        return callConversionApi(
                BASE_PATH + "convertLength",
                new UnitConversionRequest(conversion.getFrom().value, conversion.getTo().value, conversion.getValue()));
    }

    public CompletableFuture<UnitConversionResult> convertTemperature(TemperatureConversion conversion) {
        return callConversionApi(
                BASE_PATH + "convertTemperature",
                new UnitConversionRequest(conversion.getFrom().value, conversion.getTo().value, conversion.getValue()));
    }

    private CompletableFuture<UnitConversionResult> callConversionApi(String path, UnitConversionRequest request) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                UnitConversionResponse response = consumer.post(path, request, UnitConversionResponse.class);
                return new UnitConversionResult(response.convertedValue(), response.message());
            } catch (RuntimeException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado en la comunicación: " + ex.getMessage());
            }
        });
    }
}
