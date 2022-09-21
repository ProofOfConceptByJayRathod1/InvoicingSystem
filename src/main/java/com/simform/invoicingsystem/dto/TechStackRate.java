package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechStackRate {
    @NotNull
    @Size(min = 2)
    private String techStack;
    private String rate;
    private boolean isSpecial;
    private String kekaUserId;
    private String technology;
}
