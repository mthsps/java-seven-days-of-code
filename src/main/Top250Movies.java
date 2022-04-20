package main;

import static env.EnvVariables.*; // To access Api Keys 

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Top250Movies {

	public static void main(String[] args) throws InterruptedException, ExecutionException, NoSuchAlgorithmException {
		
		String top250movies = new ImbdApiClient(imdbApiKey).getResponse();
		
		List<Movie> movies = new ImdbJsonParser(top250movies).getList();
		
		HTMLGenerator.generate(movies);
		
	}
}