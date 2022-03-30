package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.repository.implementation.PatientDaoH2;
import com.example.ClinicaDental.entity.Patient;
import com.example.ClinicaDental.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PatientController {
    private PatientService patientService = new PatientService(new PatientDaoH2());

    /** Aqui voy a cargar los pacientes*/

    @PostMapping(path = "/",
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

    /**Aqui voy a mostrar en en endpoint index todos los pacientes */

    @GetMapping("/list")
    public String welcome(Model model) {
        var patients = patientService.findAll();
        model.addAttribute("patients",patients);
        return "patients";
    }

    /** Aqui voy a modificar a un paciente*/

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<Patient> update(@PathVariable int id , @PathVariable String newName) throws ServerException{
        if(patientService.update(id,newName) == null){
            throw new ServerException("No se encontro el paciente");
        } else {
            return ResponseEntity.ok(patientService.update(id,newName));
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Patient>> findAllPatients() throws Exception {
        if (patientService.findAll() == null){
            throw new ServerException("Lista vacia");
        }else {
            return ResponseEntity.ok(patientService.findAll());
        }
    }

}
