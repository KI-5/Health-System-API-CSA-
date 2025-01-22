//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 * DAO class for managing Appointment entities. Provides CRUD operations for Appointment objects.
 * @author inazu
 */
public class AppointmentDAO {
  
    //logger
    private static final Logger LOGGER = Logger.getLogger(AppointmentDAO.class.getName());

    /**
     *Method to get all Appointment entities from the list
     * @return a list of all Appointment entities
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(DataRepository.appointmentList);
    }
    
    
    /**
     *Method to get a Appointment by their ID 
     * @param appoinmentId appoinmentId the ID of the Appointment
     * @return the Appointment entity if found
     * @throws WebApplicationException if no Appointment is found with the given
     */
    public Appointment getAppointmentById(int appoinmentId) {
        return DataRepository.appointmentList.stream()
                .filter(appointment -> appointment.getAppointmentId() == appoinmentId)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Appointment " + appoinmentId +" not found", Response.Status.NOT_FOUND));
    }

    /**
     * Method to add a new Appointment entity to the list
     * @param appointment the Appointment entity to add
     * @return the added Appointment with an assigned ID and other variables
     */
    public Appointment addAppointment(Appointment appointment) {
        appointment.setAppointmentId(DataRepository.appointmentIdCount.incrementAndGet());
        DataRepository.appointmentList.add(appointment);
        return appointment;
    }

    
    /**
     * Method to update an existing Appointment entity based on the provided ID
     * @param appointmentId  An integer value of the ID of the Appointment to update
     * @param updatedAppointment the new data for the Appointment 
     * @return the updated Appointment entity, or null if not found
     * @throws WebApplicationException if no Appointment is found with the given
     */
    public Appointment updateAppointment(int appointmentId, Appointment updatedAppointment) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment != null) {
            appointment.setDate(updatedAppointment.getDate());
            appointment.setTime(updatedAppointment.getTime());
            appointment.setDoctor(updatedAppointment.getDoctor());
            appointment.setPatient(updatedAppointment.getPatient());
            return appointment;
        } else {
            LOGGER.log(Level.SEVERE, "Appointment not found for ID: " + appointmentId);
            throw new WebApplicationException("Appointment " + appointmentId + " not found", Response.Status.NOT_FOUND);
        }
    }

    /**
     * Method to delete a Appointment from the list
     * @param appointmentId  A integer value of the ID of the Appointment to delete
     * @throws WebApplicationException if no Appointment is found with the given
     */
    public void deleteAppointment(int appointmentId) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment != null) {
            DataRepository.appointmentList.remove(appointment);
        } else {
            LOGGER.log(Level.SEVERE, "Appointment not found for ID: " + appointmentId);
            throw new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND);
        }
    }
    
    /**
     *Method to get appointment based on the doctor id given
     * @param doctorId id of doctor given as input
     * @return returns the list of appointment related to the doctor
     */
    public List<Appointment> getAppointmentsByDoctorId(int doctorId) {
    return DataRepository.appointmentList.stream()
                 .filter(appointment -> appointment.getDoctor().getPersonId() == doctorId)
                 .collect(Collectors.toList());
    }
    
    
}
