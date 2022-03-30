package com.example.ClinicaDental.service;

import com.example.ClinicaDental.repository.IDaoDentist;
import com.example.ClinicaDental.entity.Dentist;

import java.util.List;

public class DentistService {

    private IDaoDentist<Dentist> dentistIDao;

    public DentistService(IDaoDentist<Dentist> dentistIDao) {
        this.dentistIDao = dentistIDao;
    }

    public Dentist save(Dentist d){
        return dentistIDao.save(d);
    }
    public Dentist update(int id, String newName){return dentistIDao.update(id,newName);}
    public Dentist findById(int id){
        return dentistIDao.findById(id);
    }
    public List<Dentist> findAll(){
        return dentistIDao.findAll();
    }
    public void delete(int id){
        dentistIDao.delete(id);
    }
    public Dentist findByEmail(String email){
        return dentistIDao.findByEmail(email);
    }
}
