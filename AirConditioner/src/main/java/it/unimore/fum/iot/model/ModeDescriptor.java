package it.unimore.fum.iot.model;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 12:30
 */
public class ModeDescriptor {

    private String[] modes={"Cool", "Heat", "Dry", "Fan"};
    private int currentMode;

    public ModeDescriptor() {
        this.currentMode=0;
    }

    public String getMode(){
        return modes[currentMode];
    }

    public void changeMode(){
        if(currentMode==3) {
            currentMode = 0;
            return;
        }
        currentMode++;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ModeDescriptor{");
        sb.append("mode=").append(modes[currentMode]);
        sb.append('}');
        return sb.toString();
    }
}
