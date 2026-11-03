package com.kheirboukhatem.workouttracker.repository;

import com.kheirboukhatem.workouttracker.domain.entities.Exercise;
import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.domain.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByCategory(ExerciseCategory category);
    List<Exercise> findByMuscleGroup(MuscleGroup muscleGroup);
    boolean existsByName(String name);
}