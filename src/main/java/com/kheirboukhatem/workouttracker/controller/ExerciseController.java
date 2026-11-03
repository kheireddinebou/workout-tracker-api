package com.kheirboukhatem.workouttracker.controller;

import com.kheirboukhatem.workouttracker.domain.dto.ExerciseDto.*;
import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<List<ExerciseResponse>> getAllExercises() {
        return ResponseEntity.ok(exerciseService.getAllExercises());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ExerciseResponse>> getExercisesByCategory(@PathVariable ExerciseCategory category) {
        return ResponseEntity.ok(exerciseService.getExercisesByCategory(category));
    }

    @PostMapping
    public ResponseEntity<ExerciseResponse> createExercise(@RequestBody ExerciseCreateRequest request) {
        return new ResponseEntity<>(exerciseService.createExercise(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExerciseResponse> updateExercise(
            @PathVariable Long id,
            @RequestBody ExerciseUpdateRequest request
    ) {
        return ResponseEntity.ok(exerciseService.updateExercise(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return ResponseEntity.noContent().build();
    }
}