package com.example.daosism.Controllers;

import com.example.daosism.Models.Cell;
import com.example.daosism.Models.Location;
import com.example.daosism.Models.Product;
import com.example.daosism.Repositories.CellRepo;
import com.example.daosism.Repositories.LocationRepo;
import com.example.daosism.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CellController {
    private final CellRepo us;
    private final ProductRepo pr;
    private final LocationRepo lr;
    private Cell updateableCell;
    public List<Cell> cells = new ArrayList<>();

    @Autowired
    public CellController(CellRepo us, ProductRepo pr, LocationRepo lr){
        this.pr = pr;
        this.lr = lr;
        updateableCell = new Cell();
        this.us = us;
    }

    @GetMapping("/Cells")
    public String Cells(Model model){
        cells = us.findAll();

        model.addAttribute("cells", cells);
        return "Cells";
    }

    @GetMapping("/deleteCell/{cellId}")
    public String DeleteCell(@PathVariable("cellId") int id) {

        us.deleteById(id);
        return "redirect:/Cells";
    }

    @GetMapping("/UpdateCell/{cellId}")
    public String UpdateCell(@PathVariable("cellId") Integer id, Model model) {
        for (Cell cell : cells) {
            if (cell.getId() == id) {
                updateableCell = cell;
                model.addAttribute("name", cell.getName());
                model.addAttribute("id", cell.getId());
                model.addAttribute("level", cell.getLevel());
                model.addAttribute("hash", cell.getHash());
                model.addAttribute("loc", cell.getLoc().getAddress());
                model.addAttribute("product", cell.getProduct().getArticle());
            }
        }
        return "Cell";
    }

    @PostMapping("/AddCell")
    public String AddCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") String loc, @ModelAttribute("product") String product){

        Location location = lr.findLocationByAddress(loc);
        Product prod = pr.findProductByArticle(product);
        us.save(new Cell(name, level, hash, location, prod));
        return "redirect:/Cells";
    }

    @PostMapping("/updateCell")
    public String UpdateCell(@ModelAttribute("name") String name, @ModelAttribute("level") Integer level, @ModelAttribute("hash") String hash, @ModelAttribute("loc") String loc, @ModelAttribute("product") String product, @ModelAttribute("id") Integer id){
        Location location = lr.findLocationByAddress(loc);
        Product prod = pr.findProductByArticle(product);
        us.delete(updateableCell);
        us.save(new Cell(name, level, hash, location, prod, id));
        return "redirect:/Cells";
    }

    @GetMapping("/GetCell")
    public String GetByProduct(@ModelAttribute("article") String art, Model model) {

        updateableCell = us.findCellByHash(art.trim());

        model.addAttribute("name", updateableCell.getName());
        model.addAttribute("id", updateableCell.getId());
        model.addAttribute("level", updateableCell.getLevel());
        model.addAttribute("hash", updateableCell.getHash());
        model.addAttribute("loc", updateableCell.getLoc().getAddress());
        model.addAttribute("product", updateableCell.getProduct().getArticle());

        return "Cell";
    }

    @GetMapping("/goInd")
    public String Index() {

        return "Index";
    }
}
