package com.example.ClinicaDental.controller;

import com.example.ClinicaDental.repository.implementation.DentistDaoH2;
import com.example.ClinicaDental.entity.Dentist;
import com.example.ClinicaDental.service.DentistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("/odontologos")
public class DentistController {
    private DentistService dentistService = new DentistService(new DentistDaoH2());

    /** Aqui voy a cargar los dentistas */

    @PostMapping(path = "/",
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

    /**Aqui voy a mostrar en en endpoint index todos los dentistas */

    @GetMapping("/list")
    public String welcome(Model model) {
        var dentists = dentistService.findAll();
        model.addAttribute("dentists",dentists);
        return "dentists";
    }

    /** Aqui voy a modificar a un dentista */

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<Dentist> update(@PathVariable int id , @PathVariable String newName) throws ServerException{
        if(dentistService.update(id,newName) == null){
            throw new ServerException("No se encontro el odontologo");
        } else {
            return ResponseEntity.ok(dentistService.update(id,newName));
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Dentist>> findAllDentists() throws ServerException {
        if (dentistService.findAll() == null){
            throw new ServerException("Lista vacia");
        } else {
            return ResponseEntity.ok(dentistService.findAll());
        }
    }

}
