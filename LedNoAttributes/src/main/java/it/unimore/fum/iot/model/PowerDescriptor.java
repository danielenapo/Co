package it.unimore.fum.iot.model;

public class PowerDescriptor {
    private boolean value; //true=on, falsse=off

    public PowerDescriptor() {
        this.value = true;
    }

    public void setValue(String value){
        if(this.value==true)
            this.value=false;
        else
            this.value=true;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        return ""+value;
    }
}
