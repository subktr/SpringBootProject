package com.sb.movielibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sb.movielibrary.model.Movie;
import com.sb.movielibrary.service.MovieService;

@Controller
public class MovieController {
	@Autowired
	private MovieService movieService;

	// list of all movies
	@GetMapping("/")
	public String viewHomepage(Model model) {
		//model.addAttribute("listOfMovies", movieService.getAllMovies());
		//return "index";	

		//return findPaginated(1, model);

		  return findPaginated(1,"movieId","asc", model);
		 
	}

	@GetMapping("/showNewMovieForm")
	public String showNewMovieForm(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		return "new_movie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {
		// save movie to database
		movieService.saveMovie(movie);
		return "redirect:/";

	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get movie from the service
		Movie movie = movieService.getMovieById(id);
		// set movie as a model attribute to pre-populate the form
		model.addAttribute("movie", movie);
		return "update_movie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value = "id") long id) {
		// call delete movie method
		this.movieService.deleteMovieById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
//			Model model) {
//		int pageSize = 5;
//
//		Page<Movie> page = movieService.findPaginated(pageNo, pageSize);
//		List<Movie> listOfMovies = page.getContent();
//		
//
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//
//		model.addAttribute("listOfMovies", listOfMovies);
//		
//		return "index";
//	}
	public String findPaginated
			(@PathVariable(value = "pageNo") int pageNo,
		    @RequestParam("sortField") String sortField,
		    @RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;	
		Page < Movie > page = movieService.findPaginated(pageNo, pageSize, sortField, sortDir);		
		List<Movie> listOfMovies = page.getContent();
		System.out.println(listOfMovies);

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listOfMovies", listOfMovies);
		
		return "index";
	}
	
}
