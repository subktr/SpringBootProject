package com.sb.movielibrary.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Movie {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id	
	private Long movieId;
	private String movieName;
	private int movieYear;
	private String movieLang;
	private String movieGenres;
	private String movieCountry;
}
