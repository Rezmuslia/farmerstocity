package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Note;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Integer> {
}
