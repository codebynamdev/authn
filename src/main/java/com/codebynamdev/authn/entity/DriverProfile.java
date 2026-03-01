package com.codebynamdev.authn.entity;

import jakarta.persistence.*;

public class DriverProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModel;
    @Enumerated(EnumType.STRING)
    private DriverStatus status;   // PENDING, APPROVED, REJECTED

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToOne(mappedBy = "driverProfile", cascade = CascadeType.ALL)
    private DriverDocument driverDocument;
}
