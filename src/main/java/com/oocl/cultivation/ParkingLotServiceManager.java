package com.oocl.cultivation;

import sun.security.krb5.internal.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager extends ParkingBoy {
    private final List<ParkingBoy> parkingBoysList;

    public ParkingLotServiceManager(ParkingLot parkingLot){
        super(parkingLot);
        this.parkingBoysList = null;
    }

    public ParkingLotServiceManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoysList) {
        super(parkingLots);
        this.parkingBoysList = parkingBoysList;
    }

    public List<ParkingBoy> getParkingBoysList(){
        return this.parkingBoysList;
    }

    public ParkingTicket askParkingBoyParkCar(ParkingBoy parkingBoy, Car car){
        if(this.parkingBoysList.contains(parkingBoy)){
            ParkingTicket parkingTicket = parkingBoy.park(car);
            if(parkingBoy.getLastErrorMessage()!=null){
                this.setLastErrorMessage("<Manager>: "+parkingBoy.getLastErrorMessage());
            }
            return parkingTicket;
        }
        else{
            return null;
        }
    }
    public Car askParkingBoyFetchCar(ParkingBoy parkingBoy, ParkingTicket ticket){
        if(this.parkingBoysList.contains(parkingBoy)){
            Car fetchedCar = parkingBoy.fetch(ticket);
            if(parkingBoy.getLastErrorMessage()!=null){
                this.setLastErrorMessage("<Manager>: "+parkingBoy.getLastErrorMessage());
            }
            return fetchedCar;
        }
        else{
            return null;
        }
    }

    public ParkingTicket park(Car car, ParkingLot parkingLot) {
        if(this.parkingLots.contains(parkingLot)){
            return super.park(car);
        }
        else{
            return null;
        }
    }

    public Car fetch(ParkingTicket ticket, ParkingLot parkingLot) {
        if(this.parkingLots.contains(parkingLot)){
            return super.fetch(ticket);
        }
        else{
            return null;
        }

    }
}
