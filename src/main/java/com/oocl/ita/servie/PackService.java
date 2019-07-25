package com.oocl.ita.servie;

import com.oocl.ita.model.Pack;
import com.oocl.ita.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public Pack setState(String id, String state) {
        Optional<Pack> optionalPack = repository.findById(id);
        if(optionalPack.isPresent()) {
            Pack pack = optionalPack.get();
            pack.setState(state);
            return repository.save(pack);
        }
        return null;
    }

    public Pack add(Pack pack) {
        return repository.save(pack);
    }

    public Pack setTime(String id, String time) {
        Optional<Pack> optionalPack = repository.findById(id);
        if(optionalPack.isPresent()) {
            Pack pack = optionalPack.get();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                pack.setTime(simpleDateFormat.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return repository.save(pack);
        }
        return null;
    }
}
