//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.MedicalRecordDAO;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.MedicalRecord;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *RESTful resource class for MedicalRecord
 * 
 * @author inazu
 */
@Path("/record")
public class MedicalRecordResource {
    //resource class
    private MedicalRecordDAO daoMedicalRecord = new MedicalRecordDAO();

    //logger
    private static final Logger LOGGER = Logger.getLogger(MedicalRecordResource.class.getName());

    
    /**
     * Retrieves all MedicalRecord
     * @return JSON array of MedicalRecord entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            return Response.ok(daoMedicalRecord.getAllMedicalRecords()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all records", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve all records").build();
        }
    }

    
    /**
     * Retrieves a specific MedicalRecord by their ID.
     * @param recordId ID of the MedicalRecord to retrieve
     * @return JSON representation of the MedicalRecord entity
     */
    @GET
    @Path("/{recordid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("recordid") int recordId) {
        try {
            MedicalRecord record = daoMedicalRecord.getMedicalRecordById(recordId);
            return Response.ok(record).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve record with ID: " + recordId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving record").build();
        }
    }

    
    /**
     * Creates a new patient MedicalRecord
     * @param medicalRecord MedicalRecord data from request
     * @return JSON representation of the newly created MedicalRecord entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            MedicalRecord newRecord  = daoMedicalRecord.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity(newRecord).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create record", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error creating record").build();
        }
    }

    /**
     * Updates an existing MedicalRecord
     * @param recordId ID of the MedicalRecord to update.
     * @param medicalRecord Updated MedicalRecord data from request
     * @return JSON representation of the updated MedicalRecord entity
     */
    @PUT
    @Path("/{recordid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("recordid") int recordId, MedicalRecord medicalRecord) {
        try {
            MedicalRecord updatedRecord = daoMedicalRecord.updateMedicalRecord(recordId, medicalRecord);
            return Response.ok(updatedRecord).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating medical record", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error occurred").build();
        }
    }

    
    /**
     * Deletes a MedicalRecord by ID
     * @param recordId ID of the MedicalRecord to delete
     * @return HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{recordid}")
    public Response deleteMedicalRecord(@PathParam("recordid") int recordId) {
        try {
            daoMedicalRecord.deleteMedicalRecord(recordId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete medical record with ID: " + recordId, e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting medical record").build();
        }   
    }
    
    /**
     * Method to get list of records of a diagnoses type
     * @param diagnosis the diagnosis input
     * @return list of medical records relevant
     */
    @GET
    @Path("/diagnosis/{diagnosis}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecordsByDiagnosis(@PathParam("diagnosis") String diagnosis) {
        try {
            List<MedicalRecord> records = daoMedicalRecord.getRecordsByDiagnosis(diagnosis);
            if (records.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No records found for diagnosis: " + diagnosis).build();
            }
            return Response.ok(records).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve records by diagnosis", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve records").build();
        }
    }
    
}
