package com.touristagency.touristagency.interfaces.serviceInterface;

import com.touristagency.touristagency.model.Destination;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelServiceInterface extends CrudServiceInterface<Hotel> {
    List<Hotel> getHotelsByName(String name);
    List<Hotel> getHotelsByDestination(Destination destination);
}
