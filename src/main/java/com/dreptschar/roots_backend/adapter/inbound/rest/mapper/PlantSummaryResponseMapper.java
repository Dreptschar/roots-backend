package com.dreptschar.roots_backend.adapter.inbound.rest.mapper;

import com.dreptschar.roots_backend.domain.model.Plant;
import com.dreptschar.roots_backend.model.PlantSummaryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantSummaryResponseMapper {
  PlantSummaryResponse toResponse(Plant plant);
}
