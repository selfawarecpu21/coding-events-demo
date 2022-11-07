package org.launchcode.codingevents.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

//Event is responsible for organizing user-inputted information into a Java object
// The class is the things we want to keep track of for the application - Business Data

@Entity
public class Event extends AbstractEntity{

//    @Id
//    @GeneratedValue  , Now AbstractEntity takes care of this field
//    private int id; // Primary Key for database, gives us a unique id for every Event object created

   // private static int nextId = 1; no longer needed now that id has @GeneratedValue for incrementing new object ids

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;



    private EventType type;

    public Event(){

    }

    public Event(String name, String description, String contactEmail, EventType type){
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.type = type;
//        this.id = nextId; no longer needed because of @GeneratedValue line 19
//        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return name;
    }



}