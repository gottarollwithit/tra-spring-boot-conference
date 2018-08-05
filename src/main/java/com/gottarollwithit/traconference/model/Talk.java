package com.gottarollwithit.traconference.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;


@Getter
@Setter
@Entity(name = "TALK")
@Table(name = "TALK")
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min = 3, max = 255)
    @Column(nullable = false)
    private String name;

    @Min(5)
    @Column(nullable = false)
    private Integer duration;

    public Talk() {
    }

    public Talk(@Length(min = 3, max = 255) String name, @Min(5) Integer duration) {
        this.name = name;
        this.duration = duration;
    }
}
