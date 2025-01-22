//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//import 
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.PatientDAO;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Patient;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *RESTful resource class for Patients
 * @author inazu
 */
@Path("/patient")
public class PatientResource {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(PatientResource.class.getName());
    
    //PatientDAO instance
    private PatientDAO patientDao=new PatientDAO();

    /**
     * Retrieves all persons
     * @return  JSON array of Patient entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            return Response.ok(patientDao.getAllPatients()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all patients", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve all patients").build();
        }
    }

    /**
     * Retrieves a specific patient by their ID.
     * @param patientId ID of the patient to retrieve
     * @return JSON representation of the Patient entity
     */
    @GET
    @Path("/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("patientId") int patientId) {
        try {
            Patient patient = patientDao.getPatientById(patientId);
            return Response.ok(patient).build();
        } catch (WebApplicationException e) {
            LOGGER.log(Level.INFO, "Patient not found with ID: " + patientId, e);
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve patient with ID: " + patientId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving patient").build();
        }
    }

    /**
     * Creates a new patient
     * @param patient Patient data from request
     * @return JSON representation of the newly created Patient entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            Patient newPatient = patientDao.addPatient(patient);
            return Response.status(Response.Status.CREATED).entity(newPatient).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create patient", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error creating patient").build();
        }
    }

    
    /**
     * Updates an existing patient
     * 
     * @param patientId ID of the patient to update.
     * @param patient Updated patient data from request
     * @return JSON representation of the updated Patient entity
     */
    @PUT
    @Path("/{patientId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("patientId") int patientId, Patient patient) {
        try {
            Patient updatedPatient = patientDao.updatePatient(patientId, patient);
            return Response.ok(updatedPatient).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update patient", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating patient").build();
        }
    }

    /**
     * Deletes a patient by ID
     * 
     * @param patientId ID of the patient to delete
     * @return  HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{patientId}")
    public Response deletePatient(@PathParam("patientId") int patientId) {
        try {
            patientDao.deletePatient(patientId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete patient with ID: " + patientId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting patient").build();
        }
    }
    
    /**
     * Method to return the count of patients searched based on a status
     * @param status the type of status searched in String
     * @return return the count or if not found give a internal server error
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientCountByStatus(@QueryParam("status") String status) {
        try {
            long count = patientDao.countByHealthStatus(status);
            return Response.ok(count).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to count patients by health status", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to count patients by health status").build();
        }
    }
    
}
