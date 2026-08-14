package com.touristagency.touristagency.service;

import com.touristagency.touristagency.interfaces.repositoryInterface.HotelRepositoryInterface;
import com.touristagency.touristagency.interfaces.serviceInterface.HotelServiceInterface;
import com.touristagency.touristagency.model.Destination;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HotelService implements HotelServiceInterface {
    @Autowired
    private HotelRepositoryInterface repo;

    @Override
    public List<Hotel> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public Optional<Hotel> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Hotel create(Hotel t) {
        return repo.save(t);
    }

    @Override
    public Optional<Hotel> update(Hotel t, int id) {
        if(existsById(id)){
            t.setId(id);
            return Optional.of(repo.save(t));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Hotel> getHotelsByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Hotel> getHotelsByDestination(Destination destination) {
        return repo.findByDestination(destination);
    }

}
