package ru.itpark.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Account;
import ru.itpark.domain.Product;
import ru.itpark.service.ProductsService;

@Controller
@RequestMapping("/")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String getAll(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        model.addAttribute("products", productsService.findAll());

        return "pages/products";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addForm() {
        return "pages/product-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(@ModelAttribute Product product, @RequestParam MultipartFile image) {
        productsService.add(product, image);

        return "redirect:/";
    }

    @GetMapping("/{id}") // {id} -> /notes/1
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("product", productsService.findById(id));

        return "pages/product";
    }

    @PreAuthorize("hasRole('ADMIN')")  // only admin can delete notes
    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        productsService.removeById(id);

        return "redirect:/";
    }



}
