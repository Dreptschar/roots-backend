package com.dreptschar.roots_backend.domain.service;

import com.dreptschar.roots_backend.domain.model.Plant;
import com.dreptschar.roots_backend.ports.outbound.PlantReadPort;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PlantQueryService {

  private final PlantReadPort plantReadPort;

  public PlantQueryService(PlantReadPort plantReadPort) {
    this.plantReadPort = plantReadPort;
  }

  public List<Plant> getAllPlants() {
    return plantReadPort.findAll();
  }
}
