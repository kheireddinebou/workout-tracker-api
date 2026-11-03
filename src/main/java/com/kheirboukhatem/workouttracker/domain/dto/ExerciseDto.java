package com.kheirboukhatem.workouttracker.domain.dto;

import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.domain.enums.MuscleGroup;

public class ExerciseDto {
    public record ExerciseResponse(
            Long id,
            String name,
            String description,
            ExerciseCategory category,
            MuscleGroup muscleGroup
    ) {}

    public record ExerciseCreateRequest(
            String name,
            String description,
            ExerciseCategory category,
            MuscleGroup muscleGroup
    ) {}

    public record ExerciseUpdateRequest(
            String name,
            String description,
            ExerciseCategory category,
            MuscleGroup muscleGroup
    ) {}
}