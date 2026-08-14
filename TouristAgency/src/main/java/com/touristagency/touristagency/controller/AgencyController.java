package com.touristagency.touristagency.controller;


import com.touristagency.touristagency.model.Agency;
import com.touristagency.touristagency.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class AgencyController {

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/agencies")
    public ResponseEntity<?> getAllAgencies() {
        return ResponseEntity.ok(agencyService.getAll());
    }

    @GetMapping("/agency/name/{name}")
    public ResponseEntity<?> getAgencyByName(@PathVariable String name) {
        List<Agency> agencies = agencyService.getAgencyByName(name);
        if(agencies.isEmpty()) return new ResponseEntity<String>(String.format("There are no agency: %s", name), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(agencies);
    }

    @GetMapping("/agency/id/{id}")
    public ResponseEntity<?> getAgencyById(@PathVariable int id) {
        Optional<Agency> agency = agencyService.findById(id);
        if(agency.isEmpty())
            return new ResponseEntity<String>(String.format("There are no agency with id: %d", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(agency.get());
    }

    @PostMapping("/agency")
    public ResponseEntity<?> createAgency(@RequestBody Agency agency) {
        if(agencyService.existsById(agency.getId()))
            return new ResponseEntity<String>(String.format("Agency with id: %d already exist", agency.getId()), HttpStatus.CONFLICT);
        Agency createdAgency = agencyService.create(agency);
        URI uri=URI.create("/agency/id/" + createdAgency.getId());
        return ResponseEntity.created(uri).body(createdAgency);
    }

    @PutMapping("/agency/{id}")
    public ResponseEntity<?> updateAgency(@PathVariable int id, @RequestBody Agency agency){
        Optional<Agency> updatedAgency = agencyService.update(agency, id);
        if(updatedAgency.isEmpty())
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(updatedAgency.get());
    }

    @DeleteMapping("/agency/{id}")
    public ResponseEntity<?> deleteAgency(@PathVariable int id){
        if(!agencyService.existsById(id))
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        agencyService.deleteById(id);
        return ResponseEntity.ok(String.format("Entity with id: %s is deleted", id));
    }


}
