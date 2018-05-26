package ru.itpark.service;

import ru.itpark.domain.Note;

import java.util.List;

public interface NotesService {
    List<Note> findAll();

    Note findById(int id);

    void removeById(int id);

    void add(Note note);
}
