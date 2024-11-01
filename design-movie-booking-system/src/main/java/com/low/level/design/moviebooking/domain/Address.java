package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Address {
    private String addressLine;
    private String city;
    private String state;
    private String zipCode;
}
