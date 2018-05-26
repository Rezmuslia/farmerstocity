package ru.itpark.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Note;

import java.util.List;

public interface NotesService {
    List<Note> findAll();

    Note findById(int id);

    void removeById(int id);

    void add(Note note, MultipartFile image);
}
