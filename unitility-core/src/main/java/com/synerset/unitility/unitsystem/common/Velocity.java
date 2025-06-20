package com.synerset.unitility.unitsystem.common;

import com.synerset.unitility.unitsystem.CalculableQuantity;

import java.util.Objects;

public class Velocity implements CalculableQuantity<VelocityUnit, Velocity> {

    private final double value;
    private final double baseValue;
    private final VelocityUnit unitType;

    public Velocity(double value, VelocityUnit unitType) {
        this.value = value;
        if(unitType == null){
            unitType = VelocityUnits.METER_PER_SECOND;
        }
        this.unitType = unitType;
        this.baseValue = unitType.toValueInBaseUnit(value);
    }

    // Static factory methods
    public static Velocity of(double value, VelocityUnit unit) {
        return new Velocity(value, unit);
    }

    public static Velocity of(double value, String unitSymbol) {
        VelocityUnit resolvedUnit = VelocityUnits.fromSymbol(unitSymbol);
        return new Velocity(value, resolvedUnit);
    }
    
    public static Velocity ofMetersPerSecond(double value) {
        return new Velocity(value, VelocityUnits.METER_PER_SECOND);
    }

    public static Velocity ofCentimetersPerSecond(double value) {
        return new Velocity(value, VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public static Velocity ofKilometersPerHour(double value) {
        return new Velocity(value, VelocityUnits.KILOMETER_PER_HOUR);
    }

    public static Velocity ofInchesPerSecond(double value) {
        return new Velocity(value, VelocityUnits.INCH_PER_SECOND);
    }

    public static Velocity ofFeetPerSecond(double value) {
        return new Velocity(value, VelocityUnits.FEET_PER_SECOND);
    }

    public static Velocity ofMilesPerHour(double value) {
        return new Velocity(value, VelocityUnits.MILES_PER_HOUR);
    }

    public static Velocity ofKnots(double value) {
        return new Velocity(value, VelocityUnits.KNOT);
    }

    public static Velocity ofMach(double value) {
        return new Velocity(value, VelocityUnits.MACH);
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
    public VelocityUnit getUnit() {
        return unitType;
    }

    @Override
    public Velocity toBaseUnit() {
        double valueInBaseUnit = unitType.toValueInBaseUnit(value);
        return of(valueInBaseUnit, unitType.getBaseUnit());
    }

    @Override
    public Velocity toUnit(VelocityUnit targetUnit) {
        double valueInMetersPerSecond = unitType.toValueInBaseUnit(value);
        double valueInTargetUnit = targetUnit.fromValueInBaseUnit(valueInMetersPerSecond);
        return Velocity.of(valueInTargetUnit, targetUnit);
    }

    @Override
    public Velocity toUnit(String targetUnit) {
        VelocityUnit resolvedUnit = VelocityUnits.fromSymbol(targetUnit);
        return toUnit(resolvedUnit);
    }

    @Override
    public Velocity withValue(double value) {
        return Velocity.of(value, unitType);
    }

    // Convert to target unit
    public Velocity toMetersPerSecond() {
        return toUnit(VelocityUnits.METER_PER_SECOND);
    }

    public Velocity toCentimetersPerSecond() {
        return toUnit(VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public Velocity toKilometersPerHour() {
        return toUnit(VelocityUnits.KILOMETER_PER_HOUR);
    }

    public Velocity toInchesPerSecond() {
        return toUnit(VelocityUnits.INCH_PER_SECOND);
    }

    public Velocity toFeetPerSecond() {
        return toUnit(VelocityUnits.FEET_PER_SECOND);
    }

    public Velocity toMilesPerHour() {
        return toUnit(VelocityUnits.MILES_PER_HOUR);
    }

    public Velocity toKnots() {
        return toUnit(VelocityUnits.KNOT);
    }

    public Velocity toMach() {
        return toUnit(VelocityUnits.MACH);
    }

    // Get value in target unit
    public double getInMetersPerSecond() {
        return getInUnit(VelocityUnits.METER_PER_SECOND);
    }

    public double getInCentimetersPerSeconds() {
        return getInUnit(VelocityUnits.CENTIMETER_PER_SECOND);
    }

    public double getInKilometersPerHours() {
        return getInUnit(VelocityUnits.KILOMETER_PER_HOUR);
    }

    public double getInInchesPerSeconds() {
        return getInUnit(VelocityUnits.INCH_PER_SECOND);
    }

    public double getInFeetPerSeconds() {
        return getInUnit(VelocityUnits.FEET_PER_SECOND);
    }

    public double getInMilesPerHours() {
        return getInUnit(VelocityUnits.MILES_PER_HOUR);
    }

    public double getInKnots() {
        return getInUnit(VelocityUnits.KNOT);
    }

    public double getInMach() {
        return getInUnit(VelocityUnits.MACH);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity inputQuantity = (Velocity) o;
        return Double.compare(inputQuantity.toBaseUnit().getValue(), baseValue) == 0 && Objects.equals(unitType.getBaseUnit(), inputQuantity.getUnit().getBaseUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(baseValue, unitType.getBaseUnit());
    }

    @Override
    public String toString() {
        return "Velocity{" + value + " " + unitType.getSymbol() + '}';
    }

}
