package com.Cloud.Cloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Cloud.Cloud.entities.Contact;
import com.Cloud.Cloud.services.ContactService;

@RestController
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private ContactService contactService;


    //get contact

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId){
          return contactService.getById(contactId);
    }

}