package com.low.level.design.moviebooking.service;

import com.low.level.design.moviebooking.domain.User;
import com.low.level.design.moviebooking.domain.UserPreferences;

import java.util.Map;

public class UserController {
    private Map<User, UserPreferences> userAndPreferences;

    public void registerUser(User user, UserPreferences userPreferences) {
        userAndPreferences.put(user, userPreferences);
        System.out.println("User registration successful");
    }

    // implement login functionality later if needed

    public void updateUserPreferences(User user, UserPreferences userPreferences) {
        if(userAndPreferences.containsKey(user)) {
            userAndPreferences.put(user, userPreferences);
        } else {
            System.out.println("User is not registered. Please register");
        }
    }

    // get booking history for the user
}
