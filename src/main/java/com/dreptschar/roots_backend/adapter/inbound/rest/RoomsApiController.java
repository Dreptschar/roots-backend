package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.api.RoomsApi;
import com.dreptschar.roots_backend.model.RoomCreateRequest;
import com.dreptschar.roots_backend.model.RoomResponse;
import com.dreptschar.roots_backend.model.RoomUpdateRequest;
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
public class RoomsApiController implements RoomsApi {

  @Override
  public ResponseEntity<List<RoomResponse>> roomsGet() {
    return ResponseEntity.ok(MockApiData.rooms());
  }

  @Override
  public ResponseEntity<RoomResponse> roomsPost(RoomCreateRequest roomCreateRequest) {
    RoomResponse room =
        new RoomResponse(
            99L,
            roomCreateRequest.getName(),
            roomCreateRequest.getDescription(),
            LocalDateTime.of(2026, 7, 13, 12, 0),
            LocalDateTime.of(2026, 7, 13, 12, 0));
    return ResponseEntity.status(HttpStatus.CREATED).body(room);
  }

  @Override
  public ResponseEntity<Void> roomsRoomIdDelete(Long roomId) {
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<RoomResponse> roomsRoomIdGet(Long roomId) {
    return ResponseEntity.ok(MockApiData.room(roomId));
  }

  @Override
  public ResponseEntity<RoomResponse> roomsRoomIdPatch(Long roomId, RoomUpdateRequest roomUpdateRequest) {
    RoomResponse room = MockApiData.room(roomId);
    if (roomUpdateRequest.getName() != null) {
      room.setName(roomUpdateRequest.getName());
    }
    if (roomUpdateRequest.getDescription() != null) {
      room.setDescription(roomUpdateRequest.getDescription());
    }
    return ResponseEntity.ok(room);
  }
}
