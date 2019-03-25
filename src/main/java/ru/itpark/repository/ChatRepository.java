package ru.itpark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
}