package com.oocl.ita.controller;

import com.oocl.ita.model.Pack;
import com.oocl.ita.servie.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/packages")
public class PackController {

    @Autowired
    private PackService packService;

    @GetMapping
    public List<Pack> getPackages(){
        return packService.getAll();
    }

    @GetMapping(params = "state")
    public List<Pack> getPackagesByState(@RequestParam String state) {
        return packService.getByState(state);
    }

    @PatchMapping(path = "/{id}", params = "state")
    public Pack setPackageState(@PathVariable String id, @RequestParam String state) {
        return packService.setState(id, state);
    }

    @PostMapping
    public Pack addPackage(@RequestBody Pack pack) {
        return packService.add(pack);
    }

    @PatchMapping(path = "/{id}", params = "time")
    public Pack setPackageTime(@PathVariable String id, @RequestParam String time) {
        return packService.setTime(id, time);
    }
}
