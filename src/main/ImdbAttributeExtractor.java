package main;

import java.util.ArrayList;
import java.util.List;

public class ImdbAttributeExtractor {
	
	private String json;

	public ImdbAttributeExtractor(String json) {
		this.json = json;
	}
 	
	List<String> extractAttribute(String attributeToExtract) {
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
	
	public List<Movie> getListOfMovies() {
	
		List<String> titles = extractAttribute("title");
		List<String> years = extractAttribute("year");
		List<String> images = extractAttribute("image");
		List<String> ratings = extractAttribute("imDbRating");
		List<Movie> movies = new ArrayList<>();
		
		for (int i=0; i < titles.size(); i++) {
			movies.add(new Movie(titles.get(i), years.get(i), images.get(i), ratings.get(i)));
		}
		
		return movies;
	
	}
}
