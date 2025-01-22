//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//import libraries
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Person;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * DAO class for managing Person entities. Provides CRUD operations for Person objects.
 * 
 * @author inazu
 */
public class PersonDAO {
 
    //logger
    private static final Logger LOGGER = Logger.getLogger(PersonDAO.class.getName());
    
    /**
     * Method to get all Person entities from the list
     * 
     * @return a list of all Person entities
     */
    public List<Person> getAllPersons() {
        return new ArrayList<>(DataRepository.personList); 
    }


    /**
     * Method to get a Person by their ID
     * 
     * @param personId personId the ID of the person
     * @return the Person entity if found
     * @throws WebApplicationException if no Person is found with the given ID
     */
    public Person getPersonById(int personId) {
        return DataRepository.personList.stream()
                .filter(person -> person.getPersonId() == personId)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Person with id " + personId+" not in records", Response.Status.NOT_FOUND));
    }

    

    /**
     * Method to add a new Person entity to the list
     * 
     * @param person the Person entity to add
     * @return the added Person with an assigned ID and other variables
     */
    public Person addPerson(Person person) {
        // Assign a new ID to the person
        int newPersonId = DataRepository.personIdCount.incrementAndGet();
        person.setPersonId(newPersonId);
        DataRepository.personList.add(person);
        return person;
    }

    
    
    /**
     * Method to update an existing Person entity based on the provided ID
     * 
     * @param personId An integer value of the ID of the person to update
     * @param newPersonData the new data for the Person 
     * @return the updated Person entity, or null if not found
     * @throws WebApplicationException if no Person is found with the given ID
     */
    public Person updatePerson(int personId, Person newPersonData) {
        Person person = getPersonById(personId);
        if (person != null) {
            person.setName(newPersonData.getName());
            person.setContactNumber(newPersonData.getContactNumber());
            person.setEmailAddress(newPersonData.getEmailAddress());
            person.setAddress(newPersonData.getAddress());
            return person;
        }
        else{
            LOGGER.log(Level.WARNING, "Person with ID: " + personId + " not found for update.");
            throw new WebApplicationException("Person " +  personId+" not found", Response.Status.NOT_FOUND);
        }
    }

    
    
    /**
     * Method to delete a person from the list
     * 
     * @param personId A integer value of the ID of the person to delete
     * @throws WebApplicationException if no Person is found with the given ID
     */
    public boolean deletePerson(int personId) {
        Person person = getPersonById(personId);
        if (person != null) {
            DataRepository.personList.remove(person);
            return true;
        } else {
            LOGGER.log(Level.INFO, "Person not found for ID: " + personId);
            throw new WebApplicationException("Person " +  personId+" not found", Response.Status.NOT_FOUND);
    }
    }
    
}
