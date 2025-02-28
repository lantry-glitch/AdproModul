package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "CreateProduct";
        }
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @PostMapping("/edit")
    public String editProductPost(@Valid @ModelAttribute Product editedProduct, BindingResult result) {
        if (result.hasErrors()) {
            return "EditProduct";
        }
        service.edit(editedProduct);
        return "redirect:/product/list";
    }

    @GetMapping("/edit/{editedProductId}")
    public String editProductPage(@PathVariable String editedProductId, Model model) {
        Product editedProduct = service.findById(editedProductId);
        model.addAttribute("product", editedProduct);
        return "EditProduct";
    }

    @DeleteMapping("/delete/{deletedProductId}")
    public String deleteProduct(@PathVariable String deletedProductId) {
        service.delete(deletedProductId);
        return "redirect:/product/list";
    }
}