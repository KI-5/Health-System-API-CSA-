//packages
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;
//imports
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * class to hold data related to an appointment
 * 
 * @author inazu
 */
public class Appointment {
    
    //appointment id or number
    private int appointmentId;
    
    //date of appointment
    private LocalDate date;
    
    //time of appointment
    private LocalTime time;
     
    //doctor
    private Doctor doctor;
    
    //patient
    private Patient patient;
    
    //default constructor
    public Appointment() {
    }
    
    //parameterised constructor

    /**
     *
     * @param appointmentId id relevant to a appointment  as int
     * @param date date of the appointment  as LocalDate
     * @param time time of the appointment  as LocalTime
     * @param patient patient of the appointment  as String
     * @param doctor doctor of the appointment  as String
     */
    public Appointment(int appointmentId, LocalDate date,LocalTime time, Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.time = time;
        this.doctor=doctor;
        this.patient=patient;
    }
    

    //getter for date
    public LocalDate getDate() {
        return date;
    }

    //getter for time
    public LocalTime getTime() {
        return time;
    }

    //getter for appiointment id
    public int getAppointmentId() {
        return appointmentId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    //setter for appiointment id
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //setter for date
    public void setDate(LocalDate date) {
        this.date = date;
    }

    //setter for time
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
}
