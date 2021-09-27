package com.sb.movielibrary.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sb.movielibrary.model.Movie;
//This will provide a CRUD database operations for the Movie entity.
public interface MovieRepository extends JpaRepository<Movie, Long>{
	

}
