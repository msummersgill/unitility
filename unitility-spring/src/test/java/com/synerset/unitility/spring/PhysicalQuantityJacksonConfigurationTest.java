package com.synerset.unitility.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synerset.unitility.unitsystem.common.Angle;
import com.synerset.unitility.unitsystem.thermodynamic.Temperature;
import com.synerset.unitility.unitsystem.util.PhysicalQuantityParsingFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.assertj.core.api.Assertions.assertThat;

class PhysicalQuantityJacksonConfigurationTest {

    @Test
    @DisplayName("should register jackson module and correctly resolve input string to physical quantity")
    void createPhysicalQuantityJacksonModule() throws JsonProcessingException {
        // Given
        PhysicalQuantityJacksonConfiguration jacksonConfiguration = new PhysicalQuantityJacksonConfiguration();
        PhysicalQuantityParsingFactory parsingRegistry = jacksonConfiguration.defaultParsingFactory();
        Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer
                = jacksonConfiguration.createPhysicalQuantityJacksonModule(parsingRegistry);
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        String inputQuantity = "{\"value\":20.0,\"unit\":\"°C\"}";

        // When
        jacksonCustomizer.customize(objectMapperBuilder);
        ObjectMapper objectMapper = objectMapperBuilder.build();
        Temperature resolvedQuantity = objectMapper.readValue(inputQuantity, Temperature.class);

        // Then
        assertThat(resolvedQuantity).isNotNull().isEqualTo(Temperature.ofCelsius(20));
    }

    @Test
    @DisplayName("should register jackson module and correctly resolve input string to physical quantity")
    void createPhysicalQuantityJacksonModuleSINoUnits() throws JsonProcessingException {
        // Given
        PhysicalQuantityJacksonConfiguration jacksonConfiguration = new PhysicalQuantityJacksonConfiguration();
        PhysicalQuantityParsingFactory parsingRegistry = jacksonConfiguration.SIParsingFactory();
        Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer
                = jacksonConfiguration.createPhysicalQuantityJacksonModuleNoUnits(parsingRegistry);
        Jackson2ObjectMapperBuilder objectMapperBuilder = Jackson2ObjectMapperBuilder.json();
        String inputQuantity = "3.14159265358979";

        // When
        jacksonCustomizer.customize(objectMapperBuilder);
        ObjectMapper objectMapper = objectMapperBuilder.build();
        Angle resolvedQuantity = objectMapper.readValue(inputQuantity, Angle.class);

        // Then
        assertThat(resolvedQuantity).isNotNull().isEqualTo(Angle.ofRadians(3.14159265358979));


        String outputQuantity = objectMapper.writeValueAsString(resolvedQuantity);

        assertThat(outputQuantity).isEqualTo(inputQuantity);
    }

}