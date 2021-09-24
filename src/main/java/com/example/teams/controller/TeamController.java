package com.example.teams.controller;

import com.example.teams.dao.TeamNotFoundException;
import com.example.teams.dao.TeamRepository;
import com.example.teams.model.Team;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
public class TeamController {

    private final TeamRepository repository;
    private final TeamModelAssembler assembler;

    public TeamController(TeamRepository repository, TeamModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/teams/{id}")
    EntityModel<Team> one(@PathVariable Long id) {

        Team team = repository.findById(id)
                .orElseThrow(() -> new TeamNotFoundException(id));

        return EntityModel.of(team,
                linkTo(methodOn(TeamController.class).one(id)).withSelfRel(),
                linkTo(methodOn(TeamController.class).all()).withRel("teams"));
    }

    @GetMapping("/teams")
    CollectionModel<EntityModel<Team>> all() {

        List<EntityModel<Team>> teams = repository.findAll()
                .stream()
                .map(team -> EntityModel.of(team,
                        linkTo(methodOn(TeamController.class).one(team.getId())).withSelfRel(),
                        linkTo(methodOn(TeamController.class).all()).withRel("teams")))
                .collect(Collectors.toList());

        return CollectionModel.of(teams, linkTo(methodOn(TeamController.class).all()).withSelfRel());
    }

    @PostMapping("/teams")
    ResponseEntity<?> newEmployee(@RequestBody Team newTeam) {

        EntityModel<Team> entityModel = assembler.toModel(repository.save(newTeam));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
}
