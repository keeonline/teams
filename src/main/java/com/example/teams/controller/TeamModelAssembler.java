package com.example.teams.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.teams.model.Team;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class TeamModelAssembler implements RepresentationModelAssembler<Team, EntityModel<Team>> {

    @Override
    public EntityModel<Team> toModel(Team employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(TeamController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(TeamController.class).all()).withRel("employees"));
    }
}
