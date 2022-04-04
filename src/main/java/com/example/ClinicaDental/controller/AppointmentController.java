package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Appointment;
import com.example.ClinicaDental.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /** Aqui voy a cargar los turnos*/

    @PostMapping(
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

    /** Aqui voy a modificar a un turno*/

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable int id , @RequestBody Appointment appointment) throws ServerException{
        if(appointmentService.update(id,appointment) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(appointmentService.update(id,appointment));
        }
    }

    /** Aqui voy a eliminar a un turno */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (appointmentService.findById(id) != null) {
            appointmentService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> findAllPatients() throws Exception {
        if (appointmentService.findAll() == null){
            throw new ServerException("Lista vacia");
        }else {
            return ResponseEntity.ok(appointmentService.findAll());
        }
    }
}
