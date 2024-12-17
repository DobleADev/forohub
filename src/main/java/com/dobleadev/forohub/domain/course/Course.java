package com.dobleadev.forohub.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Boolean active;

    public Course(DataRegisterCourse course) {
        this.name = course.name();
        this.category = course.category();
        this.active = true;
    }

    public void updateData(DataUpdateCourse newCourse) {
        this.name = newCourse.name();
        this.category = newCourse.category();
    }

    public void deactivate() {
        this.active = false;
    }
}
