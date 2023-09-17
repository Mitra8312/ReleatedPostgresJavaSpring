package com.example.daosism.Controllers;

import com.example.daosism.CRUD.UniversalService;
import com.example.daosism.Models.Location;
import com.example.daosism.Repositories.LocationRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LocationController {
    private final LocationRepo lr;
    private UniversalService us;
    public List<Location> locs = new ArrayList<>();

    public LocationController(LocationRepo lr, UniversalService us) {
        this.lr = lr;
        this.us = us;
    }

    @GetMapping("/Locations")
    public String Locations(Model model){
        locs = us.getAll(Location.class);
        model.addAttribute("locs", locs);
        return "Locations";
    }

    @GetMapping("/deleteLocation/{locId}")
    public String DeleteLocation(@PathVariable("locId") int id) {

        us.delete(Location.class, id);

        return "redirect:/Locations";
    }

    @GetMapping("/UpdateLocation/{locId}")
    public String UpdateLocation(@PathVariable("locId") Integer id, Model model) {
        for (int i = 0; i < locs.size(); i++) {
            if (locs.get(i).getId() == id) {
                model.addAttribute("name", locs.get(i).getName());
                model.addAttribute("id", locs.get(i).getId());
                model.addAttribute("address", locs.get(i).getAddress());
                model.addAttribute("description", locs.get(i).getDescription());
            }
        }
        return "Location";
    }

    @GetMapping("/AddLocation")
    public String AddLocation(@ModelAttribute("name") String name, @ModelAttribute("address") String address, @ModelAttribute("description") String description){

        us.create(new Location(name, address, description));
        return "redirect:/Locations";
    }

    @PostMapping("/updateLocation")
    public String UpdatePerson(@ModelAttribute("name") String name, @ModelAttribute("address") String address, @ModelAttribute("description") String description, @ModelAttribute("id") Integer id){

        us.update(new Location(name, address, description, id));
        return "redirect:/Locations";
    }

    @GetMapping("/GetLocation")
    public String UpdateLocation(@ModelAttribute("address") String address, Model model) {
        Location location;
        location = lr.findLocationByAddress(address.trim());

        model.addAttribute("name", location.getName());
        model.addAttribute("id", location.getId());
        model.addAttribute("address", location.getAddress());
        model.addAttribute("description", location.getDescription());

        return "Location";
    }

    @GetMapping("/goIn")
    public String Index() {

        return "Index";
    }
}
