package com.sb.movielibrary.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sb.movielibrary.model.Movie;
import com.sb.movielibrary.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public void saveMovie(Movie movie) {
		this.movieRepository.save(movie);

	}

	@Override
	public Movie getMovieById(long id) {
		Optional<Movie> optional = movieRepository.findById(id);
		Movie movie = null;
		if (optional.isPresent()) {
			movie = optional.get();
		} else {
			throw new RuntimeException(" Movie not found for id :: " + id);
		}

		return movie;
	}

	@Override
	public void deleteMovieById(long id) {
		this.movieRepository.deleteById(id);
		
	}

	@Override
//	public Page<Movie> findPaginated(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		 return this.movieRepository.findAll(pageable);
//	}
	public Page<Movie> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection) {		
		 Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			  Sort.by(sortField).descending();
		 Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
		 System.out.println("***********************************"+movieRepository.findAll(pageable));
		 return movieRepository.findAll(pageable);
	}

}
