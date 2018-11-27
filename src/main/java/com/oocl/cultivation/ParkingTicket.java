package com.oocl.cultivation;

public class ParkingTicket {
    private Car car;
    private boolean isTicketVaild;

    public ParkingTicket() {
        this.car = null;
        this.isTicketVaild = false;
    }

    public ParkingTicket(Car car) {
        this.car = car;
        this.isTicketVaild = true;
    }

    public Car getCar(){
        return this.car;
    }

    public boolean checkIfTicketIsVaild(){
        return this.isTicketVaild;
    }

    public void setTicketVaild(boolean status){
        this.isTicketVaild = status;
    }




}
