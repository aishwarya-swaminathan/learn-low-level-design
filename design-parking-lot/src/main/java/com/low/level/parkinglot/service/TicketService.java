package com.low.level.parkinglot.service;

import com.low.level.parkinglot.model.ParkingSpot;
import com.low.level.parkinglot.model.Ticket;
import com.low.level.parkinglot.model.Vehicle;
import com.low.level.parkinglot.model.VehicleType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.util.Map.entry;

public class TicketService {
    private static final Map<VehicleType, Double> chargesPerHour = Map.ofEntries(
            entry(VehicleType.TWO_WHEELER, 20.0),
            entry(VehicleType.CAR, 40.0),
            entry(VehicleType.BUS, 50.0),
            entry(VehicleType.TRUCK, 60.0)
    );

    public Ticket createTicket(Vehicle vehicle, ParkingSpot parkingSpot) {
        Ticket ticket = new Ticket(vehicle, parkingSpot);
        System.out.println("Ticket id "+ticket.getTicketId()+
                " generated for vehicle "+vehicle.getVehicleNumber());
        return ticket;
    }

    public Double computeCharges(Ticket ticket) {
        VehicleType parkingCategory = ticket.getParkingSpot().getParkingCategory();
        Duration duration = Duration.between(ticket.getStartTime(), LocalDateTime.now());
        long hours = duration.toSeconds(); // consider seconds as hours
        return chargesPerHour.get(parkingCategory) * hours;
    }
}
