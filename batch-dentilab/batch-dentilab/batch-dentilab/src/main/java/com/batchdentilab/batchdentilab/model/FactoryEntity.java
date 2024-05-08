package com.batchdentilab.batchdentilab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Table
@NoArgsConstructor
@Entity(name = "factory")
public class FactoryEntity {
    @Id
    @Column(name = "ID_FACTORY", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String nameFactory;

    @Column(name = "EMAIL")
    private String emailFactory;

    @Column(name = "ADDRESS")
    private Timestamp addressFactory;
}
