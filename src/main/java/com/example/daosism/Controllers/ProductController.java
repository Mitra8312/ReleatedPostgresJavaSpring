package com.example.daosism.Controllers;

import com.example.daosism.CRUD.UniversalService;
import com.example.daosism.Models.Product;
import com.example.daosism.Repositories.ProductRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {

    private UniversalService us;
    private final ProductRepo pr;
    public List<Product> products = new ArrayList<>();

    public ProductController(UniversalService us, ProductRepo pr) {
        this.us = us;
        this.pr = pr;
    }

    @GetMapping("/Products")
    public String Product(Model model){
        products = us.getAll(Product.class);

        model.addAttribute("products", products);
        return "Products";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String DeleteProduct(@PathVariable("productId") int id) {

        us.delete(Product.class, id);
        return "redirect:/Products";
    }

    @GetMapping("/UpdateProduct/{productId}")
    public String UpdateProduct(@PathVariable("productId") int id, Model model) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                model.addAttribute("name1", products.get(i).getName());
                model.addAttribute("id1", products.get(i).getId());
                model.addAttribute("quantity1", products.get(i).getQuantity());
                model.addAttribute("article1", products.get(i).getArticle());
                model.addAttribute("price1", products.get(i).getPrice());
                model.addAttribute("owner1", products.get(i).getOwner());

            }
        }
        return "Product";
    }

    @PostMapping("/AddProduct")
    public String AddProduct(@ModelAttribute("name") String name, @ModelAttribute("article") String article, @ModelAttribute("owner") String owner, @ModelAttribute("quantity") Integer quantity, @ModelAttribute("price") Integer price){
        try
        {
            us.create(new Product(name, article, owner, quantity, price));
        }
        catch (Exception ignored){

        }
        return "redirect:/Products";
    }

    @PostMapping("/updateProduct")
    public String UpdateProduct(@ModelAttribute("name1") String name, @ModelAttribute("article1") String article, @ModelAttribute("owner1") String owner, @ModelAttribute("quantity1") Integer quantity, @ModelAttribute("price1") Integer price, @ModelAttribute("id1") Integer id){

        us.update(new Product(name, article, owner, quantity, price, id));
        return "redirect:/Products";
    }

    @GetMapping("/GetProduct")
    public String UpdateProduct(@ModelAttribute("article") String article, Model model) {
        Product product;
        product = pr.findProductByArticle(article.trim());

        model.addAttribute("name1", product.getName());
        model.addAttribute("id1", product.getId());
        model.addAttribute("quantity1", product.getQuantity());
        model.addAttribute("article1", product.getArticle());
        model.addAttribute("price1", product.getPrice());
        model.addAttribute("owner1", product.getOwner());

        return "Product";
    }

    @GetMapping("/goIndex")
    public String Index() {

        return "Index";
    }
}
