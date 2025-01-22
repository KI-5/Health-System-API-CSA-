//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Doctor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * DAO class for managing Doctor entities. Provides CRUD operations for Doctor objects.
 * @author inazu
 */
public class DoctorDAO {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());
    
    /**
     * Method to get all Doctor entities from the list
     * 
     * @return a list of all Doctor entities
     */
    public List<Doctor> getAllDoctors() {
        return DataRepository.doctorList;
    }
    
    /**
     * Method to get a Doctor by their ID 
     * 
     * @param doctorId doctorId the ID of the doctor
     * @return the Doctor entity if found
     * @throws WebApplicationException if no Doctor is found with the given
     */
    public Doctor getDoctorById(int doctorId) {
        return DataRepository.doctorList.stream()
                         .filter(d -> d.getPersonId() == doctorId)
                         .findFirst()
                         .orElseThrow(() -> new WebApplicationException("Doctor " + doctorId +" not in records", Response.Status.NOT_FOUND));
    }

    
    /**
     * Method to add a new Doctor entity to the list
     * 
     * @param doctor the Doctor entity to add
     * @return the added Doctor with an assigned ID and other variables
     */
     public Doctor addDoctor(Doctor doctor) {
        doctor.setPersonId(DataRepository.doctorIdCount.incrementAndGet());
//        updatePatientDetails(doctor);
        DataRepository.doctorList.add(doctor);
        return doctor;
    }

    /**
     * Method to update an existing Doctor entity based on the provided ID
     * 
     * @param doctorId An integer value of the ID of the Doctor to update
     * @param updatedDoctor  the new data for the Doctor 
     * @return the updated Doctor entity, or null if not found
     * @throws WebApplicationException if no Doctor is found with the given
     */
    public Doctor updateDoctor(int doctorId, Doctor updatedDoctor) {
        
        Doctor doctor = getDoctorById(doctorId);
        if (doctor != null) {
            doctor.setName(updatedDoctor.getName());
            doctor.setEmailAddress(updatedDoctor.getEmailAddress());
            doctor.setAddress(updatedDoctor.getAddress());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
//            updatePatientDetails(doctor);
            return doctor;
        } else {
            LOGGER.log(Level.SEVERE, "Doctor not found for ID: " + doctorId);
            throw new WebApplicationException("Doctor " +doctorId+" not found", Response.Status.NOT_FOUND);
        }
    }

    
    /**
     * Method to delete a Doctor from the list
     * @param doctorId  A integer value of the ID of the Doctor to delete
     * @throws WebApplicationException if no Doctor is found with the given
     */
    public void deleteDoctor(int doctorId) {
        
        Doctor doctor = getDoctorById(doctorId);
        if (doctor != null) {
            DataRepository.doctorList.remove(doctor);
        } else {
            LOGGER.log(Level.SEVERE, "Doctor not found for deletion ID: " + doctorId);
            throw new WebApplicationException("Doctor with ID " + doctorId + " not found", Response.Status.NOT_FOUND);
        }
    }

    /**
     * Method to get doctor by specialisation
     * @param specialization specialisation given as input
     * @return Doctor list with all details
     */
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
    return DataRepository.doctorList.stream()
            .filter(doctor -> specialization.equalsIgnoreCase(doctor.getSpecialization()))
            .collect(Collectors.toList());
    }
    
    /**
     * Method to search for doctors by their name
     * @param name string of the name as input
     * @return Doctor list with all details
     */
    public List<Doctor> searchDoctorsByName(String name) {
    return DataRepository.doctorList.stream()
            .filter(doctor -> doctor.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    }
    
    /**
     * Method to get count of doctors based on specialization searched
     * @param specialization specialisation given as input
     * @return Doctor list with all details
     */
    public long countDoctorsBySpecialization(String specialization) {
    return DataRepository.doctorList.stream()
            .filter(doctor -> specialization.equalsIgnoreCase(doctor.getSpecialization()))
            .count();
    }
    
    
}