package com.example.daosism.Controllers;

import com.example.daosism.CRUD.UniversalService;
import com.example.daosism.Models.Person;
import com.example.daosism.Models.Transport;
import com.example.daosism.Repositories.PersonRepo;
import com.example.daosism.Repositories.TransportRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TransportController {

    private UniversalService us;
    private final TransportRepo tr;
    private final PersonRepo pr;
    public List<Transport> transp = new ArrayList<Transport>();

    public TransportController(UniversalService us, TransportRepo tr, PersonRepo pr) {
        this.us = us;
        this.tr = tr;
        this.pr = pr;
    }

    @GetMapping("/Transports")
    public String Transports(Model model){
        transp = us.getAll(Transport.class);
        model.addAttribute("transp", transp);
        return "Transports";
    }

    @GetMapping("/deleteTransport/{transpId}")
    public String DeleteTransport(@PathVariable("transpId") int id) {

        us.delete(Transport.class, id);
        return "redirect:/Transports";
    }

    @GetMapping("/UpdateTransport/{transpId}")
    public String UpdateLocation(@PathVariable("transpId") Integer id, Model model) {
        for (Transport transport : transp) {
            if (transport.getId() == id) {
                model.addAttribute("name", transport.getName());
                model.addAttribute("id", transport.getId());
                model.addAttribute("number", transport.getNumber());
                model.addAttribute("description", transport.getDescription());
                model.addAttribute("driver", transport.getDriver().getName() + transport.getDriver().getSecondName());

            }
        }
        return "Transport";
    }

    @GetMapping("/AddTransport")
    public String AddTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") Integer driver){
        try {
            Person person = pr.getPersonById(driver);
            us.create(new Transport(name, number, description, person));

        } catch (Exception ignored){

        }
        return "redirect:/Transports";
    }

    @PostMapping("/updateTransport")
    public String UpdateTransport(@ModelAttribute("name") String name, @ModelAttribute("number") String number, @ModelAttribute("description") String description, @ModelAttribute("driver") Integer driver, @ModelAttribute("id") Integer id){

        Person person = pr.getPersonById(driver);
        us.update(new Transport(name, number, description, person, id));
        return "redirect:/Transports";
    }

    @GetMapping("/GetTransport")
    public String UpdateLocation(@ModelAttribute("numberTr") String numberTr, Model model) {
        Transport transport;
        transport = tr.findTransportByNumber(numberTr.trim());

        model.addAttribute("name", transport.getName());
        model.addAttribute("id", transport.getId());
        model.addAttribute("number", transport.getNumber());
        model.addAttribute("description", transport.getDescription());
        model.addAttribute("driver", transport.getDriver().getName() + " " + transport.getDriver().getSecondName());

        return "Transport";
    }

    @GetMapping("/goI")
    public String Index() {

        return "Index";
    }
}
