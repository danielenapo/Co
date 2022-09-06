package it.unimore.fum.iot.model;

public class SliderDescriptor {
    private int value;

    public SliderDescriptor() {
        this.value = 0;
    }

    public void setValue(String value){
        this.value=Integer.valueOf(value);
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
