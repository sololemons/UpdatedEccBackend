package com.AuthenticationDemo.Securtity.ECCOMERCE.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentRequest {

    private String phoneNumber;

    private String amount;
}
