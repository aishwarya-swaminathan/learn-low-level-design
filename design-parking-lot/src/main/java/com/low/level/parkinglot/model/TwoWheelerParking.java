package com.low.level.parkinglot.model;

import java.util.*;

import static java.util.function.Predicate.not;

public class TwoWheelerParking implements ParkingType {
    public static final String BIKE_PARKING_TAG = "TW";
    private int capacity; // assuming there is a fixed two wheeler capacity in all parking floors

    public TwoWheelerParking(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Set<ParkingSpot> initializeParkingSpots(String floorId, VehicleType parkingCategory) {
        Set<ParkingSpot> parkingSpots = new LinkedHashSet<>();
        for(int i = 1; i <= capacity; i++) {
            ParkingSpot parkingSpot = new ParkingSpot(floorId,
                    floorId.concat(BIKE_PARKING_TAG).concat(String.valueOf(i)), parkingCategory);
            parkingSpot.setOccupied(false);
            parkingSpots.add(parkingSpot);
        }
        return parkingSpots;
    }

    @Override
    public ParkingSpot assignParkingSpot(Set<ParkingSpot> parkingSpots) {
        ParkingSpot parkingSpot = parkingSpots.stream()
                .filter(not(ParkingSpot::isOccupied)).findFirst().orElse(null);
        // remove parking spot, mark it as occupied and then add it back
        parkingSpots.remove(parkingSpot);
        return parkingSpot;
    }

    @Override
    public ParkingSpot releaseParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.setOccupied(false);
        return parkingSpot;
    }


    @Override
    public boolean isSlotAvailable(Set<ParkingSpot> parkingSpots) {
        boolean result = parkingSpots.stream().anyMatch(not(ParkingSpot::isOccupied));
        System.out.println("is slot available: "+result);
        return result;
    }
}
