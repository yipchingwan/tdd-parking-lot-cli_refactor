package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots){
        ParkingLot maxParkingLot = parkingLots.get(0);
        double bestRate = maxParkingLot.getAvailableParkingPosition()/maxParkingLot.getCapacity();
        for (ParkingLot parkingLot:parkingLots) {
            double currentRate = parkingLot.getAvailableParkingPosition()/parkingLot.getCapacity();
            if(currentRate>bestRate && parkingLot.getAvailableParkingPosition()>0){
                maxParkingLot = parkingLot;
            }

        }
        return maxParkingLot;
    }
}
