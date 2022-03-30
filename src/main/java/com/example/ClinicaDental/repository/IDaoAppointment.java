package com.example.ClinicaDental.repository;

import java.util.List;

public interface IDaoAppointment<T> {

    public T save(T t);
    public T update(int id, int room);
    public T findById(int id);
    public void delete(int id);
    public List<T> findAll();
}
