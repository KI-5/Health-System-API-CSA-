//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Prescription;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.PrescriptionDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *RESTful resource class for Prescription
 * @author inazu
 */
@Path("/prescription")
public class PrescriptionResource {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(PrescriptionResource.class.getName());
    
    //dao object
    private PrescriptionDAO daoPrescription = new PrescriptionDAO();

   
    /**
     * Retrieves all Prescription
     * @return JSON array of Prescription entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            return Response.ok(daoPrescription.getAllPrescriptions()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all prescriptions", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Retrieves a specific Prescription by their ID.
     * @param prescriptionId ID of the Prescription to retrieve
     * @return JSON representation of the Prescription entity
     */
    @GET
    @Path("/{prescriptionid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("prescriptionid") int prescriptionId) {
        try {
            Prescription prescription = daoPrescription.getPrescriptionById(prescriptionId);
            return Response.ok(prescription).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve prescription with ID: " + prescriptionId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving prescription").build();
        }
    }

    /**
     * Creates a new patient Prescription
     * @param prescription Prescription data from request
     * @return JSON representation of the newly created Prescription entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            Prescription newPrescription = daoPrescription.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity(newPrescription).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to add prescription" , e);
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

   
    /**
     * Updates an existing Prescription
     * @param prescriptionId ID of the Prescription to update.
     * @param prescription Updated Prescription data from request
     * @return JSON representation of the updated Prescription entity
     */
    @PUT
    @Path("/{prescriptionid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("prescriptionid") int prescriptionId, Prescription prescription) {
        try {
            Prescription updatedPrescription = daoPrescription.updatePrescription(prescriptionId, prescription);
            if (updatedPrescription != null) {
                return Response.ok(updatedPrescription).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (WebApplicationException e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve prescription with id " + prescriptionId , e);
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    /**
     * Deletes a Prescription by ID
     * @param prescriptionId ID of the Prescription to delete
     * @return HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{prescriptionid}")
    public Response deletePrescription(@PathParam("prescriptionid") int prescriptionId) {
        try {
            daoPrescription.deletePrescription(prescriptionId);
            LOGGER.log(Level.INFO, "Testing 204 no content. Deletion of prescription is successful " + prescriptionId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            LOGGER.log(Level.SEVERE, "Prescription doesn't exist. Throws 404 not found " + prescriptionId , e);
            return Response.status(e.getResponse().getStatus()).entity("Prescription not found").build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unable ot delete. 500 error " + prescriptionId , e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete prescription: " + e.getMessage()).build();
        }
    } 
    
    
    /**
     * method to get prescription count based on the medication 
     * @param medication medication defined
     * @return HTTP Status 204 (No Content) if deleted successfully
     */
    @GET
    @Path("/count/medication")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionCountByMedication(@QueryParam("medication") String medication) {
        try {
            long count = daoPrescription.countByMedication(medication);
            if(count > 0) {
                return Response.ok(count).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to count prescriptions by medication", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to count prescriptions: " + e.getMessage()).build();
        }
    }
    
}
