package main;

import static env.EnvVariables.*; // To access API_KEY

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Top250Movies {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + API_KEY))
				.build();

		var responseFuture = client.sendAsync(request, BodyHandlers.ofString());

		var response = responseFuture.get();
		
		String top250movies = response.body();
		
		List<String> titles = extractAttribute(top250movies, "title");
		List<String> years = extractAttribute(top250movies, "year");
		List<String> images = extractAttribute(top250movies, "image");
		List<String> ratings = extractAttribute(top250movies, "imDbRating");
		List<Movie> movies = new ArrayList<>();
		
		for (int i=0; i < titles.size(); i++) {
			movies.add(new Movie(titles.get(i), years.get(i), images.get(i), ratings.get(i)));
		}
		
		
		HTMLGenerator.generate(movies);


		
	}
	
	static List<String> extractAttribute(String json, String attributeToExtract) {
		List<String> attributes = new ArrayList<>();
		
		String[] items = json.split("\\},\\{");
		
		for (String item : items) {
			int start = item.indexOf(attributeToExtract + "\":\"") + (attributeToExtract.length()+3);
			int end = item.indexOf("\",", start);
			String attribute = item.substring(start, end);
			
			attributes.add(attribute);
		}
		
		return attributes;
	}

}