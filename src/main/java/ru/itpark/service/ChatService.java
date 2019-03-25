package ru.itpark.service;

import ru.itpark.domain.Chat;

import java.util.List;

public interface ChatService {
    List<Chat> findAll();

    void removeById(int id);

    void add(Chat chat);
}
