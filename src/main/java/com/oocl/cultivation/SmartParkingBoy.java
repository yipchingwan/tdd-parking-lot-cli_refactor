package com.oocl.cultivation;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot){
        super(parkingLot);
    }
    public SmartParkingBoy(List<ParkingLot> parkingLots){
        super(parkingLots);
    }

   @Override
    public ParkingLot getAvailableParkingLot(List<ParkingLot> parkingLots){
        ParkingLot maxParkingLot = parkingLots.get(0);
        for (ParkingLot parkingLot:parkingLots) {
            if(parkingLot.getAvailableParkingPosition()>maxParkingLot.getAvailableParkingPosition() && parkingLot.getAvailableParkingPosition()>0){
                maxParkingLot = parkingLot;
            }

        }
        return maxParkingLot;
    }
}
