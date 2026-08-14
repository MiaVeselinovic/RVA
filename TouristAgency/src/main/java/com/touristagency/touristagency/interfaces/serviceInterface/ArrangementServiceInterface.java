package com.touristagency.touristagency.interfaces.serviceInterface;

import com.touristagency.touristagency.model.Agency;
import com.touristagency.touristagency.model.Arrangement;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArrangementServiceInterface extends CrudServiceInterface<Arrangement> {
    List<Arrangement> getArrangementByHotel(Hotel hotel);
    List<Arrangement> getArrangementByAgency(Agency agency);
    List<Arrangement> getArrangementByPayed(boolean payed);
}
