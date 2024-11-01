package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Screen {
    private String screenId;
    private String screenName;
    private Theater theater;
}
