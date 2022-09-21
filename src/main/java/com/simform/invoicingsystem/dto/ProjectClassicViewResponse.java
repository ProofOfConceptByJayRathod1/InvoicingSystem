package com.simform.invoicingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClassicViewResponse {

    private List<ProjectClassicView> projectClassicViews;
    private int totalPages;
    private Long totalElements;
}
