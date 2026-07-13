package com.dreptschar.plantmanagerbackend.adapter.inbound.rest.api.model;

import java.time.LocalDateTime;

public record PlantSummaryResponse(
    Long id,
    String name,
    String species,
    Long roomId,
    String notes,
    String photoPath,
    LocalDateTime createdAt,
    LocalDateTime updatedAt) {}
