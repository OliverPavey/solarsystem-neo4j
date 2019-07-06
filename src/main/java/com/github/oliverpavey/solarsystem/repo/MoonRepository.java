package com.github.oliverpavey.solarsystem.repo;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.github.oliverpavey.solarsystem.model.Moon;

public interface MoonRepository extends Neo4jRepository<Moon,Long> {

	Optional<Moon> findByName(String name);
}
