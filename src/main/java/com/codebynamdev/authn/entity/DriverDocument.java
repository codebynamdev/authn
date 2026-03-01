package com.codebynamdev.authn.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

public class DriverDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aadhaarNumber;
    @Lob
    @Column(name = "aadhaar_document", columnDefinition = "BYTEA")
    private byte[] aadhaarDocument;

    @Lob
    @Column(name = "license_document", columnDefinition = "BYTEA")
    private byte[] licenseDocument;

    @OneToOne
    @JoinColumn(name = "driver_profile_id")
    private DriverProfile driverProfile;

}
