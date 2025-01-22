//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Prescription;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *DAO class for managing Prescription entities. Provides CRUD operations for Prescription objects.
 * @author inazu
 */
public class PrescriptionDAO {
    
 //logger
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());
    

    /**
     * Method to get all Prescription entities from the list
     * @return a list of all Prescription entities
     */
    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(DataRepository.prescriptionList);
    }

   
    /**
     * Method to get a Prescription by their ID 
     * @param id id the ID of the Prescription
     * @return the Prescription entity if found
     * @throws WebApplicationException if no Prescription is found with the given
     */
    public Prescription getPrescriptionById(int id) {
        return DataRepository.prescriptionList.stream()
            .filter(prescription -> prescription.getPrescriptionId()== id)
            .findFirst()
            .orElseThrow(() -> new WebApplicationException("Prescription with id" +  id+ " not found", Response.Status.NOT_FOUND));
    }

   
    /**
     *  Method to add a new Prescription entity to the list
     * @param prescription the Prescription entity to add
     * @return the added Prescription with an assigned ID and other variables
     */ 
    public Prescription addPrescription(Prescription prescription) {
        prescription.setPrescriptionId(DataRepository.prescriptionIdCount.incrementAndGet());
        DataRepository.prescriptionList.add(prescription);
        return prescription;
    }

    /**
     * Method to update an existing Prescription entity based on the provided ID
     * @param prescriptionId  An integer value of the ID of the Prescription to update
     * @param updatedPrescription the new data for the Prescription 
     * @return the updated Prescription entity, or null if not found
     * @throws WebApplicationException if no Prescription is found with the given
     */
    public Prescription updatePrescription(int prescriptionId, Prescription updatedPrescription) {
        Prescription prescription = getPrescriptionById(prescriptionId);
        if (prescription != null) {
            prescription.setPrescribedMedication(updatedPrescription.getPrescribedMedication());
            prescription.setRecommendedDosage(updatedPrescription.getRecommendedDosage());
            prescription.setDoctorsInstructions(updatedPrescription.getDoctorsInstructions());
            prescription.setMedicationDurationDays(updatedPrescription.getMedicationDurationDays());
            prescription.setDoctorInCharge(updatedPrescription.getDoctorInCharge());
            prescription.setPatientInConcern(updatedPrescription.getPatientInConcern());
            return prescription;
        } else {
            LOGGER.log(Level.SEVERE, "Prescription not found for ID: " + prescriptionId);
            throw new WebApplicationException("Prescription with ID:" + prescriptionId+ " not found", Response.Status.NOT_FOUND);
        }
    }
    
   
    /**
     * Method to delete a Prescription from the list
     * @param prescriptionId  A integer value of the ID of the Prescription to delete
     * @throws WebApplicationException if no Prescription is found with the given
     */
    public void deletePrescription(int prescriptionId) {
        Prescription prescription= getPrescriptionById(prescriptionId);
        if (prescription!= null){
            DataRepository.prescriptionList.remove(prescription);
        }
        LOGGER.log(Level.SEVERE, "Prescription not found for ID: " + prescriptionId);    
        throw new WebApplicationException("Prescription with ID:" + prescriptionId+ " not found", Response.Status.NOT_FOUND);
    }
    
    
    /**
     * Method to get the count based on the medication
     * @param medication  medication given
     * @return gives the count
     */
    public long countByMedication(String medication) {
    return DataRepository.prescriptionList.stream()
        .filter(prescription -> prescription.getPrescribedMedication().equalsIgnoreCase(medication))
        .count();
    }
}
