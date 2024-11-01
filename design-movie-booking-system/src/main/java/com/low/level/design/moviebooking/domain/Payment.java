package com.low.level.design.moviebooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Payment {
    private String transactionReferenceNumber;
    private User user;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
}
