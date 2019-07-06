package com.github.oliverpavey.solarsystem.repo;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.github.oliverpavey.solarsystem.model.Star;

public interface StarRepository extends Neo4jRepository<Star,Long> {
	
	Optional<Star> findByName(String name);
}
