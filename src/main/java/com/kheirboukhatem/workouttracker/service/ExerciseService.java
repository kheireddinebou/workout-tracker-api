package com.kheirboukhatem.workouttracker.service;

import com.kheirboukhatem.workouttracker.domain.entities.Exercise;
import com.kheirboukhatem.workouttracker.domain.dto.ExerciseDto.*;
import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseResponse> getAllExercises() {
        return exerciseRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<ExerciseResponse> getExercisesByCategory(ExerciseCategory category) {
        return exerciseRepository.findByCategory(category).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ExerciseResponse createExercise(ExerciseCreateRequest request) {
        if (exerciseRepository.existsByName(request.name())) {
            throw new RuntimeException("Exercise with name '" + request.name() + "' already exists!");
        }

        Exercise exercise = Exercise.builder()
                .name(request.name())
                .description(request.description())
                .category(request.category())
                .muscleGroup(request.muscleGroup())
                .build();

        Exercise saved = exerciseRepository.save(exercise);
        return mapToResponse(saved);
    }

    public ExerciseResponse updateExercise(Long id, ExerciseUpdateRequest request) {
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + id));

        if (request.name() != null) exercise.setName(request.name());
        if (request.description() != null) exercise.setDescription(request.description());
        if (request.category() != null) exercise.setCategory(request.category());
        if (request.muscleGroup() != null) exercise.setMuscleGroup(request.muscleGroup());

        Exercise updated = exerciseRepository.save(exercise);
        return mapToResponse(updated);
    }

    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new RuntimeException("Exercise not found with id: " + id);
        }
        exerciseRepository.deleteById(id);
    }

    private ExerciseResponse mapToResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getCategory(),
                exercise.getMuscleGroup()
        );
    }
}