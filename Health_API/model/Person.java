//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;
/**
 * Person class gives the base class for an individual
 * Patient class and Doctor class are built on this class
 * 
 * 
 * @author inazu
 */
 
public class Person {
    
    //name of the person
    private String name;
    
    //id of the person
    private int personId;
    
    //contact email of the person
    private String emailAddress;
    
    //contact number of the person
    private String contactNumber;
    
    //address of the person
    private String address;

    //default constructor
    public Person() {
        
    }
    
    /**
     * 
     * @param personId id relevant to a person  as int
     * @param name name of the person as String
     * @param emailAdress email of person  as String
     * @param contactNumber contact information relevant to a person  as String
     * @param address address of person as String
     */
    public Person(int personId, String name,String emailAdress, String contactNumber, String address) {
        //parameterised constructor
        this.name = name;
        this.personId = personId;
        this.emailAddress=emailAdress;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    
    //name getter
    public String getName() {
        return name;
    }

    //id getter
    public int getPersonId() {
        return personId;
    }

    //person contact email address getter
    public String getEmailAddress() {
        return emailAddress;
    }
    //contact number getter
    public String getContactNumber() {
        return contactNumber;
    }
    //address getter
    public String getAddress() {
        return address;
    }

    //name setter
    public void setName(String name) {
        this.name = name;
    }

    //id setter
    public void setPersonId(int personId) {
        this.personId = personId;
    }

    //contact email address setter
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    //contact number setter
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    //address setter
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
}
