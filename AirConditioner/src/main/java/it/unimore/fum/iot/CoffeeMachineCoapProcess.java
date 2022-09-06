package it.unimore.fum.iot;

import it.unimore.fum.iot.resource.*;
import org.eclipse.californium.core.CoapServer;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 11:57
 */
public class CoffeeMachineCoapProcess extends CoapServer {

    public CoffeeMachineCoapProcess(){

        super();

        String deviceId = "coffee-machine-0001";

        this.add(new TemperatureSensorResource("temperature", deviceId));
        this.add(new CapsulePresenceSensorResource("capsule", deviceId));
        this.add(new CoffeeActuatorResource("coffee", deviceId));
        this.add(new SliderActuatorResource("slider", deviceId));
        this.add(new PowerActuatorResource("power", deviceId));
        this.add(new WaterPercentageResource("water", deviceId));
    }

    public static void main(String[] args) {

        CoffeeMachineCoapProcess coapServer = new CoffeeMachineCoapProcess();
        coapServer.start();

        coapServer.getRoot().getChildren().forEach(resource -> {
            System.out.println(String.format("Resource %s -> URI: %s (Observable: %b)", resource.getName(), resource.getURI(), resource.isObservable()));
        });
    }
}
