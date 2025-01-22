//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports 
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.MedicalRecord;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *DAO class for managing MedicalRecord entities. Provides CRUD operations for MedicalRecord objects.
 * @author inazu
 */
public class MedicalRecordDAO {

    //logger
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());
   
   
    /**
     * Method to get all MedicalRecord entities from the list
     * @return a list of all MedicalRecord entities
     */
    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(DataRepository.medicalRecordList);
    }

    
    /**
     * Method to get a MedicalRecord by their ID 
     * @param recordId recordId the ID of the MedicalRecord
     * @return the MedicalRecord entity if found
     * @throws WebApplicationException if no MedicalRecord is found with the given
     */
    public MedicalRecord getMedicalRecordById(int recordId) {
        return DataRepository.medicalRecordList.stream()
                .filter(record -> record.getRecordId()== recordId)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Medical record with id " +  recordId+ " not found", Response.Status.NOT_FOUND));
    }

    
    /**
     * Method to add a new MedicalRecord entity to the list
     * @param record the MedicalRecord entity to add
     * @return the added MedicalRecord with an assigned ID and other variables
     */
    public MedicalRecord addMedicalRecord(MedicalRecord record) {
        record.setRecordId(DataRepository.medicalRecordIdCount.incrementAndGet());
        DataRepository.medicalRecordList.add(record);
        return record;
    }

   
    /**
     * Method to update an existing MedicalRecord entity based on the provided ID
     * @param recordId An integer value of the ID of the MedicalRecord to update
     * @param updatedRecord the new data for the MedicalRecord 
     * @return the updated MedicalRecord entity, or null if not found
     * @throws WebApplicationException if no MedicalRecord is found with the given
     */
    public MedicalRecord updateMedicalRecord(int recordId, MedicalRecord updatedRecord) {
        MedicalRecord record = getMedicalRecordById(recordId);
        if (record != null) {
            record.setDiagnosesList(updatedRecord.getDiagnosesList());
            record.setTreatmentList(updatedRecord.getTreatmentList());
            
            return record;
        } else {
            LOGGER.log(Level.SEVERE, "Record not found for ID: " + recordId);
            throw new WebApplicationException("Medical record " +  recordId+" not found", Response.Status.NOT_FOUND);
        }
    }

    
    /**
     * Method to delete a MedicalRecord from the list
     * @param recordId A integer value of the ID of the MedicalRecord to delete
     * @throws WebApplicationException if no MedicalRecord is found with the given
     */
    public void deleteMedicalRecord(int recordId) {
        MedicalRecord record = getMedicalRecordById(recordId);
        if (record != null) {
            DataRepository.medicalRecordList.remove(record);
        } else {
            LOGGER.log(Level.SEVERE, "Record not found for ID: " + recordId);
            throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
        }
    } 
    
    /**
     * Method to get record lists using an input diagnosis
     * @param diagnosis diagnosis given as input 
     * @return a list of medical records related to the diagnosis
     */
    public List<MedicalRecord> getRecordsByDiagnosis(String diagnosis) {
    return DataRepository.medicalRecordList.stream()
            .filter(record -> record.getDiagnosesList().contains(diagnosis))
            .collect(Collectors.toList());
}
    
}
