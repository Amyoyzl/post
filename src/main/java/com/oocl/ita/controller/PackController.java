package com.oocl.ita.controller;

import com.oocl.ita.model.Pack;
import com.oocl.ita.servie.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
