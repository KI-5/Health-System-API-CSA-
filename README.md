# Health-System-API-CSA-
## Overview
The **Health System API** is a backend solution designed to streamline and manage healthcare services. Built using **JAX-RS** (Java API for RESTful Web Services), the API adheres to REST principles, ensuring scalability, modularity, and maintainability. It supports efficient management of patients, doctors, appointments, medical records, prescriptions, and billing.

---

## Features
### **Person Management**
- Acts as a base class for individuals, encapsulating attributes like name, ID, and contact details.

### **Patient Management**
- Maintains patient-specific details, including medical history and health status.

### **Doctor Management**
- Handles doctors' information, including specialization and availability.

### **Appointment Management**
- Facilitates scheduling and management of doctor-patient appointments.

### **Medical Records**
- Stores detailed health records, including diagnoses and treatments.

### **Prescriptions**
- Manages details of prescribed medications and dosage instructions.

### **Billing**
- Handles financial details such as invoices, payment status, and payment methods.

---
## System Design
### **Entities**
- Encapsulated classes for modular and object-oriented design:
  - **Person**: Base entity for shared attributes.
  - **Patient**: Extends `Person` with patient-specific details.
  - **Doctor**: Extends `Person` with doctor-specific attributes.
  - **Appointment**: Links doctors and patients.
  - **Medical Record**: Tracks health records.
  - **Prescription**: Captures medication details.
  - **Billing**: Handles invoicing and payments.

### **DAO Classes**
- Perform CRUD operations and interact with the data repository.

### **Resource Classes**
- Define RESTful endpoints using JAX-RS for seamless client-server interaction.

---

## System Design
### General Structure
- GET: Retrieve records or details by ID.

- POST: Add new records.

- PUT: Update existing records.

- DELETE: Remove records.



### Types
- Person Management
- Patient Management
- Doctor Management
- Appointment Management
- Medical Record Management
- Prescription Management
- Billing Management

### Examples
```
-GET /person/{personId}
-POST /person
-PUT /person/{personId}
-DELETE /person/{personId}
```
---


## Installation
Clone the repository:
   ```bash
   git clone [https://github.com/<username>/<repository-name>.git](https://github.com/KI-5/Health-System-API-CSA-.git)
   cd <repository-name>
You can copy this into your **README.md** file. Let me know if you need further edits! 😊


