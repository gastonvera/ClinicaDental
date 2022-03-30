package com.example.ClinicaDental.repository.implementation;

import com.example.ClinicaDental.repository.IDaoAddress;
import com.example.ClinicaDental.entity.Address;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoH2 implements IDaoAddress<Address> {
    @Override
    public Address save(Address address) {

        Connection connection;
        PreparedStatement preparedStatement;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO addresses(street,number,location,province) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getNumber());
            preparedStatement.setString(3, address.getLocation());
            preparedStatement.setString(4, address.getProvince());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                address.setId(keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return address;
    }

    @Override
    public void delete(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM addresses where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Address findById(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Address address = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,street,number,location,province FROM addresses where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idAddress = result.getInt("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String location = result.getString("location");
                String province = result.getString("province");

                address = new Address(street,number,location,province);
                address.setId(idAddress);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return address;
    }

    @Override
    public List<Address> findAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        List<Address> addresses = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM addresses");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                int idAddress = result.getInt("id");
                String street = result.getString("street");
                int number = result.getInt("number");
                String location = result.getString("location");
                String province = result.getString("province");

                Address address = new Address(street,number,location,province);
                address.setId(idAddress);

                addresses.add(address);

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return addresses;
    }
}

