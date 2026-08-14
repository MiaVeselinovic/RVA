package com.touristagency.touristagency.interfaces.repositoryInterface;

import com.touristagency.touristagency.model.Agency;
import com.touristagency.touristagency.model.Arrangement;
import com.touristagency.touristagency.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArrangementRepositoryInterface extends JpaRepository<Arrangement, Integer> {
    List<Arrangement> findByPayedEquals(boolean payed);
    List<Arrangement> findByAgency(Agency agency);
    List<Arrangement> findByHotel(Hotel hotel);
}
