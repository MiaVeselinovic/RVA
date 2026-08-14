package com.touristagency.touristagency.interfaces.repositoryInterface;

import com.touristagency.touristagency.model.Destination;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepositoryInterface extends JpaRepository<Hotel, Integer> {
    List<Hotel> findByNameContainingIgnoreCase(String name);
    List<Hotel> findByDestination(Destination destination);
}
