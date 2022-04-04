package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.IDaoAppointment;
import com.example.ClinicaDental.entity.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private IDaoAppointment<Appointment> appointmentIdao;

    public AppointmentService(IDaoAppointment<Appointment> appointmentIdao) {
        this.appointmentIdao = appointmentIdao;
    }

    public Appointment save(Appointment a){
        return appointmentIdao.save(a);
    }
    public Appointment update(int id, Appointment appointment){return appointmentIdao.update(id,appointment);}
    public Appointment findById(int id){
        return appointmentIdao.findById(id);
    }
    public List<Appointment> findAll(){
        return appointmentIdao.findAll();
    }
    public void delete(int id){
        appointmentIdao.delete(id);
    }
}
