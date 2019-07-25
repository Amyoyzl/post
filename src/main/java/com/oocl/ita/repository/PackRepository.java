package com.oocl.ita.repository;

import com.oocl.ita.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, String> {
    List<Pack> findByState(String state);
}
