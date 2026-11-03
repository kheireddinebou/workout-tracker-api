package com.kheirboukhatem.workouttracker.repository;

import com.kheirboukhatem.workouttracker.domain.entities.User;
import com.kheirboukhatem.workouttracker.domain.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserOrderByScheduledDateAsc(User user);
    Optional<Workout> findByIdAndUser(Long id, User user);
}