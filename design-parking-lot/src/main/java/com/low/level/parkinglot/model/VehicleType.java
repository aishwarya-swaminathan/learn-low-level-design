package com.low.level.parkinglot.model;

public enum VehicleType {
    TWO_WHEELER(1), CAR(2), BUS(3), TRUCK(4);
    private final int categoryId;

    VehicleType(int categoryId) {
        this.categoryId = categoryId;
    }

    public static int getCategoryId(VehicleType vehicleType) {
        return vehicleType.categoryId;
    }

    public static VehicleType getVehicleByCategoryId(int categoryId) {
        for (VehicleType vehicleType : VehicleType.values()) {
            if (vehicleType.categoryId == categoryId) {
                return vehicleType;
            }
        }
        return null;
    }
}
