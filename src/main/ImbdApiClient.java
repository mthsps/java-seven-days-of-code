package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

public class ImbdApiClient {
	
	private String apiKey;

	public ImbdApiClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getResponse() throws InterruptedException, ExecutionException {

		HttpClient client = HttpClient.newHttpClient();
	
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
				.build();
	
		var responseFuture = client.sendAsync(request, BodyHandlers.ofString());
	
		var response = responseFuture.get();
		
		return response.body();
	}

}
