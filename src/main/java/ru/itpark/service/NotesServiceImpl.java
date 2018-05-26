package ru.itpark.service;

import org.springframework.stereotype.Service;
import ru.itpark.domain.Note;
import ru.itpark.exception.NoteNotFoundException;
import ru.itpark.repository.NotesRepository;

import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;

    public NotesServiceImpl(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public List<Note> findAll() {
        return notesRepository.findAll();
    }

    @Override
    public Note findById(int id) {
        return notesRepository.findById(id).orElseThrow(() -> new NoteNotFoundException(id + " not found"));  // ссылка на конструктор
    }

    @Override
    public void removeById(int id) {
        notesRepository.deleteById(id);
    }

    @Override
    public void add(Note note) {
        notesRepository.save(note);
    }
}
