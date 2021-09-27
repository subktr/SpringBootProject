package com.sb.movielibrary.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sb.movielibrary.model.Movie;
public interface MovieService {
	public List<Movie> getAllMovies();
	public void saveMovie(Movie movie);
	public Movie getMovieById(long id);
	public void deleteMovieById(long id);
	//Page<Movie> findPaginated(int pageNo, int pageSize);
	Page<Movie> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);

}
