package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("tags")
public class TagController {

    @Autowired
   private  TagRepository tagRepository;

    @GetMapping
    public String displayTags(Model model){
        model.addAttribute("title", "All Tags");
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping("create")
    public String displayCreateTagForm(Model model){
        model.addAttribute("title", "Create Tag");
        model.addAttribute(new Tag());
        return "tags/create";
    }

    @PostMapping("create")
    public String processCreateTagForm(@ModelAttribute @Valid Tag newTag, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Create Tag");
            return "tags/create";
        }
        tagRepository.save(newTag);
        return "redirect:";
    }

//
//    @GetMapping("delete")
//    public String displayDeleteTagForm(Model model){
//        model.addAttribute("title", "Delete tag");
//        model.addAttribute("tags", tagRepository.findAll());
//        return "tags/delete";
//    }
//
//
//    @PostMapping("delete")
//    public String processDeleteTagForm(@RequestParam(required=false) int[] tagIds){
//
//        if(tagIds != null){
//            for(int id : tagIds){
//                tagRepository.deleteById(id);
//            }
//        }
//        return "redirect:";
//    }

}
