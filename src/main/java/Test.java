import com.example.ClinicaDental.entity.Address;
import com.example.ClinicaDental.repository.implementation.AddressDaoH2;
import com.example.ClinicaDental.repository.implementation.PatientDaoH2;
import com.example.ClinicaDental.service.PatientService;

public class Test {
    public static void main(String[] args) {
        /*PatientService patientService = new PatientService(new PatientDaoH2());
        DentistService dentistService = new DentistService(new DentistDaoH2());
        AppointmentService appointmentService = new AppointmentService(new AppointmentDaoH2());
        Date date1 = new Date(2021, Calendar.OCTOBER,15);
        Address direccion1 = new Address("Ameghino", 3089,"Resistencia","Chaco");
        Patient paciente1 = new Patient("Mariano","Gomez","mariano@gmail.com",37896589,date1,direccion1);
        patientService.save(paciente1);
        Dentist dentist1 = new Dentist("Grisela", "Miraflores", "griselda@gmail.com","4555668",4588665,direccion1);
        dentistService.save(dentist1);

        Appointment appointment1 = new Appointment(paciente1,dentist1,date1,10);
        appointmentService.save(appointment1);



        AppointmentService appointmentService = new AppointmentService(new AppointmentDaoH2());
        ArrayList<Appointment> turnos = (ArrayList<Appointment>) appointmentService.findAll();
        for (int i = 0; i<turnos.size();i++){
            System.out.println(turnos.get(i).getPatient().getName());
            System.out.println(turnos.get(i).getPatient().getLastname());
            System.out.println(turnos.get(i).getDentist().getName());
            System.out.println(turnos.get(i).getDentist().getLastname());
        }


        PatientService patientService = new PatientService(new PatientDaoH2());
        patientService.update(8,"Naila");

         */
        Address direccion2 = new Address("Obligado", 2000,"Resistencia","Chaco");
        AddressDaoH2 addressDaoH2 = new AddressDaoH2();
        addressDaoH2.save(direccion2);

    }
}
