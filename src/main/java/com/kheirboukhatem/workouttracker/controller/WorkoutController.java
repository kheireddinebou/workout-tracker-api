package com.kheirboukhatem.workouttracker.controller;

import com.kheirboukhatem.workouttracker.domain.dto.WorkoutDto.*;
import com.kheirboukhatem.workouttracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<WorkoutResponse> createWorkout(
            @RequestBody WorkoutCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return new ResponseEntity<>(workoutService.createWorkout(request, userDetails.getUsername()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkoutResponse> updateWorkout(
            @PathVariable Long id,
            @RequestBody WorkoutUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(workoutService.updateWorkout(id, request, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        workoutService.deleteWorkout(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WorkoutResponse>> listWorkouts(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(workoutService.getUserWorkouts(userDetails.getUsername()));
    }

    @GetMapping("/reports")
    public ResponseEntity<WorkoutReportResponse> generateReport(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(workoutService.generateReport(userDetails.getUsername()));
    }
}