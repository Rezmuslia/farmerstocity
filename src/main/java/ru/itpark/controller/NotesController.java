package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Note;
import ru.itpark.service.NotesService;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("notes", notesService.findAll());

        return "notes";
    }

    @GetMapping("/add")
    public String addForm() {
        return "note-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Note note) {
        notesService.add(note);

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
