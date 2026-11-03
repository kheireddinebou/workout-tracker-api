package com.kheirboukhatem.workouttracker.domain.entities;

import com.kheirboukhatem.workouttracker.domain.enums.ExerciseCategory;
import com.kheirboukhatem.workouttracker.domain.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private ExerciseCategory category;

    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;
}