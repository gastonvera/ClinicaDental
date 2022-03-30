package com.example.ClinicaDental.entity;

import java.util.Date;

public class Appointment {
    private Long id;
    private Patient patient;
    private Dentist dentist;
    private Date date;
    private int consultingRoom;

    public Appointment(Patient patient, Dentist dentist, Date date, int consultingRoom) {
        this.patient = patient;
        this.dentist = dentist;
        this.date = date;
        this.consultingRoom = consultingRoom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConsultingRoom() {
        return consultingRoom;
    }

    public void setConsultingRoom(int consultingRoom) {
        this.consultingRoom = consultingRoom;
    }
}
