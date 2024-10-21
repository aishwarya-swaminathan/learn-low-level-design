package com.low.level.parkinglot.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private Double amount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Ticket(Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = String.valueOf(new Random().nextInt(10000));
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.startTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(vehicle, ticket.vehicle) && Objects.equals(parkingSpot, ticket.parkingSpot) && Objects.equals(amount, ticket.amount) && Objects.equals(startTime, ticket.startTime) && Objects.equals(endTime, ticket.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, vehicle, parkingSpot, amount, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", parkingSpot=" + parkingSpot +
                ", amount=" + amount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
