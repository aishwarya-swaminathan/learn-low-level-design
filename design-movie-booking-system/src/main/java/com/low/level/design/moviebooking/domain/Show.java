package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Show {
    private ShowType showType;
    private Movie movie;
    private LocalDateTime startTime;
    private Screen screen;
}
