package com.example.ClinicaDental.repository;

import java.util.List;

public interface IDaoAddress<T> {

    public T save(T t);
    public T findById(int id);
    public void delete(int id);
    public List<T> findAll();

}
