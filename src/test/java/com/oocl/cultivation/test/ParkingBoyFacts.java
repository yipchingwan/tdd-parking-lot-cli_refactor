package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket wrongTicket = new ParkingTicket();

        parkingBoy.fetch(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_park_a_parked_car(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        ParkingTicket ticket1 = parkingBoy.park(car);

        assertNull(ticket1);
    }

    @Test
    void should_get_message_if_paking_a_parked_car(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        ParkingTicket ticket1 = parkingBoy.park(car);
        assertEquals("The car is already been parked.", parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_park_car_if_there_is_no_car_been_given(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket = parkingBoy.park(null);

        assertNull(ticket);

    }
    @Test
    void should_get_message_if_there_is_no_car_been_given(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        ParkingTicket ticket = parkingBoy.park(null);

        assertEquals("Please give me a car to park.", parkingBoy.getLastErrorMessage());

    }

    @Test
    void should_park_cars_to_multiple_parking_lots(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();

        parkingBoy.park(car);

        assertSame(parkingLot1, parkingBoy.getAvailableParkingLot(parkingLots));

    }

    @Test
    void should_park_cars_using_big_capacity_parkingLot(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingLot parkingLot1 = new ParkingLot(4);
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        Car car1 = new Car();

        ParkingLot result = smartParkingBoy.getAvailableParkingLot(parkingLots);

        assertSame(parkingLot1, result);

    }

    // (1, 1) --> (1/2, 1) , (1/2, 1/3)
    @Test
    void should_pickup_the_largest_available_position_rate_parkingLot(){
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingLot parkingLot1 = new ParkingLot(3);
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingLot1);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        Car car1 = new Car();

        superSmartParkingBoy.park(car);
        superSmartParkingBoy.park(car1);
        ParkingLot result = superSmartParkingBoy.getAvailableParkingLot(parkingLots);

        assertSame(parkingLot, result);
    }

    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back_for_parkingboy_from_ManagerParkingBoysList() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);
        Car car = new Car();

        ParkingTicket ticket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy, car);
        Car fetched = parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy, ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back_for_parkingboy_from_ManagerParkingBoysList() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy, firstCar);
        ParkingTicket secondTicket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy, secondCar);

        Car fetchedByFirstTicket = parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy, firstTicket);
        Car fetchedBySecondTicket = parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy, secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back_for_parkingboy_NOT_from_ManagerParkingBoysList() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingBoy parkingBoy1 = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);
        Car car = new Car();

        ParkingTicket ticket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy, car);
        Car fetched = parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy1, ticket);

        assertNull(ticket);
        assertNull(fetched);
    }

    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back_from_manager() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        Car car = new Car();

        ParkingTicket ticket = parkingLotServiceManager.park(car);
        Car fetched = parkingLotServiceManager.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back_from_manager() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLot);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingLotServiceManager.park(firstCar);
        ParkingTicket secondTicket = parkingLotServiceManager.park(secondCar);

        Car fetchedByFirstTicket = parkingLotServiceManager.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingLotServiceManager.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong_from_manager() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);

        ParkingTicket wrongTicket = new ParkingTicket();

        parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy,wrongTicket);
        String message = parkingLotServiceManager.getLastErrorMessage();

        assertEquals("<Manager>: Unrecognized parking ticket.", message);
    }

    @Test
    void should_query_message_once_ticket_is_not_provided_from_manager() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);

        parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy,null);

        assertEquals(
                "<Manager>: Please provide your parking ticket.",
                parkingLotServiceManager.getLastErrorMessage());
    }

    @Test
    void should_query_error_message_for_used_ticket_from_manager() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);
        Car car = new Car();

        ParkingTicket ticket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,car);
        parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy,ticket);
        parkingLotServiceManager.askParkingBoyFetchCar(parkingBoy,ticket);

        assertEquals(
                "<Manager>: Unrecognized parking ticket.",
                parkingLotServiceManager.getLastErrorMessage()
        );
    }

    @Test
    void should_get_message_if_there_is_not_enough_position_from_manager() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);;

        parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,new Car());
        parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,new Car());

        assertEquals("<Manager>: The parking lot is full.", parkingLotServiceManager.getLastErrorMessage());
    }

    @Test
    void should_get_message_if_paking_a_parked_car_from_manager(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);
        Car car = new Car();

        ParkingTicket ticket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,car);
        ParkingTicket ticket1 = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,car);
        assertEquals("<Manager>: The car is already been parked.", parkingLotServiceManager.getLastErrorMessage());
    }

    @Test
    void should_get_message_if_there_is_no_car_been_given_from_manager(){
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        List<ParkingBoy>  parkingBoyList = new ArrayList<ParkingBoy>();
        parkingBoyList.add(parkingBoy);
        List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
        parkingLotList.add(parkingLot);
        ParkingLotServiceManager parkingLotServiceManager = new ParkingLotServiceManager(parkingLotList, parkingBoyList);

        ParkingTicket ticket = parkingLotServiceManager.askParkingBoyParkCar(parkingBoy,null);

        assertEquals("<Manager>: Please give me a car to park.", parkingLotServiceManager.getLastErrorMessage());

    }

}
