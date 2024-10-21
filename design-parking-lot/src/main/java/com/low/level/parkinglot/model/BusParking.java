package com.low.level.parkinglot.model;

import java.util.*;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

/**
 * The BusParking class implements the Parking interface for handling parking spots for buses.
 * It manages the assignment and availability of parking slots specifically for buses.
 */
public class BusParking implements ParkingType {
    public static final String BUS_PARKING_TAG = "BUS";
    private int capacity;

    public BusParking(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public Set<ParkingSpot> initializeParkingSpots(String floorId, VehicleType parkingCategory) {
        Set<ParkingSpot> parkingSpots = new LinkedHashSet<>();
        for(int i = 1; i <= capacity; i++) {
            ParkingSpot parkingSpot = new ParkingSpot(floorId,
                    floorId.concat(BUS_PARKING_TAG).concat(String.valueOf(i)), parkingCategory);
            parkingSpot.setOccupied(false);
            parkingSpots.add(parkingSpot);
        }
        return parkingSpots;
    }

    @Override
    public ParkingSpot assignParkingSpot(Set<ParkingSpot> parkingSpots) {
        ParkingSpot parkingSpot = parkingSpots.stream()
                .filter(Predicate.not(ParkingSpot::isOccupied)).findFirst().orElse(null);
        parkingSpots.remove(parkingSpot);
        return parkingSpot;
    }

    @Override
    public boolean isSlotAvailable(Set<ParkingSpot> parkingSpots) {
        return parkingSpots.stream().anyMatch(not(ParkingSpot::isOccupied));
    }

    @Override
    public ParkingSpot releaseParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.setOccupied(false);
        return parkingSpot;
    }
}
