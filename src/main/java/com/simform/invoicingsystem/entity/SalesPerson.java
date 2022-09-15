package com.simform.invoicingsystem.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name="Sales_persons")
public class SalesPerson {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(length=50)
    private String name;
    private long project_id;
    @Column(length=40)
    private String created_by;
    private LocalDateTime updated_at;
    @Column(length=40)
    private String updated_by;
    private LocalDateTime deleted_at;
    @Column(length=40)
    private String deleted_by;

}
