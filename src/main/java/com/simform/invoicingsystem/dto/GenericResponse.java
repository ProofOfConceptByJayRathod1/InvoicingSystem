package com.simform.invoicingsystem.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    private boolean success;
    private String message;
    private int code;
    private LocalDateTime timestamp;
}