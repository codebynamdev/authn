package com.codebynamdev.authn.dto;

import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.MulticastChannel;

public class DriverSignupRequest {
    private String licenseNumber;
    private String aadharNumber;
    private MultipartFile licenseDocument;
    private MultipartFile aadharDocument;
    private String vehicleNumber;
    private String vehicleType;
    private String vehicleModel;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;


    //Getter

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public MultipartFile getLicenseDocument() {
        return licenseDocument;
    }

    public MultipartFile getAadharDocument() {
        return aadharDocument;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public void setLicenseDocument(MultipartFile licenseDocument) {
        this.licenseDocument = licenseDocument;
    }

    public void setAadharDocument(MultipartFile aadharDocument) {
        this.aadharDocument = aadharDocument;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
