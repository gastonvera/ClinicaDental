package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Patient;
import com.example.ClinicaDental.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientService patientService;


    /** Aqui voy a cargar los pacientes*/

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Patient> create(@RequestBody Patient newPatient) throws ServerException {
        Patient patient = patientService.save(newPatient);
        if (patient == null) {
            throw new ServerException("Error al insertar paciente");
        } else {
            return new ResponseEntity<>(patient, HttpStatus.CREATED);
        }
    }

    /** Aqui voy a buscar un paciente por id*/

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable int id) throws ServerException{
        if(patientService.findById(id) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.findById(id));
        }
    }

    /** Aqui voy a modificar a un paciente*/

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable int id , @RequestBody Patient patient) throws ServerException{
        if(patientService.findById(id) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.update(id,patient));
        }
    }

    /** Aqui voy a eliminar a un paciente */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (patientService.findById(id) != null) {
            patientService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients() throws Exception {
        if (patientService.findAll() == null){
            throw new ServerException("Lista vacia");
        }else {
            return ResponseEntity.ok(patientService.findAll());
        }
    }

}
