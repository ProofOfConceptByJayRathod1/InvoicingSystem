package com.simform.invoicingsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectView {


    private String projectName;
    private String projectModelName;
    private String clientName;
    private String refEmail;
    private String invoiceCycle;
    private String payModel;
    private String accountType;


}
