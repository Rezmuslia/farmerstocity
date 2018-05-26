package ru.itpark.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Note;
import ru.itpark.service.NotesService;

import java.security.Principal;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public String getAll(Model model, Authentication authentication) {
        model.addAttribute("authorities", authentication.getAuthorities());
        model.addAttribute("notes", notesService.findAll());

        return "notes";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addForm() {
        return "note-add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String add(@ModelAttribute Note note, @RequestParam MultipartFile image) {
        notesService.add(note, image);

        return "redirect:/notes";
    }

    @GetMapping("/{id}") // {id} -> /notes/1
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("note", notesService.findById(id));

        return "note";
    }

    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        notesService.removeById(id);

        return "redirect:/notes";
    }
}
