package com.example.ClinicaDental.repository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDaoPatient<T> {

    public T save(T t);
    public T update(int id, T t);
    public T findById(int id);
    public void delete(int id);
    public List<T> findAll();
    public T findByEmail(String email);

}
