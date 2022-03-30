package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Patient;
import com.example.ClinicaDental.repository.implementation.AppointmentDaoH2;
import com.example.ClinicaDental.entity.Appointment;
import com.example.ClinicaDental.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/turnos")
public class AppointmentController {
    private AppointmentService appointmentService = new AppointmentService(new AppointmentDaoH2());

    /** Aqui voy a cargar los turnos*/

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appointment> create(@RequestBody Appointment newAppointment) throws ServerException {
        Appointment appointment = appointmentService.save(newAppointment);
        if (appointment == null) {
            throw new ServerException("Error al insertar el turno");
        } else {
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        }
    }

    /** Aqui voy a buscar un turno por id*/

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findById(@PathVariable int id) throws ServerException{
        if(appointmentService.findById(id) == null){
            throw new ServerException("No se encontro el turno");
        } else {
            return ResponseEntity.ok(appointmentService.findById(id));
        }
    }
    /**Aqui voy a mostrar en en endpoint index todos los turnos */

    @GetMapping("/list")
    public String welcome(Model model) {
        var appointments = appointmentService.findAll();
        model.addAttribute("appointments",appointments);
        return "appointments";
    }

    /** Aqui voy a modificar a un turno*/

    @PutMapping("/{id}/{room}")
    public ResponseEntity<Appointment> update(@PathVariable int id , @PathVariable int room) throws ServerException{
        if(appointmentService.update(id,room) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(appointmentService.update(id,room));
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Appointment>> findAllPatients() throws Exception {
        if (appointmentService.findAll() == null){
            throw new ServerException("Lista vacia");
        }else {
            return ResponseEntity.ok(appointmentService.findAll());
        }
    }
}
