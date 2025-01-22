//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.resources;

//import libraries

import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao.PersonDAO;
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Person;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * RESTful resource class for managing Person entities.
 * 
 * @author inazu
 */
@Path("/person")
public class PersonResource {
    
    // object of PersonDAO
    private PersonDAO personDao = new PersonDAO();

    //logger
    private static final Logger LOGGER = Logger.getLogger(PersonDAO.class.getName());
    
    
    /**
     * Retrieves all persons
     * @return JSON array of Person entities
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        try {
            return Response.ok(personDao.getAllPersons()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to retrieve all persons", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to retrieve all persons").build();
       }
        
    }

    
    /**
     * Retrieves a specific person by their ID.
     * 
     * @param personId ID of the person to retrieve
     * @return JSON representation of the Person entity
     */
    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("personId") int personId) {
        try {
            Person person = personDao.getPersonById(personId);
            return Response.ok(person).build();
        } catch (WebApplicationException e) {
            LOGGER.log(Level.INFO, "Person not found with ID: " + personId, e);
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build(); 
        } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Error retrieving person with ID: " + personId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving person").build();
        }
    }

    
    /**
     * Creates a new person
     * 
     * @param person Person data from request
     * @return JSON representation of the newly created Person entity
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        try {
            Person newPerson = personDao.addPerson(person);
            return Response.status(Response.Status.CREATED).entity(newPerson).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to create person", e); 
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating person").build();
        }
    }

    
    /**
     * Updates an existing person
     * 
     * @param personId ID of the person to update.
     * @param person Updated person data from request
     * @return JSON representation of the updated Person entity
     */
    @PUT
    @Path("/{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") int personId, Person person) {
        try {
        Person updatedPerson = personDao.updatePerson(personId, person);
            if (updatedPerson != null) {
                return Response.ok(updatedPerson).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Person not found").build();
            }
        } catch (WebApplicationException e) {
            LOGGER.log(Level.INFO, "Not found error when updating person with ID: " + personId, e);
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update person with ID: " + personId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating person").build();
        }
    }

    
    /**
     * Deletes a person by ID
     * 
     * @param personId personId ID of the person to delete
     * @return HTTP Status 204 (No Content) if deleted successfully
     */
    @DELETE
    @Path("/{personId}")
    public Response deletePerson(@PathParam("personId") int personId) {
        try {
            boolean deleted = personDao.deletePerson(personId);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            LOGGER.log(Level.INFO, "Not found error when deleting person with ID: " + personId, e);
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to delete person with ID: " + personId, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting person").build();
        }
    }
    
    
}
