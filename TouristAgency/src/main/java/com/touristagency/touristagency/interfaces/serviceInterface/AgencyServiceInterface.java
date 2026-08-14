package com.touristagency.touristagency.interfaces.serviceInterface;

import com.touristagency.touristagency.model.Agency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgencyServiceInterface extends CrudServiceInterface<Agency> {
    List<Agency> getAgencyByName(String name);
}
