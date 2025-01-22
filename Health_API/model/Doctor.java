//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model;

//imports


//Doctor class extends the Person class

/**
 * Doctor class includes data about the doctor
 * 
 * @author inazu
 */
public class Doctor extends Person{
    
    //describe specialisation
    private String doctorSpecialisation;
    
     //default constructor
    public Doctor() {
        super();
    }
    
    //parameterised constructor

    /**
     *
     * @param doctorId id of doctor as integer
     * @param name name of doctor as a String
     * @param doctorEmailAddress mail address of doctor as String
     * @param doctorContact contact of doctor as String
     * @param address address of doctor of doctor as String
     * @param doctorSpecialisation doctors specialisation of doctor as String
     */
    public Doctor(int doctorId, String name, String doctorEmailAddress, String doctorContact, String address, String doctorSpecialisation) {
        super( doctorId,name,doctorEmailAddress, doctorContact, address);
        this.doctorSpecialisation = doctorSpecialisation;
    }

    //doctor specialisation getter
    public String getSpecialization() {
        return doctorSpecialisation;
    }

    //doctor specialisatin setter
    public void setSpecialization(String doctorSpecialisation) {
        this.doctorSpecialisation = doctorSpecialisation;
    }

    
}