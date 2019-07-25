package com.oocl.ita.servie;

import com.oocl.ita.model.Pack;
import com.oocl.ita.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
        if (optionalPack.isPresent()) {
            Pack pack = optionalPack.get();
            pack.setState(state);
            return repository.save(pack);
        }
        return null;
    }

    public Pack add(Pack pack) {
        return repository.save(pack);
    }

    public Pack setTime(String id, long time) {
        if(Calendar.getInstance().getTimeInMillis() < time) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour < 9 || hour > 20){
            return null;
        }
        Optional<Pack> optionalPack = repository.findById(id);
        if (optionalPack.isPresent()) {
            Pack pack = optionalPack.get();
            pack.setTime(time);
            pack.setState("已预约");
            return repository.save(pack);
        }
        return null;
    }
}
