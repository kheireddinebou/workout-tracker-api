package com.kheirboukhatem.workouttracker.domain.dto;

import com.kheirboukhatem.workouttracker.domain.enums.MuscleGroup;

import java.time.LocalDateTime;
import java.util.List;

public class WorkoutDto {

    public record WorkoutExerciseDto(
            Long exerciseId,
            int sets,
            int repetitions,
            double weight
    ) {}

    public record WorkoutCreateRequest(
            String title,
            String description,
            LocalDateTime scheduledDate,
            List<WorkoutExerciseDto> exercises
    ) {}

    public record WorkoutUpdateRequest(
            String title,
            String description,
            String comments,
            LocalDateTime scheduledDate,
            List<WorkoutExerciseDto> exercises
    ) {}

    public record WorkoutExerciseResponse(
            Long exerciseId,
            String exerciseName,
            MuscleGroup muscleGroup,
            int sets,
            int repetitions,
            double weight
    ) {}

    public record WorkoutResponse(
            Long id,
            String title,
            String description,
            String comments,
            LocalDateTime scheduledDate,
            List<WorkoutExerciseResponse> exercises
    ) {}

    public record WorkoutReportResponse(
            long totalWorkouts,
            double totalVolumeLifted, // Sum of (sets * repetitions * weight)
            long totalExercisesPerformed
    ) {}
}