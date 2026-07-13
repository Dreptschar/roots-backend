package com.dreptschar.plantmanagerbackend.adapter.inbound.rest;

import com.dreptschar.plantmanagerbackend.adapter.inbound.rest.api.PlantsApi;
import com.dreptschar.plantmanagerbackend.adapter.inbound.rest.api.model.PlantSummaryResponse;
import com.dreptschar.plantmanagerbackend.domain.model.Plant;
import com.dreptschar.plantmanagerbackend.domain.service.PlantQueryService;
import jakarta.annotation.Generated;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-07-13T15:35:07.946460567Z[Etc/UTC]", comments = "Generator version: 7.13.0")
@RestController
@RequestMapping("${openapi.plantManagerBackend.base-path:/api}")
public class PlantsApiController implements PlantsApi {

    private final PlantQueryService plantQueryService;

    public PlantsApiController(PlantQueryService plantQueryService) {
        this.plantQueryService = plantQueryService;
    }

    @Override
    public ResponseEntity<List<PlantSummaryResponse>> plantsGet() {
        List<PlantSummaryResponse> plants = plantQueryService.getAllPlants()
                .stream()
                .map(PlantsApiController::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(plants);
    }

    private static PlantSummaryResponse toResponse(Plant plant) {
        return new PlantSummaryResponse(
                plant.id(),
                plant.name(),
                plant.species(),
                plant.roomId(),
                plant.notes(),
                plant.photoPath(),
                plant.createdAt(),
                plant.updatedAt()
        );
    }
}
