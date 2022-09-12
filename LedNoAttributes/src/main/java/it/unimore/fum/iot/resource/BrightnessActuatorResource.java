package it.unimore.fum.iot.resource;

import com.google.gson.Gson;
import it.unimore.fum.iot.model.BrightnessDescriptor;
import it.unimore.fum.iot.model.ColorDescriptor;
import it.unimore.fum.iot.utils.SenMLPack;
import it.unimore.fum.iot.utils.SenMLRecord;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.Optional;

/**
 *
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project coap-playground
 * @created 20/10/2020 - 21:54
 */
public class BrightnessActuatorResource extends CoapResource {

	private static final String OBJECT_TITLE = "BrightnessActuator";
	private Gson gson;
	private BrightnessDescriptor modeDescriptor;
	private String deviceId = null;
	private static final Number SENSOR_VERSION = 0.1;

	public BrightnessActuatorResource(String name, String deviceId) {
		super(name);
		this.deviceId = deviceId;
		init();
	}

	private void init(){

        this.gson = new Gson();
		this.modeDescriptor = new BrightnessDescriptor();

		setObservable(true);
		setObserveType(CoAP.Type.CON);

		getAttributes().setTitle("Brightness");
		getAttributes().addAttribute("rt", "it.unimore.brt");
		getAttributes().addAttribute("if", "core.a");
		getAttributes().addAttribute("ct", Integer.toString(MediaTypeRegistry.APPLICATION_SENML_JSON));
		getAttributes().addAttribute("ct", Integer.toString(MediaTypeRegistry.TEXT_PLAIN));
	}

	private Optional<String> getJsonSenmlResponse(){

		try{

			SenMLPack senMLPack = new SenMLPack();

			SenMLRecord senMLRecord = new SenMLRecord();
			senMLRecord.setBn(this.deviceId);
			senMLRecord.setBver(SENSOR_VERSION);
			senMLRecord.setT(System.currentTimeMillis());

			senMLRecord.setN("brightness");
			senMLRecord.setVs(this.modeDescriptor.getMode());


			senMLPack.add(senMLRecord);


			return Optional.of(this.gson.toJson(senMLPack));

		}catch (Exception e){
			return Optional.empty();
		}
	}

	@Override
	public void handleGET(CoapExchange exchange) {
		try{

			//If the request specify the MediaType as JSON or JSON+SenML
			if(exchange.getRequestOptions().getAccept() == MediaTypeRegistry.APPLICATION_SENML_JSON ||
					exchange.getRequestOptions().getAccept() == MediaTypeRegistry.APPLICATION_JSON){

				Optional<String> senmlPayload = getJsonSenmlResponse();

				if(senmlPayload.isPresent())
					exchange.respond(ResponseCode.CONTENT, senmlPayload.get(), exchange.getRequestOptions().getAccept());
				else
					exchange.respond(ResponseCode.INTERNAL_SERVER_ERROR);
			}
			else
				exchange.respond(ResponseCode.CONTENT, String.valueOf(this.modeDescriptor.getMode()), MediaTypeRegistry.TEXT_PLAIN);

		}catch (Exception e){
			exchange.respond(ResponseCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		try{
			this.modeDescriptor.changeMode();
			exchange.respond(ResponseCode.CHANGED);
			changed();
		}catch (Exception e){
			exchange.respond(ResponseCode.INTERNAL_SERVER_ERROR);
		}
	}


}
