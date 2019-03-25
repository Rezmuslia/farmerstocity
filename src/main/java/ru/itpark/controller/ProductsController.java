package ru.itpark.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Account;
import ru.itpark.domain.Chat;
import ru.itpark.domain.Product;
import ru.itpark.service.ChatService;
import ru.itpark.service.ProductsService;

@Controller
@RequestMapping("/")
public class ProductsController {
    private final ProductsService productsService;
    private final ChatService chatService;

    public ProductsController(ProductsService productsService, ChatService chatService) {
        this.productsService = productsService;
        this.chatService = chatService;
    }

    @GetMapping
    public String getAll(Model model, @AuthenticationPrincipal Account account) {
        model.addAttribute("account", account);
        model.addAttribute("products", productsService.findAll());
        model.addAttribute("chats",chatService.findAll());

        return "pages/products";
    }

    @PostMapping("/a")
    @PreAuthorize("hasRole('ADMIN')")
    public String chatAdd(@ModelAttribute Chat chat) {
        chatService.add(chat);
        return "redirect:/";
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

    @GetMapping("/buy")
    @PreAuthorize("hasRole('ADMIN')")
    public String buyForm() {return "pages/product-buy";}


    @PostMapping("/buy")
    @PreAuthorize("hasRole('ADMIN')")
    public String buy(@ModelAttribute Product product) {
        productsService.buy(product);
        return "redirect:/";
    }

    @GetMapping("/{id}") // {id} -> /notes/1
    @PreAuthorize("hasRole('ADMIN')")
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
