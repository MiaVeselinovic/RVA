package com.touristagency.touristagency.interfaces.serviceInterface;

import com.touristagency.touristagency.model.Destination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DestinationServiceInterface extends CrudServiceInterface<Destination> {
    List<Destination> getDestinationByPlace(String place);
    List<Destination> getDestinationByCountry(String country);
}
