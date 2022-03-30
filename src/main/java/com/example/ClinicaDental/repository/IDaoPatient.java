package com.example.ClinicaDental.repository;

import java.util.List;

public interface IDaoPatient<T> {

    public T save(T t);
    public T update(int id, String newName);
    public T findById(int id);
    public void delete(int id);
    public List<T> findAll();
    public T findByEmail(String email);

}
