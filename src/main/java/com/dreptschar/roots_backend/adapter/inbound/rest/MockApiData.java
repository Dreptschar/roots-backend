package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.model.ActionPlanResponse;
import com.dreptschar.roots_backend.model.ActionTypeResponse;
import com.dreptschar.roots_backend.model.PlantActionResponse;
import com.dreptschar.roots_backend.model.PlantDetailResponse;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import com.dreptschar.roots_backend.model.RoomResponse;
import java.time.LocalDateTime;
import java.util.List;

final class MockApiData {

  private static final LocalDateTime CREATED_AT = LocalDateTime.of(2026, 7, 1, 9, 0);
  private static final LocalDateTime UPDATED_AT = LocalDateTime.of(2026, 7, 13, 12, 30);

  private MockApiData() {}

  static List<ActionTypeResponse> actionTypes() {
    return List.of(
        new ActionTypeResponse(1L, "water", "Water plant", "water-drop"),
        new ActionTypeResponse(2L, "fertilize", "Fertilize", "sprout"),
        new ActionTypeResponse(3L, "rotate", "Rotate pot", "rotate-right"),
        new ActionTypeResponse(4L, "prune", "Prune leaves", "scissors"));
  }

  static List<RoomResponse> rooms() {
    return List.of(
        new RoomResponse(1L, "Living room", "Bright window near the sofa", CREATED_AT, UPDATED_AT),
        new RoomResponse(2L, "Kitchen", "Humidity friendly spot", CREATED_AT.plusDays(1), UPDATED_AT),
        new RoomResponse(3L, "Bedroom", "Low light corner", CREATED_AT.plusDays(2), UPDATED_AT));
  }

  static RoomResponse room(Long roomId) {
    return rooms().stream().filter(room -> room.getId().equals(roomId)).findFirst()
        .orElseGet(
            () ->
                new RoomResponse(
                    roomId,
                    "Room " + roomId,
                    "Mock room description",
                    CREATED_AT,
                    UPDATED_AT));
  }

  static List<PlantSummaryResponse> plants() {
    return List.of(
        new PlantSummaryResponse(
            1L,
            "Monstera",
            "Monstera deliciosa",
            1L,
            "Needs weekly watering",
            "/images/plants/monstera.jpg",
            CREATED_AT,
            UPDATED_AT),
        new PlantSummaryResponse(
            2L,
            "Snake Plant",
            "Dracaena trifasciata",
            2L,
            "Very tolerant, water sparingly",
            "/images/plants/snake-plant.jpg",
            CREATED_AT.plusDays(1),
            UPDATED_AT));
  }

  static PlantSummaryResponse plantSummary(Long plantId) {
    return plants().stream().filter(plant -> plant.getId().equals(plantId)).findFirst()
        .orElseGet(
            () ->
                new PlantSummaryResponse(
                    plantId,
                    "Mock Plant " + plantId,
                    "Ficus elastica",
                    1L,
                    "Mock notes",
                    "/images/plants/mock.jpg",
                    CREATED_AT,
                    UPDATED_AT));
  }

  static ActionPlanResponse actionPlan(Long id, Long plantId) {
    return new ActionPlanResponse(
        id,
        plantId,
        1L,
        7,
        CREATED_AT.minusDays(2),
        UPDATED_AT.plusDays(5),
        true,
        "Weekly watering plan",
        CREATED_AT,
        UPDATED_AT);
  }

  static List<ActionPlanResponse> actionPlans(Long plantId) {
    return List.of(actionPlan(1L, plantId), actionPlan(2L, plantId));
  }

  static PlantActionResponse plantAction(Long id, Long plantId) {
    return new PlantActionResponse(
        id,
        plantId,
        1L,
        10L,
        UPDATED_AT.minusDays(1),
        "Watered after a dry week");
  }

  static List<PlantActionResponse> plantActions(Long plantId) {
    return List.of(plantAction(1L, plantId), plantAction(2L, plantId));
  }

  static PlantDetailResponse plantDetail(Long plantId) {
    PlantSummaryResponse summary = plantSummary(plantId);
    PlantDetailResponse detail =
        new PlantDetailResponse(
            summary.getId(),
            summary.getName(),
            summary.getSpecies(),
            summary.getRoomId(),
            summary.getNotes(),
            summary.getPhotoPath(),
            summary.getCreatedAt(),
            summary.getUpdatedAt());
    detail.setRoom(room(summary.getRoomId()));
    detail.setActionPlans(actionPlans(plantId));
    detail.setActions(plantActions(plantId));
    return detail;
  }
}
