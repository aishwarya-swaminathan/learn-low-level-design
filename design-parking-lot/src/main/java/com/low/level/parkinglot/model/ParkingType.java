package com.low.level.parkinglot.model;

import java.util.Set;

public interface ParkingType {

    Set<ParkingSpot> initializeParkingSpots(String floorId, VehicleType parkingCategory);

    ParkingSpot assignParkingSpot(Set<ParkingSpot> parkingSpots);

    ParkingSpot releaseParkingSpot(ParkingSpot parkingSpot);

    boolean isSlotAvailable(Set<ParkingSpot> parkingSpots);
}
