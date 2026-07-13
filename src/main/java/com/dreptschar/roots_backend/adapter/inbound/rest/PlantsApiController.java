package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.api.PlantsApi;
import com.dreptschar.roots_backend.adapter.inbound.rest.mapper.PlantSummaryResponseMapper;
import com.dreptschar.roots_backend.domain.service.PlantQueryService;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import jakarta.annotation.Generated;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    date = "2026-07-13T15:35:07.946460567Z[Etc/UTC]",
    comments = "Generator version: 7.13.0")
@RestController
@RequestMapping("${openapi.plantManagerBackend.base-path:/api}")
public class PlantsApiController implements PlantsApi {

  private final PlantQueryService plantQueryService;
  private final PlantSummaryResponseMapper plantSummaryResponseMapper;

  public PlantsApiController(
      PlantQueryService plantQueryService, PlantSummaryResponseMapper plantSummaryResponseMapper) {
    this.plantQueryService = plantQueryService;
    this.plantSummaryResponseMapper = plantSummaryResponseMapper;
  }

  @Override
  public ResponseEntity<List<PlantSummaryResponse>> plantsGet() {
    List<PlantSummaryResponse> plants =
        plantQueryService.getAllPlants().stream()
            .map(plantSummaryResponseMapper::toResponse)
            .toList();
    return ResponseEntity.ok(plants);
  }
}
