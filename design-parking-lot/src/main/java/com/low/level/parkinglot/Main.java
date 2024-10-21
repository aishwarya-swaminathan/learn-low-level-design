package com.low.level.parkinglot;

import com.low.level.parkinglot.model.*;
import com.low.level.parkinglot.service.TicketService;

import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to XYZ Parking Lot!");
        System.out.println("Setting up Parking lot");

        Address address = new Address("1,Brick St", "Chennai", "TamilNadu", "600044", "India");
        ParkingLot parkingLot = new ParkingLot("XYZ Parking lot", address, 5, 10);

       Map<ParkingLevel, Map<ParkingType, Set<ParkingSpot>>> parkingLotData = parkingLot.initializeParkingLot();

       // a new vehicle arrives
        Vehicle twoWheeler = new Vehicle("KA34-D1234", VehicleType.TWO_WHEELER);
        System.out.println(twoWheeler.getVehicleType()+" with number "+twoWheeler.getVehicleNumber()+" arrived at the parking lot");
        Vehicle car = new Vehicle("KA34-D1236", VehicleType.CAR);
        System.out.println(car.getVehicleType()+" with number "+car.getVehicleNumber()+" arrived at the parking lot");
        Vehicle bus = new Vehicle("KA34-D1237", VehicleType.BUS);
        System.out.println(bus.getVehicleType()+" with number "+bus.getVehicleNumber()+" arrived at the parking lot");
        Vehicle truck = new Vehicle("KA34-D1238", VehicleType.TRUCK);
        System.out.println(truck.getVehicleType()+" with number "+truck.getVehicleNumber()+" arrived at the parking lot");

        ParkingSpot twoWheelerSpot = parkingLot.assignParkingSpot(twoWheeler);
        System.out.println("SpotNumber: "+twoWheelerSpot.getSpotNumber()+
                ", Floor no: "+twoWheelerSpot.getFloorId()+
                ", Parking category: "+twoWheelerSpot.getParkingCategory()+
                " assigned for vehicle with vehicle number: "+twoWheelerSpot.getVehicle().getVehicleNumber());

        ParkingSpot carSpot = parkingLot.assignParkingSpot(car);
        System.out.println("SpotNumber: "+carSpot.getSpotNumber()+
                ", Floor no: "+carSpot.getFloorId()+
                ", Parking category: "+carSpot.getParkingCategory()+
                " assigned for vehicle with vehicle number: "+carSpot.getVehicle().getVehicleNumber());

        ParkingSpot busSpot = parkingLot.assignParkingSpot(bus);
        System.out.println("SpotNumber: "+busSpot.getSpotNumber()+
                ", Floor no: "+busSpot.getFloorId()+
                ", Parking category: "+busSpot.getParkingCategory()+
                " assigned for vehicle with vehicle number: "+busSpot.getVehicle().getVehicleNumber());

        ParkingSpot truckSpot = parkingLot.assignParkingSpot(truck);
        System.out.println("SpotNumber: "+truckSpot.getSpotNumber()+
                ", Floor no: "+truckSpot.getFloorId()+
                ", Parking category: "+truckSpot.getParkingCategory()+
                " assigned for vehicle with vehicle number: "+truckSpot.getVehicle().getVehicleNumber());

        // generate tickets
        TicketService ticketService = new TicketService();
        Ticket twTicket = ticketService.createTicket(twoWheeler, twoWheelerSpot);
        Ticket carTicket = ticketService.createTicket(car, carSpot);
        Ticket busTicket = ticketService.createTicket(bus, busSpot);
        Ticket truckTicket = ticketService.createTicket(truck, truckSpot);

        Thread.sleep(5000);
       // the vehicle departs
        parkingLot.releaseParkingSpot(twoWheelerSpot);
        parkingLot.releaseParkingSpot(carSpot);
        parkingLot.releaseParkingSpot(busSpot);
        parkingLot.releaseParkingSpot(truckSpot);

        // compute charges
        System.out.println("Total charges for " + twTicket.getVehicle().getVehicleType() +
                " with vehicle number " + twTicket.getVehicle().getVehicleNumber() + " is : " +
                ticketService.computeCharges(twTicket));
        System.out.println("Total charges for " + carTicket.getVehicle().getVehicleType() +
                " with vehicle number " + carTicket.getVehicle().getVehicleNumber() + " is : " +
                ticketService.computeCharges(carTicket));
        System.out.println("Total charges for " + busTicket.getVehicle().getVehicleType() +
                " with vehicle number " + busTicket.getVehicle().getVehicleNumber() + " is : " +
                ticketService.computeCharges(busTicket));
        System.out.println("Total charges for " + truckTicket.getVehicle().getVehicleType() +
                " with vehicle number " + truckTicket.getVehicle().getVehicleNumber() + " is : " +
                ticketService.computeCharges(truckTicket));

        System.out.println("Thank you, please visit us again!!!");


    }
}