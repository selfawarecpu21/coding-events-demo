package org.launchcode.codingevents.models;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Event is responsible for organizing user-inputted information into a Java object
// The class is the things we want to keep track of for the application - Business Data

@Entity
public class Event extends AbstractEntity{

//    @Id
//    @GeneratedValue  , Now AbstractEntity takes care of this field
//    private int id; // Primary Key for database, gives us a unique id for every Event object created

   // private static int nextId = 1; no longer needed now that id has @GeneratedValue for incrementing new object ids, Also replaced using AbstractEntity

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @OneToOne
    @Valid
    @NotNull
    private EventDetails eventDetails;



    @ManyToOne // many events in one category
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;


    public Event(String name, EventCategory eventCategory){
        this.name = name;
        this.eventCategory = eventCategory;
//        this.id = nextId; no longer needed because of @GeneratedValue, AbstractEntity takes care of this
//        nextId++;
    }


    public Event(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }


    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return name;
    }



}
