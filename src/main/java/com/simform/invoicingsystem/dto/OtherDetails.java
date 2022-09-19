package com.simform.invoicingsystem.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
