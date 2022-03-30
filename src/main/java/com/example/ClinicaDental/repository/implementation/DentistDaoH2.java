package com.example.ClinicaDental.repository.implementation;

import com.example.ClinicaDental.repository.IDaoDentist;
import com.example.ClinicaDental.entity.Address;
import com.example.ClinicaDental.entity.Dentist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoH2 implements IDaoDentist<Dentist> {

    private final AddressDaoH2 addressDaoH2 = new AddressDaoH2();

    @Override
    public Dentist save(Dentist dentist) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();


            Address address = addressDaoH2.save(dentist.getAddress());
            dentist.getAddress().setId(address.getId());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO dentists(name,lastname,email,matricula,dni,address_id) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, dentist.getName());
            preparedStatement.setString(2, dentist.getLastname());
            preparedStatement.setString(3,dentist.getEmail());
            preparedStatement.setString(4,dentist.getMatricula());
            preparedStatement.setInt(5, dentist.getDni());

            preparedStatement.setInt(6, dentist.getAddress().getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                dentist.setId((long) keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return dentist;
    }

    @Override
    public Dentist update(int id, String newName) {
        Connection connection;
        PreparedStatement preparedStatement;
        Dentist dentist = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("UPDATE dentists SET name = ? WHERE id = ?;");
            preparedStatement.setString(1,newName);
            preparedStatement.setInt(2,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();

            //4 Obtener resultados

            dentist = findById(id);

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return dentist;
    }


    @Override
    public void delete(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM dentists where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Dentist findById(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Dentist dentist = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM dentists where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idDentist = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String enrollment = result.getString("matricula");
                int dni = result.getInt("dni");
                int idAddress = result.getInt("address_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                dentist = new Dentist(name,lastname,email,enrollment,dni,address);
                dentist.setId((long) idDentist);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return dentist;
    }

    @Override
    public List<Dentist> findAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        List<Dentist> dentists = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM dentists");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idDentist = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String enrollment = result.getString("matricula");
                int dni = result.getInt("dni");
                int idAddress = result.getInt("address_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                Dentist dentist = new Dentist(name,lastname,email,enrollment,dni,address);
                dentist.setId((long) idDentist);
                dentists.add(dentist);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return dentists;
    }

    @Override
    public Dentist findByEmail(String email) {
        Connection connection;
        PreparedStatement preparedStatement;
        Dentist dentist = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM dentists where email = ?");
            preparedStatement.setString(1,email);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idDentist = result.getInt("id");
                String name = result.getString("name");
                String lastname = result.getString("lastname");
                String emailResult = result.getString("email");
                String enrollment = result.getString("matricula");
                int dni = result.getInt("dni");
                int idAddress = result.getInt("address_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Address address = addressDaoH2.findById(idAddress);
                dentist = new Dentist(name,lastname,emailResult,enrollment,dni,address);
                dentist.setId((long) idDentist);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return dentist;
    }
}
