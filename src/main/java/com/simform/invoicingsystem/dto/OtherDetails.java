package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherDetails {
    private String csm;
    private List<String> salesman;
    private String contractLink;
    private String source;
    private String channel;
    private boolean activeBillingFlag;
}
