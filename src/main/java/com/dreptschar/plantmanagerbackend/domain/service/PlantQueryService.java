package com.dreptschar.plantmanagerbackend.domain.service;

import com.dreptschar.plantmanagerbackend.domain.model.Plant;
import com.dreptschar.plantmanagerbackend.ports.outbound.PlantReadPort;
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
