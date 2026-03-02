package com.codebynamdev.authn.entity;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;
@Table(name = "driver_document")
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

    // Getter and Setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public byte[] getAadhaarDocument() {
        return aadhaarDocument;
    }

    public void setAadhaarDocument(byte[] aadhaarDocument) {
        this.aadhaarDocument = aadhaarDocument;
    }

    public byte[] getLicenseDocument() {
        return licenseDocument;
    }

    public void setLicenseDocument(byte[] licenseDocument) {
        this.licenseDocument = licenseDocument;
    }

    public DriverProfile getDriverProfile() {
        return driverProfile;
    }

    public void setDriverProfile(DriverProfile driverProfile) {
        this.driverProfile = driverProfile;
    }
}
