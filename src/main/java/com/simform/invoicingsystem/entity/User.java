package com.simform.invoicingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 100)
    private String email;
    private String password;
    private String reset_password_token;
    private boolean status;
    private long user_role_id;
    private LocalDateTime created_at;
    @Column(length = 40)
    private String created_by;
    private LocalDateTime updated_at;
    @Column(length = 40)
    private String updated_by;
    private LocalDateTime deleted_at;
    @Column(length = 40)
    private String deleted_by;
}
