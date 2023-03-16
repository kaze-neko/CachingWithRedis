package com.kazeneko.CashingWithRedis;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@EnableCaching
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;
    @GetMapping
    public List<Note> getAllNotes() {
        return noteRepository.getAllNotes();
    }
    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Note")
    public Note getNoteById(@PathVariable int id) {
        return noteRepository.getNoteById(id);
    }
    @PostMapping
    public Note saveNote(@RequestBody Note note) {
        return noteRepository.saveNote(note);
    }
    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Note")
    public List<Note> deleteNote(@PathVariable int id) {
        return noteRepository.deleteNote(id);
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseError noteNotFound(NoSuchElementException exception, HttpServletRequest request) {
        return new ResponseError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), exception.getMessage(), request.getRequestURI());
    }
}
