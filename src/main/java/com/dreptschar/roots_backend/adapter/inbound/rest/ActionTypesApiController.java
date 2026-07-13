package com.dreptschar.roots_backend.adapter.inbound.rest;

import com.dreptschar.roots_backend.api.ActionTypesApi;
import com.dreptschar.roots_backend.model.ActionTypeResponse;
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
public class ActionTypesApiController implements ActionTypesApi {

  @Override
  public ResponseEntity<List<ActionTypeResponse>> actionTypesGet() {
    return ResponseEntity.ok(MockApiData.actionTypes());
  }
}
