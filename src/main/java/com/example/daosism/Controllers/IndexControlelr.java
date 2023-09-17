package com.example.daosism.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlelr {
    @GetMapping()
    public String Index(){

        return "Index";
    }

    @GetMapping("/goProducts")
    public String Products() {

        return "redirect:/Products";
    }

    @GetMapping("/goPeople")
    public String People() {

        return "redirect:/People";
    }

    @GetMapping("/goCells")
    public String Cells() {

        return "redirect:/Cells";
    }

    @GetMapping("/goLocations")
    public String Locations() {

        return "redirect:/Locations";
    }

    @GetMapping("/goTransports")
    public String Transports() {

        return "redirect:/Transports";
    }
}
