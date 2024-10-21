package com.low.level.parkinglot.model;

import java.util.*;


/**
 * The ParkingLevel class models a specific level in a parking structure.
 * It manages the parking spots available for different types of vehicles on this level.
 */
public class ParkingLevel implements Comparable<ParkingLevel> {
    private String floorId;
    private int capacity;
    private int availableSpots; // not asked in the problem
    private final ParkingStrategy parkingStrategy = ParkingStrategy.getInstance(capacity);
    private Map<ParkingType, Set<ParkingSpot>> parkingSpotsByVehicleType;

    public ParkingLevel(String floorId, int capacity) {
        this.floorId = floorId;
        this.capacity = capacity;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

   public boolean isSpotAvailable(VehicleType vehicleType) {
       ParkingType parking = parkingStrategy.getParkingStrategy(vehicleType);
       Set<ParkingSpot> parkingSpots = parkingSpotsByVehicleType.get(parking);
//       System.out.println("vehicle type: "+vehicleType.name()+", parking spots available in level: "+parkingSpots);
       return parking.isSlotAvailable(parkingSpots);
   }

   public ParkingSpot assignParkingSpot(ParkingType parking, Vehicle vehicle) {
       Set<ParkingSpot> parkingSpots = parkingSpotsByVehicleType.get(parking);
       ParkingSpot parkingSpot = parking.assignParkingSpot(parkingSpots);
       parkingSpot.setVehicle(vehicle);
       parkingSpots.add(parkingSpot);
       parkingSpotsByVehicleType.put(parking, parkingSpots);
       return parkingSpot;
   }

    public ParkingSpot releaseParkingSpot(ParkingSpot parkingSpot) {
        VehicleType vehicleType = parkingSpot.getVehicle().getVehicleType();
        ParkingType parking = parkingStrategy.getParkingStrategy(vehicleType);
        Set<ParkingSpot> parkingSpots = parkingSpotsByVehicleType.get(parking);
        parkingSpots.remove(parkingSpot);
        ParkingSpot releasedParkingSpot = parking.releaseParkingSpot(parkingSpot);
        releasedParkingSpot.setVehicle(null);
        parkingSpots.add(releasedParkingSpot);
        parkingSpotsByVehicleType.put(parking, parkingSpots);
        return parkingSpot;
    }

    public Set<ParkingSpot> getParkingSpots(ParkingType parkingType) {
        return parkingSpotsByVehicleType.get(parkingType);
    }

   public void addParkingSpot(ParkingType parking, ParkingSpot parkingSpot) {
        parkingSpotsByVehicleType.get(parking).add(parkingSpot);
   }

    public Map<ParkingType, Set<ParkingSpot>> initializeParkingLevel() {
        this.parkingSpotsByVehicleType = new HashMap<>();
        ParkingType twoWheelerParking = parkingStrategy.getParkingStrategy(VehicleType.TWO_WHEELER);
        ParkingType carParking = parkingStrategy.getParkingStrategy(VehicleType.CAR);
        ParkingType busParking = parkingStrategy.getParkingStrategy(VehicleType.BUS);
        ParkingType truckParking = parkingStrategy.getParkingStrategy(VehicleType.TRUCK);
        parkingSpotsByVehicleType.put(twoWheelerParking, twoWheelerParking.initializeParkingSpots(floorId, VehicleType.TWO_WHEELER));
        parkingSpotsByVehicleType.put(carParking, carParking.initializeParkingSpots(floorId, VehicleType.CAR));
        parkingSpotsByVehicleType.put(busParking, busParking.initializeParkingSpots(floorId, VehicleType.BUS));
        parkingSpotsByVehicleType.put(truckParking, truckParking.initializeParkingSpots(floorId, VehicleType.TRUCK));
        return parkingSpotsByVehicleType;
    }

    @Override
    public int compareTo(ParkingLevel parkingLevel) {
        return this.floorId.compareTo(parkingLevel.getFloorId());
    }
}
