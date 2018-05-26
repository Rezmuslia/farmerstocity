package ru.itpark.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Note;
import ru.itpark.exception.NoteNotFoundException;
import ru.itpark.repository.NotesRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;
    private final Environment environment;

    public NotesServiceImpl(NotesRepository notesRepository, Environment environment) {
        this.notesRepository = notesRepository;
        this.environment = environment;
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
        // FIXME: файл тоже нужно удалять
        notesRepository.deleteById(id);
    }

    @Override
    public void add(Note note, MultipartFile image) {
        Note saved = notesRepository.save(note);

        Path path = Paths.get(
            environment.getProperty("user.dir"),
            "upload",
            "static"
        );

        // TODO: 1. либо конвертировать всё в JPG
        // TODO: 2. выбирать нужные расширения (либо в заметке хранить расширение картинки, либо могу настроить сервер)


        // FIXME: фильтровать плохие файлы
        try {
            image.transferTo(
                path.resolve(saved.getId() + ".jpg").toFile()
            );
        } catch (IOException e) {
            // TODO: выкидывать собственный exception
            e.printStackTrace();
        }
    }
}
