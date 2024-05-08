package com.batchdentilab.batchdentilab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Table
@NoArgsConstructor
@Entity(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "ID_ORDER", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_FACTORY", nullable = false)
    private Long idFactory;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "FILE_PATH", nullable = false)
    private String filePath;

    @Column(name = "TIMESTAMP_SEND", nullable = false)
    @CreationTimestamp
    private LocalDateTime timestamp_send;
}
