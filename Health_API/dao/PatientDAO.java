//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Patient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 * DAO class for managing Patient entities. Provides CRUD operations for Patient objects.
 * 
 * @author inazu
 */
public class PatientDAO {
    

    //logger
    private static final Logger LOGGER = Logger.getLogger(PatientDAO.class.getName());

    /**
     * Method to get all Patient entities from the list
     * 
     * @return a list of all Patient entities
     */
    public List<Patient> getAllPatients() {
        return DataRepository.patientList;
    }
    
    
    /**
     * Method to get a Patient by their ID
     * 
     * @param patientId patientId the ID of the patient
     * @return the Patient entity if found
     * @throws WebApplicationException if no Patient is found with the given
     */
    public Patient getPatientById(int patientId) {
          return DataRepository.patientList.stream()
                          .filter(p -> p.getPersonId() == patientId)
                          .findFirst()
                          .orElseThrow(() -> new WebApplicationException("Patient " + patientId +" not found", Response.Status.NOT_FOUND));
    
    }
    

    /**
     * Method to add a new Patient entity to the list
     * 
     * @param patient the Patient entity to add
     * @return the added Patient with an assigned ID and other variables
     * 
     */
    public Patient addPatient(Patient patient) {
        //assign new id to patient
        patient.setPersonId(DataRepository.patientIdCount.incrementAndGet());
        DataRepository.patientList.add(patient);
        return patient;
        
    }
    
    
    /**
     *Method to update an existing Patient entity based on the provided ID
     * 
     * @param patientId An integer value of the ID of the patient to update
     * @param updatedPatient the new data for the Patient 
     * @return the updated Patient entity, or null if not found
     * @throws WebApplicationException if no Patient is found with the given
     */
    public Patient updatePatient(int patientId, Patient updatedPatient) {

        Patient patient = getPatientById(patientId);
        if (patient != null) {
            patient.setName(updatedPatient.getName());
            patient.setEmailAddress(updatedPatient.getEmailAddress());
            patient.setContactNumber(updatedPatient.getContactNumber());
            patient.setAddress(updatedPatient.getAddress());
            patient.setMedicalHistory(updatedPatient.getMedicalHistory());
            patient.setCurrentHealthStatus(updatedPatient.getCurrentHealthStatus());
            return patient;
        } else {
            
            LOGGER.log(Level.SEVERE, "Patient not found for ID: " + patientId);
            throw new WebApplicationException("Patient " + patientId +" not found", Response.Status.NOT_FOUND);
        }

    }
    
    
    /**
     * Method to delete a patient from the list
     * 
     * @param patientId  A integer value of the ID of the patient to delete
     * @throws WebApplicationException if no Patient is found with the given
     */
    public void deletePatient(int patientId) {

        Patient patient = getPatientById(patientId);
        if (patient == null) {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
        DataRepository.patientList.remove(patient);
    }

    /**
     * Method to get a count of patients using the status
     * 
     * @param status  status of patient taken as string
     * @return the status in the form of long as a count
     */
    public long countByHealthStatus(String status) {
        return DataRepository.patientList.stream()
                                      .filter(patient -> status.equalsIgnoreCase(patient.getCurrentHealthStatus()))
                                      .count();
    }
    
    
    
}
