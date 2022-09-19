package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDetails {

    private String techStack;

    Collection<RateDetail> rateDetailCollection;

}
