package com.low.level.design.moviebooking.domain;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserPreferences {
    private String preferredCity;
    private List<String> preferredLanguages;
}
