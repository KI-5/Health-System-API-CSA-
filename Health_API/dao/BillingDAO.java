//package
package com.healthcarecoursework._ishanikanapathipillai_5cosc022w.dao;

//imports
import com.healthcarecoursework._ishanikanapathipillai_5cosc022w.model.Billing;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *DAO class for managing Billing entities. Provides CRUD operations for Billing objects.
 * @author inazu
 */
public class BillingDAO {
   
    //logger
    private static final Logger LOGGER = Logger.getLogger(DoctorDAO.class.getName());
    

    /**
     *Method to get all Billing entities from the list
     * @return a list of all Billing entities
     */
    public List<Billing> getAllBillings() {
        return new ArrayList<>(DataRepository.billingList);
    }

   /**
     * Method to get a Billing by their ID 
     * @param billingId  the ID of the Billing
     * @return the Billing entity if found
     * @throws WebApplicationException if no Billing is found with the given
     */
    public Billing getBillingById(int billingId) {
        return DataRepository.billingList.stream()
                       .filter(billing -> billing.getBillingId() == billingId)
                       .findFirst()
                       .orElseThrow(() -> new WebApplicationException("Billing not in records", Response.Status.NOT_FOUND));
    }

    /**
     * Method to add a new Billing entity to the list
     * @param billing the Billing entity to add
     * @return the added Billing with an assigned ID and other variables
     */
    public Billing addBilling(Billing billing) {
        billing.setBillingId(DataRepository.billingIdCount.incrementAndGet());
        DataRepository.billingList.add(billing);
        return billing;
    }

    /**
     * Method to update an existing Billing entity based on the provided ID
     * @param billingId An integer value of the ID of the Billing to update
     * @param updatedBilling  the new data for the Billing 
     * @return the updated Billing entity, or null if not found
     * @throws WebApplicationException if no Billing is found with the given
     */
    public Billing updateBilling(int billingId, Billing updatedBilling) {
        Billing billing = getBillingById(billingId);
        if (billing != null) {
            billing.setInvoicePrice(updatedBilling.getInvoicePrice());
            billing.setStatus(updatedBilling.getStatus());
            billing.setPaymentDueDate(updatedBilling.getPaymentDueDate());
            billing.setDateOfPayment(updatedBilling.getDateOfPayment());
            billing.setPaymentMethod(updatedBilling.getPaymentMethod());
            billing.setOutstandingBalance(updatedBilling.getOutstandingBalance());
            billing.setDescription(updatedBilling.getDescription());
            return billing;
        }else{
            LOGGER.log(Level.SEVERE, "Billing not found for ID: " + billingId);
            throw new WebApplicationException("Billing " +billingId + " not found", Response.Status.NOT_FOUND);
            
        }
        
    }

    /**
     * Method to delete a Billing from the list
     * @param billingId A integer value of the ID of the Billing to delete
     *  @throws WebApplicationException if no Billing is found with the given
     */
    public void deleteBilling(int billingId) {
        Billing billing = getBillingById(billingId);
        if (billing != null) {
            DataRepository.billingList.remove(billing);
        } else {
            LOGGER.log(Level.SEVERE, "Billing not found for ID: " + billingId);
            throw new WebApplicationException("Billing not in records", Response.Status.NOT_FOUND);
        }
    } 
    
//    public double calculateTotalOutstandingBalances() {
//    return DataRepository.billingList.stream()
//                                     .filter(billing -> "Unpaid".equals(billing.getStatus()))
//                                     .mapToDouble(Billing::getOutstandingBalance)
//                                     .sum();
//    }
}
