package com.github.oliverpavey.solarsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import com.github.oliverpavey.solarsystem.model.Moon;
import com.github.oliverpavey.solarsystem.model.Planet;
import com.github.oliverpavey.solarsystem.model.Star;
import com.github.oliverpavey.solarsystem.repo.MoonRepository;
import com.github.oliverpavey.solarsystem.repo.PlanetRepository;
import com.github.oliverpavey.solarsystem.repo.StarRepository;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
@EnableNeo4jRepositories("com.github.oliverpavey")
public class SolarSystemNeo4jApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SolarSystemNeo4jApplication.class, args);
	}
	
	@Autowired
	StarRepository starRepository;
	
	@Autowired
	PlanetRepository planetRepository;

	@Autowired
	MoonRepository moonRepository;

	@Override
	public void run(String... args) throws Exception {
		log.info("Application started.");

		Star sol = starRepository.findByName("Sol").orElseGet(()-> new Star());
		sol.setName("Sol");
		sol = starRepository.save(sol);
		
		Planet mercury = createPlanet(sol, "Mercury", null);
		Planet venus = createPlanet(sol, "Venus", mercury);
		Planet earth = createPlanet(sol, "Earth", venus);
		Planet mars = createPlanet(sol, "Mars", earth);
		Planet jupiter = createPlanet(sol, "Jupiter", mars);
		Planet saturn = createPlanet(sol, "Saturn", jupiter);
		Planet uranus = createPlanet(sol, "Uranus", saturn);
		Planet neptune = createPlanet(sol, "Neptune", uranus);
		
		createMoons(earth, "Luna");
		createMoons(mars, "Phobos", "Deimos");
		// The outer planets have many moons, only a few of the moons are modelled here.
		createMoons(jupiter, "Io", "Europa", "Ganymede", "Callisto", "Lysithea");
		createMoons(saturn, "Titan", "Phoebe", "Prometheus", "Janus", "Pandora");
		createMoons(uranus, "Titania", "Oberon", "Miranda", "Ariel", "Umbriel");
		createMoons(neptune, "Triton");
	
		log.info("To see the data connect to the Neo4j browser "
				+ "(e.g. http://fledge:7474) and run \n"
				+ "MATCH (n) RETURN n;");
		
		log.info("Appliction terminated.");
	}

	private Planet createPlanet(Star star, String name, Planet preceeding) {
		Planet planet = planetRepository.findByName(name).orElseGet(()-> new Planet());
		planet.setStar(star);
		planet.setName(name);
		if (preceeding != null)
			planet.setPreceeding(preceeding);
		return planetRepository.save(planet);
	}

	private void createMoons(Planet planet, String... names) {
		for (String name : names) {
			createMoon(planet, name);
		}
	}

	private void createMoon(Planet planet, String name) {
		Moon moon = moonRepository.findByName(name).orElseGet(()-> new Moon());
		moon.setPlanet(planet);
		moon.setName(name);
		moonRepository.save(moon);
		
	}

}
