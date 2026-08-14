package com.touristagency.touristagency.service;

import com.touristagency.touristagency.interfaces.repositoryInterface.AgencyRepositoryInterface;
import com.touristagency.touristagency.interfaces.serviceInterface.AgencyServiceInterface;
import com.touristagency.touristagency.model.Agency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AgencyService implements AgencyServiceInterface {


    @Autowired
    private AgencyRepositoryInterface repo;

    @Override
    public List<Agency> getAll() {return repo.findAll();}

    @Override
    public boolean existsById(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public Optional<Agency> findById(int id) {
        return repo.findById(id);
    }

    @Override
    public Agency create(Agency t) {
        return repo.save(t);
    }

    @Override
    public Optional<Agency> update(Agency a, int id) {
        if(existsById(id)){
            a.setId(id);
            return Optional.of(repo.save(a));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<Agency> getAgencyByName(String naziv) {
        return repo.findByNameContainingIgnoreCase(naziv);
    }

}
