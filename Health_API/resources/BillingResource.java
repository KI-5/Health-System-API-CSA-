//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Billing;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.BillingDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *RESTful resource class for Billing
 * @author inazu
 */
@Path("/billing")
public class BillingResource {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(BillingResource.class.getName());
    
    //billing dao objcet
    private BillingDAO daoBilling = new BillingDAO();

    
    /**
     *Retrieves all Billing
     * @return JSON array of Billing entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = daoBilling.getAllBillings();
            return Response.ok(billings).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all billings", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Retrieves a specific Billing by their ID.
     * @param billingId ID of the Billing to retrieve
     * @return JSON representation of the Billing entity
     */
    @GET
    @Path("/{billingid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("billingid") int billingId) {
        try {
            Billing billing = daoBilling.getBillingById(billingId);
            return Response.ok(billing).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve billing with ID: " + billingId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    /**
     * Creates a new patient Billing
     * @param billing Billing data from request
     * @return JSON representation of the newly created Billing entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            Billing newBilling = daoBilling.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(newBilling).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create billing", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }}

    /**
     * Updates an existing Billing
     * @param billingId  ID of the Billing to update.
     * @param billing Updated Billing data from request
     * @return JSON representation of the updated Billing entity
     */
    @PUT
    @Path("/{billingid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("billingid") int billingId, Billing billing) {
        try {
            Billing updatedBilling = daoBilling.updateBilling(billingId, billing);
            return Response.ok(updatedBilling).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update billing", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

   
    /**
     * Deletes a Billing by ID
     * @param billingId ID of the Billing to delete
     * @return  HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{billingid}")
    public Response deleteBilling(@PathParam("billingid") int billingId) {
        try {
            daoBilling.deleteBilling(billingId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete billing with ID: " + billingId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
//    @GET
//    @Path("/total-outstanding")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getTotalOutstandingBalances() {
//        try {
//            double totalOutstanding = daoBilling.calculateTotalOutstandingBalances();
//            if(totalOutstanding > 0) {
//                return Response.ok(totalOutstanding).build();
//            } else {
//                return Response.noContent().build();
//            }
//        } catch (Exception e) {
//            LOGGER.log(Level.SEVERE, "Failed to calculate total outstanding balances", e);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to calculate total balances: " + e.getMessage()).build();
//        }
//    }

}
