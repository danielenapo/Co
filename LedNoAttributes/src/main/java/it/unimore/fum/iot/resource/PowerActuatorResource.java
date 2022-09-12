package it.unimore.fum.iot.resource;

import com.google.gson.Gson;
import it.unimore.fum.iot.model.PowerDescriptor;
import it.unimore.fum.iot.utils.SenMLPack;
import it.unimore.fum.iot.utils.SenMLRecord;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.Optional;

public class PowerActuatorResource extends CoapResource {
    private final String deviceId;
    private Gson gson;
    private PowerDescriptor powerDescriptor;

    public PowerActuatorResource(String name, String deviceId) {
        super(name);
        this.deviceId = deviceId;
        init();
    }

    private void init(){

        this.gson = new Gson();
        this.powerDescriptor = new PowerDescriptor();

        setObservable(false);
        setObserveType(CoAP.Type.CON);

        getAttributes().setTitle("power");
        getAttributes().addAttribute("rt", "it.unimore.sw");
        getAttributes().addAttribute("if", "core.a");
        getAttributes().addAttribute("ct", Integer.toString(MediaTypeRegistry.APPLICATION_SENML_JSON));
        getAttributes().addAttribute("ct", Integer.toString(MediaTypeRegistry.TEXT_PLAIN));
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        try{

            //If the request specify the MediaType as JSON or JSON+SenML
            if(exchange.getRequestOptions().getAccept() == MediaTypeRegistry.APPLICATION_SENML_JSON ||
                    exchange.getRequestOptions().getAccept() == MediaTypeRegistry.APPLICATION_JSON){

                Optional<String> senmlPayload = getJsonSenmlResponse();

                if(senmlPayload.isPresent())
                    exchange.respond(CoAP.ResponseCode.CONTENT, senmlPayload.get(), exchange.getRequestOptions().getAccept());
                else
                    exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
            }
            else
                exchange.respond(CoAP.ResponseCode.CONTENT, String.valueOf(this.powerDescriptor.toString()), MediaTypeRegistry.TEXT_PLAIN);
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }

    private Optional<String> getJsonSenmlResponse(){

        try{

            SenMLPack senMLPack = new SenMLPack();

            SenMLRecord senMLRecord = new SenMLRecord();
            senMLRecord.setBn(this.deviceId);
            senMLRecord.setN("value");
            //senMLRecord.setBver(SENSOR_VERSION);
            //senMLRecord.setU(UNIT);
            senMLRecord.setVb(this.powerDescriptor.getValue()); //NOTA: USO Vb PERCHE' E' BOOLEANO
            //senMLRecord.setT(this.temperatureSensorDescriptor.getTimestamp());

            senMLPack.add(senMLRecord);

            return Optional.of(this.gson.toJson(senMLPack));

        }catch (Exception e){
            return Optional.empty();
        }
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try{
            this.powerDescriptor.setValue( exchange.getRequestText());
            exchange.respond(CoAP.ResponseCode.CHANGED);
            changed();
        }catch (Exception e){
            exchange.respond(CoAP.ResponseCode.INTERNAL_SERVER_ERROR);
        }
    }
}
