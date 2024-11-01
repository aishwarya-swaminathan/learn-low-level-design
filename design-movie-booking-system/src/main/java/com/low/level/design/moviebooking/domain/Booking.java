package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Booking {
    private String bookingId;
    private Movie movie;
    private User user;
    private LocalDateTime bookingDate;
    private Show show;
    private List<Seat> bookedSeats;
    private Payment payment;
    private BookingStatus bookingStatus;
}
