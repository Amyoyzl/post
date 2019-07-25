package com.oocl.ita.servie;

import com.oocl.ita.model.Pack;
import com.oocl.ita.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    @Autowired
    private PackRepository repository;

    public List<Pack> getAll() {
        return repository.findAll();
    }

    public List<Pack> getByState(String state) {
        return repository.findByState(state);
    }
}
