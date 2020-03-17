package htam.rest.api.skeleton.demo.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "example_model")
@Getter
@Setter
public class Note implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("example_id")
    @Column(name = "example_id")
    private Integer exampleId;

    @JsonProperty("example_note")
    @Column(name = "example_note")
    private String exampleNote;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    private Timestamp createdCate;

    @JsonProperty("updated_date")
    @Column(name = "updated_date")
    private Timestamp updateCate;
}
