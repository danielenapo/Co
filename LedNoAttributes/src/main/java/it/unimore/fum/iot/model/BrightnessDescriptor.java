package it.unimore.fum.iot.model;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 12:30
 */
public class BrightnessDescriptor {

    private String[] modes={"25%", "50%", "75%", "100%"};
    private int currentMode;

    public BrightnessDescriptor() {
        this.currentMode=0;
    }

    public String getMode(){
        return modes[currentMode];
    }

    public void changeMode(){
        if(currentMode==modes.length) {
            currentMode = 0;
            return;
        }
        currentMode++;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BrightnessDescriptor{");
        sb.append("brightess=").append(modes[currentMode]);
        sb.append('}');
        return sb.toString();
    }
}
