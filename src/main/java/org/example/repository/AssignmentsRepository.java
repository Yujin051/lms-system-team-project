package org.example.repository;

import org.example.entity.Assignments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentsRepository extends JpaRepository<Assignments, Long> {
}
