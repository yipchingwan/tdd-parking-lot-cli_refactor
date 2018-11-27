package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    protected ParkingLot parkingLot;
    protected final List<ParkingLot> parkingLots;
    protected String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        this.parkingLots = new ArrayList<ParkingLot>();
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots){
        this.parkingLots = parkingLots;
        this.parkingLot = this.getAvailableParkingLot(parkingLots);

    }


    public ParkingTicket park(Car car) {
        // TODO: Please implement the method
        setLastErrorMessage(null);
        if(car==null){
            this.setLastErrorMessage("Please give me a car to park.");
            return null;
        }

        if(this.getAvailableParkingLot(parkingLots)!=null && this.getAvailableParkingLot(parkingLots).getAvailableParkingPosition()>0){
            this.parkingLot = this.getAvailableParkingLot(parkingLots);
            if(car.getParkedStatus()){
                ParkingTicket ticket = new ParkingTicket(car);
                car.setParkedStatus(false);
                this.parkingLot.addCarToLot(ticket, car);
                return ticket;
            }
            else{
                this.setLastErrorMessage("The car is already been parked.");
                return null;
            }
        }
        else{
            this.setLastErrorMessage("The parking lot is full.");
            return null;
        }
    }
    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
        if(ticket==null){
            this.setLastErrorMessage("Please provide your parking ticket.");
            return null;
        }
        else{
            if(ticket.checkIfTicketIsVaild()){
                Car car = this.findParkingLotFromTicket(ticket).removeCarFromLot(ticket);
                ticket.setTicketVaild(false);
                return car;
            }
            else{
                this.setLastErrorMessage("Unrecognized parking ticket.");
                return null;
            }
        }
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String message) {
        this.lastErrorMessage = message;
    }

    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots){
        for (ParkingLot p: parkingLots) {
            if(p.getAvailableParkingPosition()>0){
                return p;
            }
        }
        return null;

    }

    public ParkingLot findParkingLotFromTicket(ParkingTicket parkingTicket){
        for (ParkingLot parkingLot: this.parkingLots) {
            if(parkingLot.getCarsList().containsKey(parkingTicket)){
                return parkingLot;
            }
        }
        return null;
    }
}
