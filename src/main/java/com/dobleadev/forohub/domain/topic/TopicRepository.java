package com.dobleadev.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Long> {
//    @Query("SELECT t FROM Topic t WHERE t.active = TRUE")
    Page<Topic> findByActiveTrue(Pageable paginacion);
}
