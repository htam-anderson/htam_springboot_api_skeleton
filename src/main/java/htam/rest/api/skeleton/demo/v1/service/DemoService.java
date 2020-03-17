package htam.rest.api.skeleton.demo.v1.service;

import htam.rest.api.skeleton.demo.v1.model.Note;

import java.util.List;
import java.util.Optional;

public interface DemoService {
    List<Note> findAllProduct();
    Optional<Note> findById(Integer id);
    void save(Note note);
    void remove(Note note);
}
