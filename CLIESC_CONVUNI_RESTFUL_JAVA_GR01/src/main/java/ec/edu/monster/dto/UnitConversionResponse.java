package ec.edu.monster.dto;

public record UnitConversionResponse(
        boolean success,
        String message,
        String category,
        double inputValue,
        String fromUnit,
        String toUnit,
        double convertedValue) {

}
