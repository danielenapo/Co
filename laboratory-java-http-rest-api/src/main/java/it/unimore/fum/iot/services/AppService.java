package it.unimore.fum.iot.services;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import it.unimore.fum.iot.model.DeviceDescriptor;
import it.unimore.fum.iot.resources.DeviceResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class AppService extends Application<AppConfig> {

    public static void main(String[] args) throws Exception{
        new AppService().run(new String[]{"server", args.length > 0 ? args[0] : "configuration.yml"});
    }

    public void run(AppConfig appConfig, Environment environment) throws Exception {
        addDeviceCoffee(appConfig);
        addDeviceConditioner(appConfig);
        //Add our defined resources
        environment.jersey().register(new DeviceResource(appConfig));
    }

    private void addDeviceCoffee(AppConfig appConfig){

        DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
        deviceDescriptor.setUuid("device00001");
        deviceDescriptor.setIp("192.168.0.225");
        deviceDescriptor.setPort(5683);
        deviceDescriptor.setProtocol("coap");
        deviceDescriptor.setDisplayName("Coffee machine");
        File img = new File("C:\\Users\\gnele\\IdeaProjects\\laboratory-java-http-rest-api\\src\\main\\java\\it\\unimore\\fum\\iot\\services\\images\\Radioheadokcomputer.png");
        deviceDescriptor.setImage(base64Converter(img));

        appConfig.getDeviceManager().createNewDevice(deviceDescriptor);
    }

    private void addDeviceConditioner(AppConfig appConfig){

        DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
        deviceDescriptor.setUuid("device00002");
        deviceDescriptor.setIp("192.168.0.225");
        deviceDescriptor.setPort(5683);
        deviceDescriptor.setProtocol("coap");
        deviceDescriptor.setDisplayName("Air Conditioner");
        File img = new File("C:\\Users\\gnele\\IdeaProjects\\laboratory-java-http-rest-api\\src\\main\\java\\it\\unimore\\fum\\iot\\services\\images\\solaris.jpg");
        deviceDescriptor.setImage(base64Converter(img));

        appConfig.getDeviceManager().createNewDevice(deviceDescriptor);
    }


    public static String base64Converter(File file){
        Base64.Encoder encoder= Base64.getEncoder();
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = encoder.encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encodedfile;
    }
}