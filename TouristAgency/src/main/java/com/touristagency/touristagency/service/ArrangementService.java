package com.touristagency.touristagency.service;

import com.touristagency.touristagency.interfaces.repositoryInterface.ArrangementRepositoryInterface;
import com.touristagency.touristagency.interfaces.serviceInterface.ArrangementServiceInterface;
import com.touristagency.touristagency.model.Agency;
import com.touristagency.touristagency.model.Arrangement;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ArrangementService implements ArrangementServiceInterface {

    @Autowired
    private ArrangementRepositoryInterface repo;

    @Override
    public List<Arrangement> getAll() {
        return repo.findAll();
    }

    @Override
    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public Optional<Arrangement> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Arrangement create(Arrangement t) {
        return repo.save(t);
    }

    @Override
    public Optional<Arrangement> update(Arrangement t, int id) {
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
    public List<Arrangement> getArrangementByHotel(Hotel hotel) {
        return repo.findByHotel(hotel);
    }

    @Override
    public List<Arrangement> getArrangementByAgency(Agency agency) {
        return repo.findByAgency(agency);
    }

    @Override
    public List<Arrangement> getArrangementByPayed(boolean place) {
        return repo.findByPayedEquals(place);
    }

}
