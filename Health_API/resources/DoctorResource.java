//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.DoctorDAO;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Doctor;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *RESTful resource class for Doctors
 * @author inazu
 */
@Path("/doctor")
public class DoctorResource {
    
    //logger
    private static final Logger LOGGER = Logger.getLogger(DoctorResource.class.getName());
    
    //object of dao of doctor
    private DoctorDAO daoDoctor=new DoctorDAO();
    
    
    /**
     * Retrieves all doctors
     * @return JSON array of Doctor entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            List<Doctor> doctors = daoDoctor.getAllDoctors();
            return Response.ok(doctors).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all doctors", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve all doctors").build();
        }
    }

    /**
     * Retrieves a specific Doctor by their ID.
     * @param doctorId  ID of the Doctor to retrieve
     * @return JSON representation of the Doctor entity
     */
    @GET
    @Path("/{doctorid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("doctorid") int doctorId) {
        try {
            Doctor doctor = daoDoctor.getDoctorById(doctorId);
            return Response.ok(doctor).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve doctr with ID: " + doctorId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving doctor").build();
        }
    }

    /**
     * Creates a new patient Doctor
     * @param doctor Doctor data from request
     * @return JSON representation of the newly created Doctor entity
     */ 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            Doctor newDoctor = daoDoctor.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(newDoctor).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding doctor", e);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    
    /**
     *  Updates an existing Doctor
     * @param doctorid ID of the Doctor to update.
     * @param doctor  Updated Doctor data from request
     * @return JSON representation of the updated Doctor entity
     */
    @PUT
    @Path("/{doctorid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("doctorid") int doctorid, Doctor doctor) {
        try {
            Doctor updatedDoctor = daoDoctor.updateDoctor(doctorid, doctor);
            return Response.ok(updatedDoctor).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update doctor", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating doctor").build();
        }
    }

   
    /**
     *Deletes a Doctor by ID
     * 
     * @param doctorid ID of the Doctor to delete
     * @return  HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{doctorid}")
    public Response deleteDoctor(@PathParam("doctorid") int doctorid) {
        try {
            daoDoctor.deleteDoctor(doctorid);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete doctor with ID: " + doctorid, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting doctor").build();
        }
    }
    
    /**
     * Retrieve doctors based on specialization
     * @param specialization input of specialization
     * @return list of Doctors for the specialization
     */
    @GET
    @Path("/by-specialization/{specialization}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorsBySpecialization(@PathParam("specialization") String specialization) {
        try {
            List<Doctor> doctors = daoDoctor.getDoctorsBySpecialization(specialization);
            if (doctors.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No doctors found with specialization: " + specialization).build();
            }
            return Response.ok(doctors).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving doctors by specialization", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving doctors").build();
        }
    }
    
    /**
     * Get doctor details for searched name
     * @param name name searched for
     * @return list of Doctors for the name
     */
    @GET
    @Path("/search-by-name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchDoctorsByName(@QueryParam("name") String name) {
        try {
            List<Doctor> doctors = daoDoctor.searchDoctorsByName(name);
            if (doctors.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No doctors found with name containing: " + name).build();
            }
            return Response.ok(doctors).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to search doctors by name", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error searching doctors by name").build();
        }
    }
    
    /**
     * Get count of doctors for the searched specialization
     * @param specialization specialization searched for
     * @return list of Doctors for the specialization
     */
    @GET
    @Path("/count-by-specialization/{specialization}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countDoctorsBySpecialization(@PathParam("specialization") String specialization) {
        try {
            long count = daoDoctor.countDoctorsBySpecialization(specialization);
            return Response.ok(count).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to count doctors by specialization", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to count doctors").build();
        }
    }
    
}
