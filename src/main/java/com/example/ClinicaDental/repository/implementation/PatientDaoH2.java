package com.example.ClinicaDental.repository.implementation;

import com.example.ClinicaDental.repository.IDaoPatient;
import com.example.ClinicaDental.entity.Address;
import com.example.ClinicaDental.entity.Patient;
import com.example.ClinicaDental.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDaoH2 implements IDaoPatient<Patient> {

    private final AddressDaoH2 addressDaoH2 = new AddressDaoH2();

    @Override
    public Patient save(Patient patient) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();


            Address address = addressDaoH2.save(patient.getAddress());
            patient.getAddress().setId(address.getId());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO patients(name,lastname,email,dni,addmission_date,address_id) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setString(3,patient.getEmail());
            preparedStatement.setInt(4, patient.getDni());
            preparedStatement.setDate(5, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setInt(6, patient.getAddress().getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                patient.setId((long) keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return patient;
    }

    @Override
    public Patient update(int id, Patient patient) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("UPDATE patients SET id = ?, name = ?, lastname = ?, email = ?, dni = ?, addmission_date = ?, address_id = ? WHERE id = ?;");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,patient.getName());
            preparedStatement.setString(3, patient.getLastname());
            preparedStatement.setString(4,patient.getEmail());
            preparedStatement.setInt(5, patient.getDni());
            preparedStatement.setDate(6, Util.utilDateToSqlDate(patient.getAdmissionDate()));
            preparedStatement.setInt(7, patient.getAddress().getId());
            preparedStatement.setInt(8,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            patient.setId((long) id);
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }

    @Override
    public void delete(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Borramos el domicilio del paciente
            int idDomicilio = findById(id).getAddress().getId();
            addressDaoH2.delete(idDomicilio);

            //3 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM patients where id = ?");
            preparedStatement.setInt(1,id);

            //4 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Patient findById(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Patient patient = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM patients where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idPatient = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date addmission_date = result.getDate("addmission_date");
                int idAddress = result.getInt("address_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                patient = new Patient(name,lastname,email,dni,addmission_date,address);
                patient.setId((long) idPatient);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }

    @Override
    public List<Patient> findAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        List<Patient> patients = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM patients");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idPatient = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                int dni = result.getInt("dni");
                Date addmission_date = result.getDate("addmission_date");
                int idAddress = result.getInt("address_id");

                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                Patient patient = new Patient(name,lastname,email,dni,addmission_date,address);
                patient.setId((long) idPatient);
                patients.add(patient);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return patients;
    }

    @Override
    public Patient findByEmail(String email) {
        Connection connection;
        PreparedStatement preparedStatement;
        Patient patient = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM patients where email = ?");
            preparedStatement.setString(1,email);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idPatient = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String emailResult = result.getString("email");
                int dni = result.getInt("dni");
                Date addmission_date = result.getDate("addmission_date");
                int idAddress = result.getInt("address_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                patient = new Patient(name,lastname,emailResult,dni,addmission_date,address);
                patient.setId((long) idPatient);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return patient;
    }


}