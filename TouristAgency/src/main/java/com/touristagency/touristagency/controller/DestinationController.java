package com.touristagency.touristagency.controller;

import com.touristagency.touristagency.model.Destination;
import com.touristagency.touristagency.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/destination")
    public ResponseEntity<?> getAllDestinations() {
        return ResponseEntity.ok(destinationService.getAll());
    }

    @GetMapping("/destinations/place/{place}")
    public ResponseEntity<?> getDestinationByPlace(@PathVariable String place) {
        List<Destination> destinations = destinationService.getDestinationByPlace(place);
        if (destinations.isEmpty())
            return new ResponseEntity<String>(String.format("There are no any destination in place: %s", place), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(destinations);
    }

    @GetMapping("/destination/id/{id}")
    public ResponseEntity<?> getDestinationById(@PathVariable int id) {
        Optional<Destination> destination = destinationService.findById(id);
        if (destination.isEmpty())
            return new ResponseEntity<String>(String.format("There are no any destination with id: %d", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(destination.get());
    }

    @PostMapping("/destination")
    public ResponseEntity<?> createDestination(@RequestBody Destination destination) {
        if (destinationService.existsById(destination.getId()))
            return new ResponseEntity<String>(String.format("Destination with id: %d already exist", destination.getId()), HttpStatus.CONFLICT);
        Destination createdDestination = destinationService.create(destination);
        URI uri = URI.create("/destination/id/" + createdDestination.getId());
        return ResponseEntity.created(uri).body(createdDestination);
    }

    @GetMapping("/destination/country/{country}")
    public ResponseEntity<?> getDestinationsByCountry(@PathVariable String country) {
        List<Destination> destinations = destinationService.getDestinationByCountry(country);
        if (destinations.isEmpty())
            return new ResponseEntity<String>(String.format("There are no any destination in country: %s", country), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(destinations);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@PathVariable int id, @RequestBody Destination destination) {
        Optional<Destination> updatedDestination = destinationService.update(destination, id);
        if (updatedDestination.isEmpty())
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(updatedDestination.get());
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable int id) {
        if (!destinationService.existsById(id))
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        destinationService.deleteById(id);
        return ResponseEntity.ok(String.format("Entity with id: %s is deleted", id));
    }

}
