package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialRateDetails {
    private long userId;
    private int price;
}
