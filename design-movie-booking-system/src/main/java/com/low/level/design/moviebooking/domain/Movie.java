package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Movie {
    private String movieId;
    private String movieName;
    private String movieLanguage;
    private Duration movieDuration;
}
