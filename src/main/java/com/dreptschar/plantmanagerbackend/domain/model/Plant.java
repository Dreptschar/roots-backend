package com.dreptschar.plantmanagerbackend.domain.model;

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
