package com.synerset.unitility.unitsystem.thermodynamic;

import com.synerset.unitility.unitsystem.PhysicalQuantity;

import java.util.Objects;

public class Temperature implements PhysicalQuantity<TemperatureUnits> {

    public static final Temperature PHYSICAL_MIN_LIMIT = Temperature.ofKelvins(0);
    private final double value;
    private final double baseValue;
    private final TemperatureUnits unit;

    public Temperature(double value, TemperatureUnits unit) {
        this.value = value;
        this.unit = unit;
        this.baseValue = unit.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Temperature of(double value, TemperatureUnits unit) {
        return new Temperature(value, unit);
    }

    public static Temperature ofKelvins(double value) {
        return new Temperature(value, TemperatureUnits.KELVIN);
    }

    public static Temperature ofCelsius(double value) {
        return new Temperature(value, TemperatureUnits.CELSIUS);
    }

    public static Temperature ofFahrenheit(double value) {
        return new Temperature(value, TemperatureUnits.FAHRENHEIT);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public double getBaseValue() {
        return baseValue;
    }

    @Override
    public TemperatureUnits getUnit() {
        return unit;
    }

    @Override
    public Temperature toBaseUnit() {
        double valueInKelvin = unit.toValueInBaseUnit(value);
        return Temperature.of(valueInKelvin, TemperatureUnits.KELVIN);
    }

    @Override
    public Temperature toUnit(TemperatureUnits targetUnit) {
        double valueInKelvin = unit.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInKelvin);
        return Temperature.of(valueInTargetUnit, targetUnit);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Temperature createNewWithValue(double value) {
        return Temperature.of(value, unit);
    }

    // Convert to target unit
    public Temperature toKelvin() {
        return toUnit(TemperatureUnits.KELVIN);
    }

    public Temperature toCelsius() {
        return toUnit(TemperatureUnits.CELSIUS);
    }

    public Temperature toFahrenheit() {
        return toUnit(TemperatureUnits.FAHRENHEIT);
    }

    // Get value in target unit
    public double getInKelvins() {
        return getIn(TemperatureUnits.KELVIN);
    }

    public double getInCelsius() {
        return getIn(TemperatureUnits.CELSIUS);
    }

    public double getInFahrenheits() {
        return getIn(TemperatureUnits.FAHRENHEIT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Temperature inputQuantity = (Temperature) o;
        return Double.compare(inputQuantity.toBaseUnit().value, baseValue) == 0 && Objects.equals(unit.getBaseUnit(),
                inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unit.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Temperature{" + value + " " + unit.getSymbol() + '}';
    }

}
