package me.danielaguilar.movieTK.repositories;

import me.danielaguilar.movieTK.models.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {
}
