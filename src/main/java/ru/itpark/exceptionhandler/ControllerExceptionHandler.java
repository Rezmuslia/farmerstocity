package ru.itpark.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itpark.exception.NoteNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(NoteNotFoundException.class)
  public String handleNoteNotFound(
      Model model, NoteNotFoundException e
  ) {
    // model доступна так же, как и в контроллере
    model.addAttribute("message", e.getMessage());
    return "note-not-found";
  }
}
