package org.launchcode.codingevents.controllers;


//import org.launchcode.codingevents.data.EventData; // no longer needed because of EventRepository class
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {

    @Autowired // tells spring boot to look for EventRepository if it exists
    private EventRepository eventRepository;
    // findAll, save, findById are accessible by eventRepository extending CrudRepository(these methods are in this class).

    @Autowired
    private EventCategoryRepository eventCategoryRepository;


    @GetMapping
    public String displayAllEvents(@RequestParam(required=false) Integer categoryId, Model model) {
        if(categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            Optional<EventCategory> result =eventCategoryRepository.findById(categoryId);
            if(result.isEmpty()){
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            }else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        }
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories",eventCategoryRepository.findAll());
        return "events/create";
    }


    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Event");
            return "events/create";
        }


        eventRepository.save(newEvent);
        return "redirect:";
    }



    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }


    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){

        if(eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model){

        Optional<Event> result = eventRepository.findById(eventId);
        if(result.isEmpty()){
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        }else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + "Details");
            model.addAttribute("event", event);
        }
        return "events/detail";
    }



}



//
//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId){
//        Event eventToEdit = EventData.getById(eventId);
//        model.addAttribute("event", eventToEdit);
//        String title = "Edit Event " + eventToEdit.getName() + " (id=" + eventToEdit.getId() + ")";
//        model.addAttribute("title", title);
//        return "events/edit";
//    }
//
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description){
//        Event eventToEdit = EventData.getById(eventId);
//        eventToEdit.setName(name);
//        eventToEdit.setDescription(description);
//        return "redirect:";
//    }
//
//


