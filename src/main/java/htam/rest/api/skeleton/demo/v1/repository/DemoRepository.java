package htam.rest.api.skeleton.demo.v1.repository;

import htam.rest.api.skeleton.demo.v1.model.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends CrudRepository<Note, Integer> {
}
