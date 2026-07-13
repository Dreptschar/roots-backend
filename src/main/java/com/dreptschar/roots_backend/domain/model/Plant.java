package com.dreptschar.roots_backend.domain.model;

import java.time.LocalDateTime;

public record Plant(
    Long id,
    String name,
    String species,
    Long roomId,
    String notes,
    String photoPath,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
