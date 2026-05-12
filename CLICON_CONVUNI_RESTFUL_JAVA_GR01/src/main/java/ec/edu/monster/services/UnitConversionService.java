package ec.edu.monster.services;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.ObjectMapper;

import ec.edu.monster.clients.rest.ApiException;
import ec.edu.monster.clients.rest.api.DefaultApi;
import ec.edu.monster.clients.rest.model.ConversionRequest;
import ec.edu.monster.clients.rest.model.ConversionResult;
import ec.edu.monster.models.LengthConversion;
import ec.edu.monster.models.MassConversion;
import ec.edu.monster.models.TemperatureConversion;
import ec.edu.monster.models.UnitConversionResult;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnitConversionService {
    private final DefaultApi client;
    private final ObjectMapper mapper;

    public CompletableFuture<UnitConversionResult> convertMass(MassConversion conversion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ConversionRequest dto = new ConversionRequest();
                dto.fromUnit(conversion.getFrom().value);
                dto.toUnit(conversion.getTo().value);
                dto.value(BigDecimal.valueOf(conversion.getValue()));

                Object response = client.convertMass(dto);
                ConversionResult conversionResult = mapper.convertValue(response, ConversionResult.class);

                return new UnitConversionResult(conversionResult.getConvertedValue().doubleValue(),
                        conversionResult.getMessage().toString());
            } catch (ApiException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado: " + ex.getMessage());
            }
        });
    }

    public CompletableFuture<UnitConversionResult> convertLength(LengthConversion conversion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ConversionRequest dto = new ConversionRequest();
                dto.fromUnit(conversion.getFrom().value);
                dto.toUnit(conversion.getTo().value);
                dto.value(BigDecimal.valueOf(conversion.getValue()));

                Object response = client.convertLength(dto);
                ConversionResult conversionResult = mapper.convertValue(response, ConversionResult.class);

                return new UnitConversionResult(conversionResult.getConvertedValue().doubleValue(),
                        conversionResult.getMessage().toString());
            } catch (ApiException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado: " + ex.getMessage());
            }
        });
    }

    public CompletableFuture<UnitConversionResult> convertTemperature(TemperatureConversion conversion) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ConversionRequest dto = new ConversionRequest();
                dto.fromUnit(conversion.getFrom().value);
                dto.toUnit(conversion.getTo().value);
                dto.value(BigDecimal.valueOf(conversion.getValue()));

                Object response = client.convertTemperature(dto);
                ConversionResult conversionResult = mapper.convertValue(response, ConversionResult.class);

                return new UnitConversionResult(conversionResult.getConvertedValue().doubleValue(),
                        conversionResult.getMessage().toString());
            } catch (ApiException ex) {
                throw new RuntimeException("Error en el servidor de conversión: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error inesperado: " + ex.getMessage());
            }
        });
    }
}
