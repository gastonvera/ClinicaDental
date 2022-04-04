package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.entity.Dentist;
import com.example.ClinicaDental.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    /** Aqui voy a cargar los dentistas */

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dentist> create(@RequestBody Dentist newDentist) throws ServerException {
        Dentist dentist = dentistService.save(newDentist);
        if (dentist == null) {
            throw new ServerException("Error al insertar dentista");
        } else {
            return new ResponseEntity<>(dentist, HttpStatus.CREATED);
        }
    }

    /** Aqui voy a buscar un dentista por id */

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> findById(@PathVariable int id) throws ServerException{
        if(dentistService.findById(id) == null){
            throw new ServerException("No se encontro el dentista");
        } else {
            return ResponseEntity.ok(dentistService.findById(id));
        }
    }

    /** Aqui voy a modificar a un dentista */

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> update(@PathVariable int id , @RequestBody Dentist dentist) throws ServerException{
        if(dentistService.update(id,dentist) == null){
            throw new ServerException("No se encontro el odontologo");
        } else {
            return ResponseEntity.ok(dentistService.update(id,dentist));
        }
    }

    /** Aqui voy a eliminar a un odontologo */

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        ResponseEntity<String> response = null;

        if (dentistService.findById(id) != null) {
            dentistService.delete(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Turno eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return response;
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> findAllDentists() throws ServerException {
        if (dentistService.findAll() == null){
            throw new ServerException("Lista vacia");
        } else {
            return ResponseEntity.ok(dentistService.findAll());
        }
    }

}
