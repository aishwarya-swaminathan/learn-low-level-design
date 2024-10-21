package com.low.level.parkinglot.model;

import com.low.level.parkinglot.exception.NoSlotAvailableException;

import java.util.*;


/**
 * Represents a parking lot with multiple levels and various parking spots for different types of vehicles.
 *
 * This class allows for adding new parking levels, assigning parking spots to vehicles based on their type,
 * and managing the availability of parking slots using a specified parking strategy.
 */
public class ParkingLot {
    private String name;
    private int levels;
    private Address address;
    private List<ParkingLevel> parkingLevels;
    private final ParkingStrategy parkingStrategy;
    private int capacity;
    private Map<ParkingLevel, Map<ParkingType, Set<ParkingSpot>>> parkingSpotsByLevel;

    public ParkingLot(String name, Address address, int noOfLevels, int capacity) {
        this.name = name;
        this.address = address;
        this.levels = noOfLevels;
        this.capacity = capacity;
        this.parkingStrategy = ParkingStrategy.getInstance(capacity);
    }

    public Map<ParkingLevel, Map<ParkingType, Set<ParkingSpot>>> initializeParkingLot() {
        parkingSpotsByLevel = new TreeMap<>();
        for(int i = 1; i <=levels; i++) {
            String floorId = i+"F";
            ParkingLevel parkingLevel = new ParkingLevel(floorId, capacity);
            Map<ParkingType, Set<ParkingSpot>> parkingLevelCache = parkingLevel.initializeParkingLevel();
            parkingSpotsByLevel.put(parkingLevel, parkingLevelCache);
        }
        return parkingSpotsByLevel;
    }

    public void addParkingLevel(String floorId) {
        ParkingLevel parkingLevel = new ParkingLevel(floorId, capacity);
        Map<ParkingType, Set<ParkingSpot>> parkingLevelCache = parkingLevel.initializeParkingLevel();
        parkingSpotsByLevel.put(parkingLevel, parkingLevelCache);

    }

    public ParkingSpot assignParkingSpot(Vehicle vehicle) {
        int vehicleCategory = VehicleType.getCategoryId(vehicle.getVehicleType());
        ParkingSpot parkingSpot = getNextAvailableSpot(vehicle);

        while(parkingSpot == null && vehicleCategory < 4) {
            System.out.println("No slot available for the vehicle type - " + vehicle.getVehicleType() + " in all parking floors");
            System.out.println("Do you want to get a higher category vehicle spot assigned? (y/n)");
            Scanner sc = new Scanner(System.in);
            String response = sc.next();

            if("y".equals(response)) {
                vehicleCategory = vehicleCategory + 1;
                parkingSpot = getNextAvailableSpot(vehicle);
            } else {
                System.out.println("No parking spot for your vehicle type available right now. " +
                        "Please visit after some time");
                break;
            }
        }

        if(parkingSpot == null && vehicleCategory > 4) {
            throw new NoSlotAvailableException("All slots filled");
        }
       return parkingSpot;
    }

    private ParkingSpot getNextAvailableSpot(Vehicle vehicle) {
        ParkingSpot parkingSpot = null;
        ParkingType parking = parkingStrategy.getParkingStrategy(vehicle.getVehicleType());

        for(ParkingLevel parkingLevel : parkingSpotsByLevel.keySet()) {
            if(parkingLevel.isSpotAvailable(vehicle.getVehicleType())) {
                parkingSpot = parkingLevel.assignParkingSpot(parking, vehicle);
                break;
            }
        }
        return parkingSpot;
    }

    public ParkingSpot releaseParkingSpot(ParkingSpot parkingSpot) {
        ParkingSpot releasedParkingSpot = null;
        Set<ParkingLevel> parkingLevels = parkingSpotsByLevel.keySet();
        ParkingLevel parkingLevel = parkingLevels.stream()
                .filter(level -> level.getFloorId().equals(parkingSpot.getFloorId()))
                .findFirst()
                .orElse(null);

        VehicleType vehicleType = parkingSpot.getVehicle().getVehicleType();

        if(parkingLevel!=null){
            releasedParkingSpot = parkingLevel.releaseParkingSpot(parkingSpot);
            ParkingType parkingType = parkingStrategy.getParkingStrategy(vehicleType);
            Map<ParkingType, Set<ParkingSpot>> parkingSpotsForLevel = parkingSpotsByLevel.get(parkingLevel);
            parkingSpotsForLevel.put(parkingType, parkingLevel.getParkingSpots(parkingType));
            parkingSpotsByLevel.put(parkingLevel, parkingSpotsForLevel);
        }
        return releasedParkingSpot;
    }
}
