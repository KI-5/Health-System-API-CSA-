//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.AppointmentDAO;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Appointment;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



/**
 * RESTful resource class for Appointment
 * 
 * @author inazu
 */
@Path("/appointment")
public class AppointmentResource {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(AppointmentResource.class.getName());
   
    //dao appointment object
    private AppointmentDAO daoAppointment = new AppointmentDAO();

  
    /**
     * Retrieves all Appointments
     * @return JSON array of Appointment entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            return Response.ok(daoAppointment.getAllAppointments()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all appointments", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve all appointments").build();
        }
    }

    
    /**
     * Retrieves a specific Appointment by their ID.
     * @param appointmentId ID of the Appointment to retrieve
     * @return JSON representation of the Appointment entity
     */
    @GET
    @Path("/{appointmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("appointmentId") int appointmentId) {
        try {
            Appointment appointment = daoAppointment.getAppointmentById(appointmentId);
            return Response.ok(appointment).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve appointment with ID: " + appointmentId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving appointment").build();
        }
    }

    
    /**
     * Creates a new patient Appointment
     * @param appointment Appointment data from request
     * @return JSON representation of the newly created Appointment entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            Appointment newAppointment = daoAppointment.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(newAppointment).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding appointment", e);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error creating appointment").build();
        }
    }
    
    /**
     * Updates an existing Appointment
     * @param id ID of the Appointment to update.
     * @param appointment Updated Appointment data from request
     * @return JSON representation of the updated Appointment entity
     */
    @PUT
    @Path("/{appointmentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("appointmentId") int id, Appointment appointment) {
        try {
            Appointment updatedAppointment = daoAppointment.updateAppointment(id, appointment);
            return Response.ok(updatedAppointment).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    
    /**
     *Deletes a Appointment by ID
     * @param appointmentId ID of the Appointment to delete
     * @return HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{appointmentId}")
    public Response deleteAppointment(@PathParam("appointmentId") int appointmentId) {
        try {
            daoAppointment.deleteAppointment(appointmentId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete appointment with ID: " + appointmentId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting appointment").build();
        }
    } 
    
    /**
     * Method to get the appointment related to the given doctor id
     * @param doctorId the input given which gives the appointment details
     * @return
     */
    @GET
    @Path("/doctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsByDoctor(@PathParam("doctorId") int doctorId) {
        try {
            List<Appointment> appointments = daoAppointment.getAppointmentsByDoctorId(doctorId);
            if (appointments.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No appointments found for doctor ID: " + doctorId).build();
            }
            return Response.ok(appointments).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve appointments for doctor ID: " + doctorId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving appointments for doctor").build();
        }
    }

}
