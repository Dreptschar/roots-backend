package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.api.PlantsApi;
import com.dreptschar.roots_backend.model.ActionPlanCreateRequest;
import com.dreptschar.roots_backend.model.ActionPlanResponse;
import com.dreptschar.roots_backend.model.PlantActionCreateRequest;
import com.dreptschar.roots_backend.model.PlantActionResponse;
import com.dreptschar.roots_backend.model.PlantCreateRequest;
import com.dreptschar.roots_backend.model.PlantDetailResponse;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import com.dreptschar.roots_backend.model.PlantUpdateRequest;
import jakarta.annotation.Generated;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
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

  @Override
  public ResponseEntity<List<PlantSummaryResponse>> plantsGet() {
    return ResponseEntity.ok(MockApiData.plants());
  }

  @Override
  public ResponseEntity<PlantDetailResponse> plantsPost(PlantCreateRequest plantCreateRequest) {
    PlantDetailResponse plant = MockApiData.plantDetail(99L);
    plant.setName(plantCreateRequest.getName());
    plant.setSpecies(plantCreateRequest.getSpecies());
    plant.setRoomId(plantCreateRequest.getRoomId());
    plant.setNotes(plantCreateRequest.getNotes() != null ? plantCreateRequest.getNotes() : "");
    plant.setPhotoPath(plantCreateRequest.getPhotoPath() != null ? plantCreateRequest.getPhotoPath() : "");
    plant.setRoom(MockApiData.room(plantCreateRequest.getRoomId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(plant);
  }

  @Override
  public ResponseEntity<Void> plantsPlantIdDelete(Long plantId) {
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<PlantDetailResponse> plantsPlantIdGet(Long plantId) {
    return ResponseEntity.ok(MockApiData.plantDetail(plantId));
  }

  @Override
  public ResponseEntity<PlantDetailResponse> plantsPlantIdPatch(Long plantId, PlantUpdateRequest plantUpdateRequest) {
    PlantDetailResponse plant = MockApiData.plantDetail(plantId);
    if (plantUpdateRequest.getName() != null) {
      plant.setName(plantUpdateRequest.getName());
    }
    if (plantUpdateRequest.getSpecies() != null) {
      plant.setSpecies(plantUpdateRequest.getSpecies());
    }
    if (plantUpdateRequest.getRoomId() != null) {
      plant.setRoomId(plantUpdateRequest.getRoomId());
      plant.setRoom(MockApiData.room(plantUpdateRequest.getRoomId()));
    }
    if (plantUpdateRequest.getNotes() != null) {
      plant.setNotes(plantUpdateRequest.getNotes());
    }
    if (plantUpdateRequest.getPhotoPath() != null) {
      plant.setPhotoPath(plantUpdateRequest.getPhotoPath());
    }
    return ResponseEntity.ok(plant);
  }

  @Override
  public ResponseEntity<List<ActionPlanResponse>> plantsPlantIdActionPlansGet(Long plantId) {
    return ResponseEntity.ok(MockApiData.actionPlans(plantId));
  }

  @Override
  public ResponseEntity<ActionPlanResponse> plantsPlantIdActionPlansPost(
      Long plantId, ActionPlanCreateRequest actionPlanCreateRequest) {
    ActionPlanResponse actionPlan = MockApiData.actionPlan(99L, plantId);
    actionPlan.setActionTypeId(actionPlanCreateRequest.getActionTypeId());
    actionPlan.setIntervalDays(actionPlanCreateRequest.getIntervalDays());
    actionPlan.setLastPerformedAt(actionPlanCreateRequest.getLastPerformedAt());
    actionPlan.setActive(
        actionPlanCreateRequest.getActive() == null || actionPlanCreateRequest.getActive());
    actionPlan.setNotes(actionPlanCreateRequest.getNotes());
    return ResponseEntity.status(HttpStatus.CREATED).body(actionPlan);
  }

  @Override
  public ResponseEntity<List<PlantActionResponse>> plantsPlantIdActionsGet(Long plantId) {
    return ResponseEntity.ok(MockApiData.plantActions(plantId));
  }

  @Override
  public ResponseEntity<PlantActionResponse> plantsPlantIdActionsPost(
      Long plantId, PlantActionCreateRequest plantActionCreateRequest) {
    PlantActionResponse action = MockApiData.plantAction(99L, plantId);
    action.setActionTypeId(plantActionCreateRequest.getActionTypeId());
    action.setActionPlanId(plantActionCreateRequest.getActionPlanId());
    action.setPerformedAt(
        plantActionCreateRequest.getPerformedAt() != null
            ? plantActionCreateRequest.getPerformedAt()
            : LocalDateTime.of(2026, 7, 13, 11, 45));
    action.setNotes(plantActionCreateRequest.getNotes());
    return ResponseEntity.status(HttpStatus.CREATED).body(action);
  }
}
