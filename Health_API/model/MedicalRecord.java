//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;

//imports
import java.util.ArrayList;
import java.util.List;
/**
 * class to hold data related to a medical record
 * @author inazu
 */
public class MedicalRecord {
    //medical record id
    private int recordId;
    
    //list of all diagnoses
    private List<String> diagnosesList;
    
    //list of treatments
    private List<String> treatmentList;
    
    //patient related
    private Patient patient;
    
    //doctor who was involved
    private Doctor doctor;
    
    //default constructor
    public MedicalRecord() {
    }

    //parameterised constructor

    /**
     *
     * @param recordId id relevant to a medical record  as int
     * @param diagnoses diagnoses relevant to a medical record  as String type list
     * @param treatments treatments relevant to a medical record  as String type list
     * @param doctor the doctor involved in creating the medical record as Doctor
     * @param patient the patient who has a medical record as Patient
     */
    public MedicalRecord(int recordId, Patient patient,Doctor doctor, List<String> diagnoses, List<String> treatments) {
        this.recordId =recordId;
        this.patient=patient;
        this.doctor=doctor;
        this.diagnosesList= diagnoses;
        this.treatmentList = treatments;
    }

    // getter for the record id
    public int getRecordId() {
        return recordId;
    }

    // getter for the diagnoses
    public List<String> getDiagnosesList() {
        return new ArrayList<>(diagnosesList);
    }

    // getter for the treatments
    public List<String> getTreatmentList() {
        return new ArrayList<>(treatmentList);
    }

    // getter for the patient
    public Patient getPatient() {
        return patient;
    }

    // getter for the patient
    public Doctor getDoctor() {
        return doctor;
    }
    
    //setter for recordId
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    //setter for diagnoses list
    public void setDiagnosesList(List<String> diagnosesList) {
        this.diagnosesList = diagnosesList;
    }

    //setter for treatment list
    public void setTreatmentList(List<String> treatmentList) {
        this.treatmentList = treatmentList;
    }

    //setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //setter for doctor
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
