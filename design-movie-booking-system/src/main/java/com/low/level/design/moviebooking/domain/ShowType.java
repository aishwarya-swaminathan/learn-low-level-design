package com.low.level.design.moviebooking.domain;

import lombok.Getter;

@Getter
public enum ShowType {
    MORNING(1),
    MATINEE(2),
    EVENING(3),
    NIGHT(4);

    private final int showNumber;

    ShowType(int showNumber) {
        this.showNumber = showNumber;
    }
}
