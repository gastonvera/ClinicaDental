package com.example.ClinicaDental.repository.implementation;

import com.example.ClinicaDental.repository.IDaoAppointment;
import com.example.ClinicaDental.entity.Appointment;
import com.example.ClinicaDental.entity.Dentist;
import com.example.ClinicaDental.entity.Patient;
import com.example.ClinicaDental.util.Util;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentDaoH2 implements IDaoAppointment<Appointment> {

    private final PatientDaoH2 patientDaoH2 = new PatientDaoH2();
    private final DentistDaoH2 dentistDaoH2 = new DentistDaoH2();


    @Override
    public Appointment save(Appointment appointment) {
        Connection connection;
        PreparedStatement preparedStatement;

        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();


            Patient patient = patientDaoH2.save(appointment.getPatient());
            appointment.getPatient().setId(patient.getId());


            Dentist dentist = dentistDaoH2.save(appointment.getDentist());
            appointment.getDentist().setId(dentist.getId());

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO appointments(patient_id,dentist_id, consulting_date, consulting_room) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, Math.toIntExact(appointment.getPatient().getId()));
            preparedStatement.setInt(2, Math.toIntExact(appointment.getDentist().getId()));
            preparedStatement.setDate(3, Util.utilDateToSqlDate(appointment.getDate()));
            preparedStatement.setInt(4, appointment.getConsultingRoom());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                appointment.setId((long) keys.getInt(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return appointment;
    }

    @Override
    public Appointment update(int id, Appointment appointment) {
            Connection connection;
            PreparedStatement preparedStatement;
            try {
                //1 Levantar el driver y Conectarnos
                connection = ConnectionH2.getConnection();

                //2 Crear una sentencia
                preparedStatement = connection.prepareStatement("UPDATE appointments SET patient_id = ?, dentist_id = ?,consulting_date = ?, consulting_room = ? WHERE id = ?;");
                preparedStatement.setInt(1, Math.toIntExact(appointment.getPatient().getId()));
                preparedStatement.setInt(2, Math.toIntExact(appointment.getDentist().getId()));
                preparedStatement.setDate(3, Util.utilDateToSqlDate(appointment.getDate()));
                preparedStatement.setInt(4, appointment.getConsultingRoom());
                preparedStatement.setInt(5,id);

                //3 Ejecutar una sentencia SQL
                preparedStatement.executeUpdate();
                appointment.setId((long) id);
                preparedStatement.close();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }

            return appointment;

    }

    @Override
    public void delete(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM appointments where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Appointment findById(int id) {
        Connection connection;
        PreparedStatement preparedStatement;
        Appointment appointment = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT * FROM appointments where id = ?");
            preparedStatement.setInt(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                int idAppointment = result.getInt("id");
                int idPatient = result.getInt("patient_id");
                int idDentist = result.getInt("dentist_id");
                Date date = result.getDate("consulting_date");
                int consultingRoom = result.getInt("consulting_room");

                Patient patient = patientDaoH2.findById(idPatient);
                Dentist dentist = dentistDaoH2.findById(idDentist);
                appointment = new Appointment(patient,dentist,date,consultingRoom);
                appointment.setId((long) idAppointment);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        Connection connection;
        PreparedStatement preparedStatement;
        List<Appointment> appointments = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = ConnectionH2.getConnection();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM appointments");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                int idAppointment = result.getInt("id");
                int idPatient = result.getInt("patient_id");
                int idDentist = result.getInt("dentist_id");
                Date date = result.getDate("consulting_date");
                int consultingRoom = result.getInt("consulting_room");

                Patient patient = patientDaoH2.findById(idPatient);
                Dentist dentist = dentistDaoH2.findById(idDentist);
                Appointment appointment = new Appointment(patient,dentist,date,consultingRoom);
                appointment.setId((long) idAppointment);

                appointments.add(appointment);

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return appointments;
    }
}
