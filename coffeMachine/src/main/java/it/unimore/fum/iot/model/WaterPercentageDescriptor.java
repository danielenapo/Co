package it.unimore.fum.iot.model;

import java.util.Random;

public class WaterPercentageDescriptor {
    private long timestamp;

    private double value;

    private String unit = "%";

    private static final int TEMPERATURE_VALUE_BOUND = 30;

    private static final int TEMPERATURE_START_VALUE = 20;

    private transient Random random;

    public WaterPercentageDescriptor() {
        this.random = new Random();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void measureTemperatureValue(){
        this.value = this.random.nextInt(100);
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WaterPercentageDescriptor{");
        sb.append("timestamp=").append(timestamp);
        sb.append(", value=").append(value);
        sb.append(", unit='").append(unit).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
