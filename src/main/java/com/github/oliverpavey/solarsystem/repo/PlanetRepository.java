package com.github.oliverpavey.solarsystem.repo;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.github.oliverpavey.solarsystem.model.Planet;

public interface PlanetRepository extends Neo4jRepository<Planet,Long> {

	Optional<Planet> findByName(String name);
}
