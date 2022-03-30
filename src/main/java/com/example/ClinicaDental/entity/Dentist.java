package com.example.ClinicaDental.entity;

public class Dentist {
    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String matricula;
    private int dni;
    private Address address;

    public Dentist(String name, String lastname, String email, String matricula, int dni, Address address) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.matricula = matricula;
        this.dni = dni;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
