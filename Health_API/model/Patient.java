//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;

//imports

/**
 * Patient class gives the the information related to a patient with all their details
 * 
 * @author inazu
 */
public class Patient extends Person{
    
    //a list to include the medical history of the person
    private String medicalHistory;
    
    //String to include the current health status
    private String currentHealthStatus;
    
    //default constructor
    public Patient() {
        super();
    }
    
     //parameterised constructor

    /**
     *
     * @param patientId id of patient as int
     * @param patientName name of patient as String
     * @param patientEmailAddress mail of patient as String
     * @param contactNumber number  of patient as String
     * @param patientAddress address  of patient as String
     * @param medicalHistory medical history  of patient as String
     * @param currentHealthStatus health status of patient as String
     */
    public Patient(Integer patientId, String patientName, String patientEmailAddress, String contactNumber, String patientAddress, String medicalHistory, String currentHealthStatus) {
        super(patientId, patientName, patientEmailAddress,contactNumber , patientAddress);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }
    
 
    //medical history getter
    public String getMedicalHistory() {
        return medicalHistory;
    }

    //current health status getter
    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }


    //medical history setter
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    //current health status setter
    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }

}