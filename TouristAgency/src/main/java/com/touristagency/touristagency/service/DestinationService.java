package com.touristagency.touristagency.service;

import com.touristagency.touristagency.interfaces.repositoryInterface.DestinationRepositoryInterface;
import com.touristagency.touristagency.interfaces.serviceInterface.DestinationServiceInterface;
import com.touristagency.touristagency.model.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DestinationService implements DestinationServiceInterface {

    @Autowired
    private DestinationRepositoryInterface repo;

    @Override
    public List<Destination> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public Optional<Destination> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Destination create(Destination d) {
        return repo.save(d);
    }

    @Override
    public Optional<Destination> update(Destination t, int id) {
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
    public List<Destination> getDestinationByPlace(String place) {
        return repo.findByPlaceContainingIgnoreCase(place);
    }

    @Override
    public List<Destination> getDestinationByCountry(String drzava) {
        return repo.findByCountryContainingIgnoreCase(drzava);
    }
}
