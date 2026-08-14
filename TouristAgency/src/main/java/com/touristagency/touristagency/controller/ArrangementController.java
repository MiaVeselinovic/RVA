package com.touristagency.touristagency.controller;

import com.touristagency.touristagency.model.Agency;
import com.touristagency.touristagency.model.Arrangement;
import com.touristagency.touristagency.model.Hotel;
import com.touristagency.touristagency.service.AgencyService;
import com.touristagency.touristagency.service.ArrangementService;
import com.touristagency.touristagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ArrangementController {

    @Autowired
    private ArrangementService arrangementService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/arrangement/id/{id}")
    public ResponseEntity<?> getArrangementById(@PathVariable int id) {
        Optional<Arrangement> arrangement = arrangementService.findById(id);
        if (arrangement.isEmpty())
            return new ResponseEntity<String>(String.format("There are no arrangement with id: %d", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(arrangement.get());
    }

    @PostMapping("/arrangement")
    public ResponseEntity<?> createArrangement(@RequestBody Arrangement arrangement) {
        if (arrangementService.existsById(arrangement.getId()))
            return new ResponseEntity<String>(String.format("Arrangement sa id: %d already exist", arrangement.getId()), HttpStatus.CONFLICT);
        Arrangement createdArrangement = arrangementService.create(arrangement);
        URI uri = URI.create("/arrangement/id/" + createdArrangement.getId());
        return ResponseEntity.created(uri).body(createdArrangement);
    }
    @PutMapping("/arrangement/{id}")
    public ResponseEntity<?> updateArrangement(@PathVariable int id, @RequestBody Arrangement arrangement) {
        Optional<Arrangement> updatedArrangement= arrangementService.update(arrangement, id);
        if (updatedArrangement.isEmpty())
            return new ResponseEntity<String>(String.format("Arrangement with id: %s not exist", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(updatedArrangement.get());
    }

    @DeleteMapping("/arrangement/{id}")
    public ResponseEntity<?> deleteArrangement(@PathVariable int id) {
        if (!arrangementService.existsById(id))
            return new ResponseEntity<String>(String.format("Arrangement with id: %s not exist", id), HttpStatus.NOT_FOUND);
        arrangementService.deleteById(id);
        return ResponseEntity.ok(String.format("Arrangement with: %s is deleted", id));
    }

    @GetMapping("/arrangement/agency/{foreignKey}")
    public ResponseEntity<?> getArrangementsByAgency(@PathVariable int foreignKey) {
        Optional<Agency> agency = agencyService.findById(foreignKey);
        if (agency.isEmpty())
            return new ResponseEntity<String>(String.format("Agency with id: %s not exist", foreignKey), HttpStatus.NOT_FOUND);
        List<Arrangement> arrangements = arrangementService.getArrangementByAgency(agency.get());
        if (arrangements.isEmpty())
            return new ResponseEntity<String>(String.format("There are no entity with foreign key: %s", foreignKey), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(arrangements);
    }

    @GetMapping("/arrangement/hotel/{foreignKey}")
    public ResponseEntity<?> getArrangementByHotel(@PathVariable int foreignKey) {
        Optional<Hotel> hotel = hotelService.findById(foreignKey);
        if (hotel.isEmpty())
            return new ResponseEntity<String>(String.format("Hotel with id: %s not exist", foreignKey), HttpStatus.NOT_FOUND);
        List<Arrangement> arrangements = arrangementService.getArrangementByHotel(hotel.get());
        if (arrangements.isEmpty())
            return new ResponseEntity<String>(String.format("There are not entity with %s", foreignKey), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(arrangements);
    }

    @GetMapping("/arrangement/payed/{payed}")
    public ResponseEntity<?> getArrangementByPayed(@PathVariable boolean payed) {
        List<Arrangement> arrangements = arrangementService.getArrangementByPayed(payed);
        if (arrangements.isEmpty())
            if(payed) {
                return new ResponseEntity<String>(String.format("There are no payed arrangements"), HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<String>(String.format("There are no unpayed arrangements"), HttpStatus.NOT_FOUND);
            }
        return ResponseEntity.ok(arrangements);
    }
}


