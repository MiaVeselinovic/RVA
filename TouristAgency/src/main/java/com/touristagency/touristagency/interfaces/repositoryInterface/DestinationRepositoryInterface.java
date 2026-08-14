package com.touristagency.touristagency.interfaces.repositoryInterface;

import com.touristagency.touristagency.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepositoryInterface extends JpaRepository<Destination, Integer> {
    List<Destination> findByPlaceContainingIgnoreCase(String place);
    List<Destination> findByCountryContainingIgnoreCase(String country);
}
