package ru.itpark.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Chat;
import ru.itpark.repository.ChatRepository;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final Environment environment;

    public ChatServiceImpl(ChatRepository chatRepository, Environment environment) {
        this.chatRepository = chatRepository;
        this.environment = environment;
    }

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    @Override
    public void removeById(int id) {
        // FIXME: файл тоже нужно удалять
        chatRepository.deleteById(id);
    }

    @Override
    public void add(Chat chat) {
        Chat saved = chatRepository.save(chat);


        // TODO: 1. либо конвертировать всё в JPG
        // TODO: 2. выбирать нужные расширения (либо в заметке хранить расширение картинки, либо могу настроить сервер)


        // FIXME: фильтровать плохие файлы
//        try {
//            image.transferTo(
//                path.resolve(saved.getId() + ".jpg").toFile()
//            );
//        } catch (IOException e) {
//            // TODO: выкидывать собственный exception
//            e.printStackTrace();
    }
}


