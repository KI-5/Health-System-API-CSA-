//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;

//imports
/**
 * class to hold data related to an Prescription
 * 
 * @author inazu
 */
public class Prescription {
    
    //prescription number
    private int prescriptionId;
    //prescribed medication
    private String prescribedMedication;
    //dosage given
    private String recommendedDosage;
    //doctors instructions
    private String doctorsInstructions;
    //duration for medication
    private int medicationDurationDays;
    //doctor in charge
    private Doctor doctorInCharge;
    //patient 
    private Patient patientInConcern;
    //default constructors
    public Prescription() { 
    }
    //parameterised constructor
    /**
     *
     * @param prescriptionId id relevant to a prescription  as int
     * @param prescribedMedication medication prescribed relevant to a prescription  as String
     * @param recommendedDosage recommended dosage relevant to a prescription  as String
     * @param doctorsInstructions instructions from the doctor relevant to a prescription  as String
     * @param medicationDurationDays the duration of the medication relevant to a prescription  as int
     * @param doctorInCharge the doctor who prescribed relevant to a prescription  as Doctor
     * @param patientInConcern the patient who was prescribed relevant to a prescription  as Patient
     */
    public Prescription(int prescriptionId, String prescribedMedication, String recommendedDosage, String doctorsInstructions, int medicationDurationDays, Doctor doctorInCharge, Patient patientInConcern) {
        this.prescriptionId = prescriptionId;
        this.prescribedMedication = prescribedMedication;
        this.recommendedDosage = recommendedDosage;
        this.doctorsInstructions = doctorsInstructions;
        this.medicationDurationDays = medicationDurationDays;
        this.doctorInCharge= doctorInCharge;
        this.patientInConcern=patientInConcern;
    }

    //getter for the prescription id
    public int getPrescriptionId() {
        return prescriptionId;
    }
    //getter for the prescription medication
    public String getPrescribedMedication() {
        return prescribedMedication;
    }
    //getter for the dosage
    public String getRecommendedDosage() {
        return recommendedDosage;
    }
    //getter for the instructions
    public String getDoctorsInstructions() {
        return doctorsInstructions;
    }
    //getter for the duration
    public int getMedicationDurationDays() {
        return medicationDurationDays;
    }
    //getter for doctor
    public Doctor getDoctorInCharge() {
        return doctorInCharge;
    }
    //getter for patient
    public Patient getPatientInConcern() {
        return patientInConcern;
    }
    //setter for the prescription id
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }
    //setter for the prescription medication
    public void setPrescribedMedication(String prescribedMedication) {
        this.prescribedMedication = prescribedMedication;
    }
    //setter for the dosage
    public void setRecommendedDosage(String recommendedDosage) {
        this.recommendedDosage = recommendedDosage;
    }
    //setter for the instructions
    public void setDoctorsInstructions(String doctorsInstructions) {
        this.doctorsInstructions = doctorsInstructions;
    }
    //setter for the duration
    public void setMedicationDurationDays(int medicationDurationDays) {
        this.medicationDurationDays = medicationDurationDays;
    }
    //setter for doctor
    public void setDoctorInCharge(Doctor doctorInCharge) {
        this.doctorInCharge = doctorInCharge;
    }

    //setter for patient
    public void setPatientInConcern(Patient patientInConcern) {
        this.patientInConcern = patientInConcern;
    }
    

}
