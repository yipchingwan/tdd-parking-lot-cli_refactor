package com.oocl.cultivation;

public class Car {
    private boolean canPark;

    public Car() {
        this.canPark = true;
    }

    public void setParkedStatus(boolean status){
        this.canPark = status;
    }

    public boolean getParkedStatus(){
        return this.canPark;
    }
}
