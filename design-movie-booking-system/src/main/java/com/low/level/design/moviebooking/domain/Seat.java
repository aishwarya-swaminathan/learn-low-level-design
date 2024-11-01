package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Seat {
    private String seatId;
    private SeatStatus seatStatus;
    private Show show;
    private Theater theater;
}
