/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.doctorsappointmentmanagementsystem;

/**
 *
 * @author Shimi
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class DoctorsAppointmentManagementSystem {
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);   
    static {
        doctors.add(new Pediatrist("Masrur Zaman"));
        doctors.add(new Gastroenterologist("Lubaba Noor"));
        doctors.add(new ENT_Specialist("Humaira Inaya"));
        doctors.add(new Dermatologist("Kabir Chowdhury"));
        doctors.add(new Cardiologist("Khalid Hossain"));
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n*****-----*****Doctors Appointment Management System*****-----*****");
            System.out.println("\t\t1. Patient Registration");
            System.out.println("\t\t2. Show Available Doctors");
            System.out.println("\t\t3. Book an Appointment");
            System.out.println("\t\t4. View Appointments");
            System.out.println("\t\t5. Exit");
            System.out.print("\t\tSelect and Enter an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    PatientReggistration();
                    break;
                case 2:
                    showAvailableDoctors();
                    break;
                case 3:
                    bookAppointment();
                    break;
                case 4:
                    viewAppointments();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option Invalid. Please try again later.");
            }
        }
    }

   
    private static void PatientReggistration() {
        System.out.print("\t\tEnter patient name: ");
        String name = scanner.nextLine();
        System.out.print("\t\tEnter phone number: ");
        String contactInfo = scanner.nextLine();
        patients.add(new Patient(name, contactInfo));
        System.out.println("\t\tPatient " + name + " registration successful.");
    }

  
    private static void showAvailableDoctors() {
        System.out.println("\n\t\tAvailable Doctors:");
        for (Doctor doctor : doctors) {
            doctor.displayAvailability();
        }
    }

 
    private static void bookAppointment() {
        System.out.println("\n\t\tBooking an appointment");
        System.out.print("\t\tEnter patient name: ");
        String patientName = scanner.nextLine();
        System.out.print("\t\tEnter patient phone number: ");
        String patientContactDetails = scanner.nextLine();
        

        Patient patient = null;
        for (Patient p : patients) {   
            if (p.getName().equalsIgnoreCase(patientName) && p.getContactDetails().equalsIgnoreCase(patientContactDetails)) {
                patient = p;                
                break;
    }
}
if (patient == null) {
    System.out.println("::::::::)))))))Patient not found. Please do registration first.::::::::)))))))");
    return;
}

        System.out.println("\t\tSelect doctor:");
        for (int i = 0; i < doctors.size(); i++) {
            System.out.println((i + 1) +  ". Dr. " + doctors.get(i).getName() + " (" + doctors.get(i).getExpertness() + ")");
        }
        int dIndex = scanner.nextInt() - 1;
        scanner.nextLine(); 

        if (dIndex < 0 || dIndex >= doctors.size()) {
            System.out.println("::::::::)))))))Invalid doctor selection.::::::::)))))))");
            return;
        }

        Doctor doctor = doctors.get(dIndex);
        System.out.print("\t\tEnter date for appointment (DD-MM-YYYY): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(doctor, patient, date);
        appointment.saveToFile();
        System.out.println("\n***************Appointment booked successfully***************");
    }

    private static void viewAppointments() {
        System.out.println("\n\t\tAll Appointments:");
        try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("::::::::)))))))Reding Error appointments.::::::::)))))))");
        }
    }
}

class Doctor {
    private String name;
    private String expertness;
    private int id;
    public Doctor(String name, String expertness) {
        this.name = name;
        this.expertness = expertness;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertness() {
        return expertness;
    }

    public void setExpertness(String expertness) {
        this.expertness = expertness;
    }
    
    public int getId() {
        return id;
    }

    public void id(int id) {
        this.id = id;
    }

    public void displayAvailability() {
        System.out.println("Displaying availability for doctor: " + name);
    }
}

class Pediatrist extends Doctor {
    public Pediatrist(String name) {
        super(name, "Pediatrist");
    }

    @Override
    public void displayAvailability() {
        System.out.println(getId() + "Dr. " + getName() + " (Pediatrist) is available for walk-ins.");
    }
}

class Gastroenterologist extends Doctor {
    public Gastroenterologist(String name) {
        super(name, "Gastroenterologist");
    }

    @Override
    public void displayAvailability() {
        System.out.println(getId() + "Dr. " + getName() + " (Gastroenterologist) requires appointment confirmation.");
    }
}
class ENT_Specialist extends Doctor {
    public ENT_Specialist(String name) {
        super(name, "ENT_Specialist");
    }

    @Override
    public void displayAvailability() {
        System.out.println(getId() + "Dr. " + getName() + " (ENT_Specialist) requires appointment confirmation.");
    }
}
class Dermatologist extends Doctor {
    public Dermatologist(String name) {
        super(name, "Dermatologistt");
    }

    @Override
    public void displayAvailability() {
        System.out.println(getId() + "Dr. " + getName() + " (Dermatologist) requires appointment confirmation.");
    }
}
class Cardiologist extends Doctor {
    public Cardiologist(String name) {
        super(name, "Cardiologist");
    }

    @Override
    public void displayAvailability() {
        System.out.println(getId() + "Dr. " + getName() + " (Cardiologist) requires appointment confirmation.");
    }
}


class Patient {
    private String name;
    private String contactDetails;

    public Patient(String name, String contactDetails ) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}

class Appointment {
    private Doctor doctor;
    private Patient patient;
    private String date;

    public Appointment(Doctor doctor, Patient patient, String date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }


    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment: " + patient.getName() + " with Dr. " + doctor.getName() + " on " + date;
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
            writer.write(toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


