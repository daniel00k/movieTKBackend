package me.danielaguilar.movieTK.repositories;

import me.danielaguilar.movieTK.models.Director;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Director, Long> {
}
