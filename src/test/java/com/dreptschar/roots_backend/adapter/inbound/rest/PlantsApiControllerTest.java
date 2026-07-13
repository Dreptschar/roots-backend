package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.adapter.inbound.rest.mapper.PlantSummaryResponseMapper;
import com.dreptschar.roots_backend.domain.model.Plant;
import com.dreptschar.roots_backend.domain.service.PlantQueryService;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlantsApiControllerTest {

  @Mock private PlantQueryService plantQueryService;

  @Mock private PlantSummaryResponseMapper plantSummaryResponseMapper;

  @InjectMocks private PlantsApiController controller;

  @Test
  void plantsGetReturnsMappedPlants() {
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
    PlantSummaryResponse response =
        new PlantSummaryResponse(
            42L,
            "Monstera",
            "Monstera deliciosa",
            7L,
            "Needs indirect light",
            "/images/monstera.png",
            createdAt,
            updatedAt);

    when(plantQueryService.getAllPlants()).thenReturn(List.of(plant));
    when(plantSummaryResponseMapper.toResponse(plant)).thenReturn(response);

    // Act
    ResponseEntity<List<PlantSummaryResponse>> result = controller.plantsGet();

    // Assert
    assertThat(result.getStatusCode().value()).isEqualTo(200);
    assertThat(result.getBody()).containsExactly(response);
    verify(plantQueryService).getAllPlants();
    verify(plantSummaryResponseMapper).toResponse(plant);
  }
}
