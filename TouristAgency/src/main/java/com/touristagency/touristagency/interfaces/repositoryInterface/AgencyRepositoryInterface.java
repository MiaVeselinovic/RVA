package com.touristagency.touristagency.interfaces.repositoryInterface;

import com.touristagency.touristagency.model.Agency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgencyRepositoryInterface extends JpaRepository<Agency, Integer> {
    List<Agency> findByNameContainingIgnoreCase(String name);
}
