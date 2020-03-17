package htam.rest.api.skeleton.demo.v1.service;

import htam.rest.api.skeleton.demo.v1.model.Note;
import htam.rest.api.skeleton.demo.v1.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService {
    private DemoRepository demoRepository;

    @Autowired
    public DemoServiceImpl (DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public List<Note> findAllProduct() {
        return (List<Note>) demoRepository.findAll();
    }

    @Override
    public Optional<Note> findById(Integer id) {
        return demoRepository.findById(id);
    }

    @Override
    public void save(Note product) {
        demoRepository.save(product);
    }

    @Override
    public void remove(Note product) {
        demoRepository.delete(product);
    }
}
