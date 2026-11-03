package com.kheirboukhatem.workouttracker.seeder;

import com.kheirboukhatem.workouttracker.domain.entities.Exercise;
import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.domain.enums.MuscleGroup;
import com.kheirboukhatem.workouttracker.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseDataSeeder implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) {
        if (exerciseRepository.count() == 0) {
            List<Exercise> defaultExercises = List.of(
                    Exercise.builder().name("Barbell Bench Press").description("Compound chest movement").category(ExerciseCategory.STRENGTH).muscleGroup(MuscleGroup.CHEST).build(),
                    Exercise.builder().name("Squat").description("Compound lower body movement").category(ExerciseCategory.STRENGTH).muscleGroup(MuscleGroup.BACK).build(),
                    Exercise.builder().name("Deadlift").description("Full body posterior chain exercise").category(ExerciseCategory.STRENGTH).muscleGroup(MuscleGroup.BACK).build(),
                    Exercise.builder().name("Pull-up").description("Bodyweight upper body pulling exercise").category(ExerciseCategory.STRENGTH).muscleGroup(MuscleGroup.BACK).build(),
                    Exercise.builder().name("Overhead Press").description("Shoulder strengthening exercise").category(ExerciseCategory.STRENGTH).muscleGroup(MuscleGroup.SHOULDERS).build(),
                    Exercise.builder().name("Running").description("Cardiovascular endurance training").category(ExerciseCategory.CARDIO).muscleGroup(MuscleGroup.FULL_BODY).build()
            );

            exerciseRepository.saveAll(defaultExercises);
            System.out.println(">>> Default exercises seeded successfully!");
        }
    }
}