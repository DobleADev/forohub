package com.dobleadev.forohub.domain.course;

public record DataListCourse(
        Long id,
        String name,
        Category category
) {
    public DataListCourse(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
