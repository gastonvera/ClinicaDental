package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.IDaoPatient;
import com.example.ClinicaDental.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final IDaoPatient<Patient> patientIDao;

    public PatientService(IDaoPatient<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }


    public Patient save(Patient p){
        return patientIDao.save(p);
    }
    /*
    public Patient simpleSave(Patient p){
        return patientIDao.simpleSave(p);
    }

     */
    public Patient findById(int id){
        return patientIDao.findById(id);
    }
    public Patient update(int id, Patient patient){return patientIDao.update(id,patient);}
    public List<Patient> findAll(){
        return patientIDao.findAll();
    }
    public void delete(int id){
        patientIDao.delete(id);
    }
    public Patient findByEmail(String email){
        return patientIDao.findByEmail(email);
    }
}
