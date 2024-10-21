package com.low.level.parkinglot.model;

public class ParkingStrategy {
    private static ParkingStrategy instance = null;

    private TwoWheelerParking twoWheelerParking;
    private CarParking carParking;
    private BusParking busParking;
    private TruckParking truckParking;

    private ParkingStrategy(int capacity) {
        this.twoWheelerParking = new TwoWheelerParking(capacity);
        this.carParking = new CarParking(capacity);
        this.busParking = new BusParking(capacity);
        this.truckParking = new TruckParking(capacity);
    }

    public static ParkingStrategy getInstance(int capacity) {
        if(instance == null) {
            instance = new ParkingStrategy(capacity);
        }
        return instance;
    }

    public ParkingType getParkingStrategy(VehicleType vehicleType) {
        return selectParkingBasedOnType(vehicleType);
    }

    private ParkingType selectParkingBasedOnType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case TWO_WHEELER -> twoWheelerParking;
            case CAR -> carParking;
            case BUS -> busParking;
            case TRUCK -> truckParking;
            default -> throw new IllegalArgumentException("Unknown VehicleType: " + vehicleType);
        };
    }
}
