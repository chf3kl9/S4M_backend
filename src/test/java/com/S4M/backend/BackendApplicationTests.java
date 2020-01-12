package com.S4M.backend;

import com.S4M.backend.models.Movie;
import com.S4M.backend.repositories.MovieRepository;
import com.S4M.backend.services.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BackendApplicationTests {

	@Mock
	MovieRepository movieMockRepository;

	@InjectMocks
	private MovieService movieService;

	@Test
	public void createMovieValid(){
		Movie expected = new Movie(1, "Movie 1", "Movie 1", "Movie 1", "Url", null, null, null);
		Movie movie = new Movie("Movie 1", "Movie 1", "Movie 1", "Url");
		Mockito.when(movieMockRepository.save(Mockito.any(Movie.class))).thenReturn(expected);

		assertEquals(expected, movieService.insertMovie("Movie 1", "Movie 1", "Movie 1", "Url", ""));
		Mockito.verify(movieMockRepository, Mockito.times(1)).save(movie);
	}

	@Test
	public void contextLoads() {
	}

}
