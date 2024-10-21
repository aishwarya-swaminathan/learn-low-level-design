package com.low.level.parkinglot.model;

import java.util.Objects;

public class ParkingSpot {
    private String spotNumber;
    private VehicleType parkingCategory;
    private boolean isOccupied;
    private String floorId;
    private Vehicle vehicle;

    public ParkingSpot(String floorId, String spotNumber, VehicleType parkingCategory) {
        this.floorId = floorId;
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.vehicle = null;
        this.parkingCategory = parkingCategory;
    }

    public String getFloorId() {
        return floorId;
    }

    public String getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(String spotNumber) {
        this.spotNumber = spotNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public VehicleType getParkingCategory() {
        return parkingCategory;
    }

    public void setParkingCategory(VehicleType parkingCategory) {
        this.parkingCategory = parkingCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return isOccupied == that.isOccupied && Objects.equals(spotNumber, that.spotNumber) && parkingCategory == that.parkingCategory && Objects.equals(floorId, that.floorId) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotNumber, parkingCategory, isOccupied, floorId, vehicle);
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotNumber='" + spotNumber + '\'' +
                ", parkingCategory=" + parkingCategory +
                ", isOccupied=" + isOccupied +
                ", floorId='" + floorId + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }
}
