package com.oocl.cultivation;

import sun.security.krb5.internal.Ticket;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    //public int getAvailableParkingPosition() {
    //        return car.size() - capacity;
    //    }
    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public void addCarToLot(ParkingTicket ticket, Car car){
        cars.put(ticket, car);
    }

    public Car removeCarFromLot (ParkingTicket ticket){
        return cars.remove(ticket);
    }

    public int getCapacity(){
        return this.capacity;
    }

    public Map<ParkingTicket, Car> getCarsList(){
        return this.cars;
    }
}
