package com.example.ClinicaDental.entity;

import java.util.Date;

public class Patient {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private int dni;
    private Date admissionDate;
    private Address address;

    public Patient(String name, String lastname, String email, int dni, Date admissionDate,Address address) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
    }

    /*Sobrecargo al constructor
    public Patient(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
    }

     */

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }
}
