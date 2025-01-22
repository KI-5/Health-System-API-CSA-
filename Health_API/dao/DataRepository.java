//packages
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;
//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Appointment;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Billing;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Doctor;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.MedicalRecord;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Patient;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Person;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Prescription;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *Class to store the lists of data from all the DAO classes
 * 
 * @author inazu
 */
public class DataRepository {
    //person list
    public static final List<Person> personList = new ArrayList<>();
    //patients list
    public static final List<Patient> patientList = new ArrayList<>();
    //doctors list
    public static final List<Doctor> doctorList = new ArrayList<>();
    //appointments list
    public static final List<Appointment> appointmentList = new ArrayList<>();
    //medical records list
    public static final List<MedicalRecord> medicalRecordList = new ArrayList<>();
    //prescription list
    public static final List<Prescription> prescriptionList = new ArrayList<>();
    //billing list
    public static final List<Billing> billingList = new ArrayList<>();
    //counters for each list of entity
    public static final AtomicInteger patientIdCount = new AtomicInteger(0);
    public static final AtomicInteger doctorIdCount = new AtomicInteger(0);
    public static final AtomicInteger appointmentIdCount = new AtomicInteger(0);
    public static final AtomicInteger medicalRecordIdCount = new AtomicInteger(0);
    public static final AtomicInteger prescriptionIdCount = new AtomicInteger(0);
    public static final AtomicInteger billingIdCount = new AtomicInteger(0);
    public static final AtomicInteger personIdCount = new AtomicInteger(0);
    
    //method to access the data
    static {
        initializePatients();
        initializeDoctors();
        initializeAppointments();
        initializeMedicalRecords();
        initializePrescriptions();
        initializeBillings();
        initializePersons();
    }
    
    //patients data
    private static void initializePatients() {
        patientList.add(new Patient(patientIdCount.incrementAndGet(), "John Doe", "jdjd@example.com", "555-1234", "12 Elm St", "Fair", "Diabetes"));
        patientList.add(new Patient(patientIdCount.incrementAndGet(), "Jane Smith", "jsmithy@example.com", "555-5678", "34 Oak St", "Good", "Obesity"));
    }
    //doctors data
    private static void initializeDoctors() {
        doctorList.add(new Doctor(doctorIdCount.incrementAndGet(), "Dr. Alice", "alica@example.com", "123-456-7890", "1234 Center St", "Cardiology"));
        doctorList.add(new Doctor(doctorIdCount.incrementAndGet(), "Dr. Bob", "bob@example.com", "234-567-8901", "5678 North St", "Dermatology"));
    }
    //apointments data
    private static void initializeAppointments() {
        appointmentList.add(new Appointment(appointmentIdCount.incrementAndGet(), LocalDate.of(2024, 7, 2), LocalTime.now(), patientList.get(0), doctorList.get(0)));
        appointmentList.add(new Appointment(appointmentIdCount.incrementAndGet(), LocalDate.of(2024, 7, 3), LocalTime.now(), patientList.get(1), doctorList.get(1)));
    }
    //medical records data
    private static void initializeMedicalRecords() {
        medicalRecordList.add(new MedicalRecord(medicalRecordIdCount.incrementAndGet(), patientList.get(0), doctorList.get(0), List.of("Hypertension"), List.of("Lisinopril 10 mg daily")));
        medicalRecordList.add(new MedicalRecord(medicalRecordIdCount.incrementAndGet(), patientList.get(1), doctorList.get(1), List.of("Type 2 Diabetes"), List.of("Metformin 500 mg twice daily")));
    }
    //prescription data
    private static void initializePrescriptions() {
        prescriptionList.add(new Prescription(prescriptionIdCount.incrementAndGet(), "Metformin", "500 mg", "Twice a day", 30, doctorList.get(0), patientList.get(1)));
        prescriptionList.add(new Prescription(prescriptionIdCount.incrementAndGet(), "Atorvastatin", "20 mg", "Once a day", 30, doctorList.get(1), patientList.get(0)));
    }
    //billing data
    private static void initializeBillings() {
        billingList.add(new Billing(billingIdCount.incrementAndGet(), 200.50, "Unpaid", LocalDate.now().plusMonths(1), LocalDate.now(), "Credit Card", 0.0, "Cardiology consultation", 1, patientList.get(0), patientList.get(1), 1, 1));
    }
    //person data
    private static void initializePersons() {
        personList.add(new Person(personIdCount.incrementAndGet(), "Admin", "admin@example.com", "000-000-0000", "Admin Address"));
        personList.add(new Person(personIdCount.incrementAndGet(), "Dr. Alice", "alica@example.com", "123-456-7890", "1234 Center St"));
    }
}




