package com.touristagency.touristagency.controller;


import com.touristagency.touristagency.model.Destination;
import com.touristagency.touristagency.model.Hotel;
import com.touristagency.touristagency.service.DestinationService;
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
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/hotels")
    public ResponseEntity<?> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/hotel/name/{name}")
    public ResponseEntity<?> getHotelsByName(@PathVariable String name) {
        List<Hotel> hotels = hotelService.getHotelsByName(name);
        if (hotels.isEmpty())
            return new ResponseEntity<String>(String.format("There are no hotel with this name: %s", name), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/hotel/id/{id}")
    public ResponseEntity<?> getHotelById(@PathVariable int id) {
        Optional<Hotel> hotel = hotelService.findById(id);
        if (hotel.isEmpty())
            return new ResponseEntity<String>(String.format("There are no hotel with id: %d", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(hotel.get());
    }

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        if (hotelService.existsById(hotel.getId()))
            return new ResponseEntity<String>(String.format("Hotel with id: %d not exist", hotel.getId()), HttpStatus.CONFLICT);
        Hotel createdHotel = hotelService.create(hotel);
        URI uri = URI.create("/hotel/id/" + createdHotel.getId());
        return ResponseEntity.created(uri).body(createdHotel);
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        Optional<Hotel> updatedHotel = hotelService.update(hotel, id);
        if (updatedHotel.isEmpty())
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(updatedHotel.get());
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id) {
        if (!hotelService.existsById(id))
            return new ResponseEntity<String>(String.format("Entity with id: %s not exist", id), HttpStatus.NOT_FOUND);
        hotelService.deleteById(id);
        return ResponseEntity.ok(String.format("Entity with id: %s is deleted", id));
    }

    @GetMapping("/hotel/destination/{foreignKey}")
    public ResponseEntity<?> getHotelsByDestination(@PathVariable int foreignKey) {
        Optional<Destination> destination = destinationService.findById(foreignKey);
        if (destination.isEmpty())
            return new ResponseEntity<String>(String.format("Destination with id: %s not exist", foreignKey), HttpStatus.NOT_FOUND);
        List<Hotel> hotels = hotelService.getHotelsByDestination(destination.get());
        if (hotels.isEmpty())
            return new ResponseEntity<String>(String.format("There are no entity with foreign key: %S", foreignKey), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(hotels);
    }


}
