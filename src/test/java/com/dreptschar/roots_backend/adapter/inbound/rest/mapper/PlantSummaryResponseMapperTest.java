package com.dreptschar.roots_backend.adapter.inbound.rest.mapper;

import com.dreptschar.roots_backend.domain.model.Plant;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class PlantSummaryResponseMapperTest {

  private final PlantSummaryResponseMapper mapper =
      Mappers.getMapper(PlantSummaryResponseMapper.class);

  @Test
  void mapsPlantToPlantSummaryResponse() {
    // Arrange
    LocalDateTime createdAt = LocalDateTime.of(2026, 7, 13, 10, 15, 30);
    LocalDateTime updatedAt = LocalDateTime.of(2026, 7, 13, 11, 20, 45);
    Plant plant =
        new Plant(
            42L,
            "Monstera",
            "Monstera deliciosa",
            7L,
            "Needs indirect light",
            "/images/monstera.png",
            createdAt,
            updatedAt);

    // Act
    PlantSummaryResponse response = mapper.toResponse(plant);

    // Assert
    assertThat(response.getId()).isEqualTo(42L);
    assertThat(response.getName()).isEqualTo("Monstera");
    assertThat(response.getSpecies()).isEqualTo("Monstera deliciosa");
    assertThat(response.getRoomId()).isEqualTo(7L);
    assertThat(response.getNotes()).isEqualTo("Needs indirect light");
    assertThat(response.getPhotoPath()).isEqualTo("/images/monstera.png");
    assertThat(response.getCreatedAt()).isEqualTo(createdAt);
    assertThat(response.getUpdatedAt()).isEqualTo(updatedAt);
  }
}
