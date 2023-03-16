package com.kazeneko.CashingWithRedis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NoteRepository {
    public List<Note> getAllNotes() {
        ObjectMapper objectMapper = new ObjectMapper();
        File notesJSON = new File("notes.json");
        try {
            List<Note> notes = objectMapper.readValue(notesJSON, new TypeReference<List<Note>>() {});
            return notes;
        }  catch (DatabindException exception) {
            return new ArrayList<>();  // if json is empty have to return empty list
        } catch (IOException exception) {
            throw new JsonParseException("Wrong json-database status"); // if something else was wrong
        }
    }
    public Note getNoteById(int id) {
        List<Note> notes = getAllNotes();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                return notes.get(i);
            }
        }
        throw new NoSuchElementException("No note with transferred id was found");
    }
    public Note saveNote(Note note) {
        ObjectMapper objectMapper = new ObjectMapper();
        File notesJSON = new File("notes.json");
        List<Note> notes = getAllNotes();
        notes.add(note);
        try {
            objectMapper.writeValue(notesJSON, notes);
            return note;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Note> deleteNote(int id) {
        List<Note> notes = getAllNotes();
        System.out.println(notes.toString());
        Note temp = getNoteById(id);
        System.out.println(notes.remove(temp));
        System.out.println(notes.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        File notesJSON = new File("notes.json");
        try {
            objectMapper.writeValue(notesJSON, notes);
            return notes;
        } catch (IOException e) {
            throw new JsonParseException("Wrong json-database status");
        }
    }
}
