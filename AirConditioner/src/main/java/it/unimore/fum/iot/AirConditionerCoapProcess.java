package it.unimore.fum.iot;

import it.unimore.fum.iot.resource.*;
import org.eclipse.californium.core.CoapServer;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 11:57
 */
public class AirConditionerCoapProcess extends CoapServer {

    public AirConditionerCoapProcess(){

        super();

        String deviceId = "air-conditioner-0001";

        this.add(new TemperatureSensorResource("temperature", deviceId));
        this.add(new ModeActuatorResource("mode", deviceId));
        this.add(new SliderActuatorResource("slider", deviceId));
        this.add(new PowerActuatorResource("power", deviceId));
        this.add(new WaterPercentageResource("water", deviceId));
    }

    public static void main(String[] args) {

        AirConditionerCoapProcess coapServer = new AirConditionerCoapProcess();
        coapServer.start();

        coapServer.getRoot().getChildren().forEach(resource -> {
            System.out.println(String.format("Resource %s -> URI: %s (Observable: %b)", resource.getName(), resource.getURI(), resource.isObservable()));
        });
    }
}
