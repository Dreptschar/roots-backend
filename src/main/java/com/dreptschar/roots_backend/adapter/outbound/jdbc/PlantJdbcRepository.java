package com.dreptschar.roots_backend.adapter.outbound.jdbc;

import com.dreptschar.roots_backend.domain.model.Plant;
import com.dreptschar.roots_backend.ports.outbound.PlantReadPort;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PlantJdbcRepository implements PlantReadPort {

  private static final RowMapper<Plant> PLANT_ROW_MAPPER =
      (rs, rowNum) ->
          new Plant(
              rs.getLong("id"),
              rs.getString("name"),
              rs.getString("species"),
              rs.getObject("room_id", Long.class),
              rs.getString("notes"),
              rs.getString("photo_path"),
              toLocalDateTime(rs.getTimestamp("created_at")),
              toLocalDateTime(rs.getTimestamp("updated_at")));

  private final JdbcTemplate jdbcTemplate;

  public PlantJdbcRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Plant> findAll() {
    return jdbcTemplate.query(
        """
                select id, name, species, room_id, notes, photo_path, created_at, updated_at
                from plants
                order by created_at desc, id desc
                """,
        PLANT_ROW_MAPPER);
  }

  private static java.time.LocalDateTime toLocalDateTime(Timestamp timestamp) {
    return timestamp == null ? null : timestamp.toLocalDateTime();
  }
}
