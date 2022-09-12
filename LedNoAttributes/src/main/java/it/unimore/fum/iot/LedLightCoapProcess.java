package it.unimore.fum.iot;

import it.unimore.fum.iot.resource.*;
import org.eclipse.californium.core.CoapServer;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 11:57
 */
public class LedLightCoapProcess extends CoapServer {

    public LedLightCoapProcess(){

        super();

        String deviceId = "air-conditioner-0001";

        this.add(new ColorActuatorResource("color", deviceId));
        this.add(new BrightnessActuatorResource("brightness", deviceId));
        this.add(new PowerActuatorResource("power", deviceId));
    }

    public static void main(String[] args) {

        LedLightCoapProcess coapServer = new LedLightCoapProcess();
        coapServer.start();

        coapServer.getRoot().getChildren().forEach(resource -> {
            System.out.println(String.format("Resource %s -> URI: %s (Observable: %b)", resource.getName(), resource.getURI(), resource.isObservable()));
        });
    }
}
