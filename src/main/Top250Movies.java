package main;

import static env.EnvVariables.*; // To access API_KEY
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Top250Movies {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		String top250movies = new ImbdApiClient(API_KEY).getResponse();
		
		List<Movie> movies = new ImdbAttributeExtractor(top250movies).getListOfMovies();
		
		HTMLGenerator.generate(movies);
		
	}
}