package com.github.oliverpavey.solarsystem.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import lombok.Data;

@NodeEntity
@Data
public class Star {
	
	@Id @GeneratedValue
	Long id;

	@Property
	String name;
}
