package com.kheirboukhatem.workouttracker.service;

import com.kheirboukhatem.workouttracker.domain.entities.Exercise;
import com.kheirboukhatem.workouttracker.domain.entities.User;
import com.kheirboukhatem.workouttracker.domain.entities.Workout;
import com.kheirboukhatem.workouttracker.domain.entities.WorkoutExercise;
import com.kheirboukhatem.workouttracker.domain.dto.WorkoutDto.*;
import com.kheirboukhatem.workouttracker.repository.ExerciseRepository;
import com.kheirboukhatem.workouttracker.repository.UserRepository;
import com.kheirboukhatem.workouttracker.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
    }

    @Transactional
    public WorkoutResponse createWorkout(WorkoutCreateRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);

        Workout workout = Workout.builder()
                .title(request.title())
                .description(request.description())
                .scheduledDate(request.scheduledDate())
                .user(user)
                .workoutExercises(new ArrayList<>())
                .build();

        if (request.exercises() != null) {
            for (WorkoutExerciseDto exDto : request.exercises()) {
                Exercise exercise = exerciseRepository.findById(exDto.exerciseId())
                        .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + exDto.exerciseId()));

                WorkoutExercise workoutExercise = WorkoutExercise.builder()
                        .workout(workout)
                        .exercise(exercise)
                        .sets(exDto.sets())
                        .repetitions(exDto.repetitions())
                        .weight(exDto.weight())
                        .build();

                workout.getWorkoutExercises().add(workoutExercise);
            }
        }

        Workout savedWorkout = workoutRepository.save(workout);
        return mapToResponse(savedWorkout);
    }

    @Transactional
    public WorkoutResponse updateWorkout(Long id, WorkoutUpdateRequest request, String userEmail) {
        User user = getUserByEmail(userEmail);
        Workout workout = workoutRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Workout not found or unauthorized"));

        if (request.title() != null) workout.setTitle(request.title());
        if (request.description() != null) workout.setDescription(request.description());
        if (request.comments() != null) workout.setComments(request.comments());
        if (request.scheduledDate() != null) workout.setScheduledDate(request.scheduledDate());

        if (request.exercises() != null) {
            workout.getWorkoutExercises().clear();
            for (WorkoutExerciseDto exDto : request.exercises()) {
                Exercise exercise = exerciseRepository.findById(exDto.exerciseId())
                        .orElseThrow(() -> new RuntimeException("Exercise not found with id: " + exDto.exerciseId()));

                WorkoutExercise workoutExercise = WorkoutExercise.builder()
                        .workout(workout)
                        .exercise(exercise)
                        .sets(exDto.sets())
                        .repetitions(exDto.repetitions())
                        .weight(exDto.weight())
                        .build();

                workout.getWorkoutExercises().add(workoutExercise);
            }
        }

        Workout updatedWorkout = workoutRepository.save(workout);
        return mapToResponse(updatedWorkout);
    }

    @Transactional
    public void deleteWorkout(Long id, String userEmail) {
        User user = getUserByEmail(userEmail);
        Workout workout = workoutRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Workout not found or unauthorized"));
        workoutRepository.delete(workout);
    }

    public List<WorkoutResponse> getUserWorkouts(String userEmail) {
        User user = getUserByEmail(userEmail);
        List<Workout> workouts = workoutRepository.findByUserOrderByScheduledDateAsc(user);
        return workouts.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public WorkoutReportResponse generateReport(String userEmail) {
        User user = getUserByEmail(userEmail);
        List<Workout> workouts = workoutRepository.findByUserOrderByScheduledDateAsc(user);

        long totalWorkouts = workouts.size();
        long totalExercises = 0;
        double totalVolume = 0.0;

        for (Workout w : workouts) {
            for (WorkoutExercise we : w.getWorkoutExercises()) {
                totalExercises++;
                totalVolume += (we.getSets() * we.getRepetitions() * we.getWeight());
            }
        }

        return new WorkoutReportResponse(totalWorkouts, totalVolume, totalExercises);
    }

    private WorkoutResponse mapToResponse(Workout workout) {
        System.out.println("size " + workout.getWorkoutExercises().size());
        List<WorkoutExerciseResponse> exerciseResponses = workout.getWorkoutExercises().stream()
                .map(we -> new WorkoutExerciseResponse(
                        we.getExercise().getId(),
                        we.getExercise().getName(),
                        we.getExercise().getMuscleGroup(),
                        we.getSets(),
                        we.getRepetitions(),
                        we.getWeight()
                ))
                .collect(Collectors.toList());

        return new WorkoutResponse(
                workout.getId(),
                workout.getTitle(),
                workout.getDescription(),
                workout.getComments(),
                workout.getScheduledDate(),
                exerciseResponses
        );
    }
}