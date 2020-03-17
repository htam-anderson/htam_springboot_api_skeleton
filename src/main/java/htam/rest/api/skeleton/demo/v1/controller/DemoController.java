package htam.rest.api.skeleton.demo.v1.controller;

import htam.rest.api.skeleton.demo.v1.model.Note;
import htam.rest.api.skeleton.demo.v1.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
public class DemoController {
    private DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Map<String, String> checkHealth() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Server is running");

        return response;
    }

    @GetMapping("/v1/notes")
    public ResponseEntity<List<Note>> getAllNote() {
        List<Note> notes = demoService.findAllProduct();
        if (notes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @GetMapping("/v1/notes/{id}")
    public ResponseEntity<Note> getNoteById(
            @PathVariable("id") Integer id) {
        Optional<Note> note = demoService.findById(id);
        if (!note.isPresent()) {
            return new ResponseEntity<>(note.get(),HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(note.get(), HttpStatus.OK);
    }

    @PostMapping("/v1/notes")
    public ResponseEntity<Note> postNote(
            @RequestBody Note note,
            UriComponentsBuilder builder) {
        demoService.save(note);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("v1/note/{id}")
                .buildAndExpand(note.getExampleId()).toUri());

        return new ResponseEntity<>(note, HttpStatus.CREATED);
    }

    @PutMapping("/v1/notes/{id}")
    public ResponseEntity<Note> putNote(
            @PathVariable("id") Integer id,
            @RequestBody Note inputNote) {
        Optional<Note> currentNote = demoService.findById(id);

        if (!currentNote.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentNote.get().setExampleNote(inputNote.getExampleNote());
        demoService.save(currentNote.get());

        return new ResponseEntity<>(currentNote.get(), HttpStatus.OK);
    }

    @DeleteMapping("/v1/notes/{id}")
    public ResponseEntity<Note> deleteNote(
            @PathVariable("id") Integer id) {
        Optional<Note> product = demoService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        demoService.remove(product.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
