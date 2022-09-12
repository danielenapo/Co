package it.unimore.fum.iot.model;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project java-coap-laboratory
 * @created 12/11/2021 - 12:30
 */
public class ColorDescriptor {

    private String[] colors={"Red", "Green", "Blue", "Purple", "White"};
    private int currentColor;

    public ColorDescriptor() {
        this.currentColor=0;
    }

    public String getMode(){
        return colors[currentColor];
    }

    public void changeMode(){
        if(currentColor==colors.length) {
            currentColor = 0;
            return;
        }
        currentColor++;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ColorDescriptor{");
        sb.append("color=").append(colors[currentColor]);
        sb.append('}');
        return sb.toString();
    }
}
