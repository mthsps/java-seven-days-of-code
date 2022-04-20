package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

public class ImbdApiClient implements ApiClient {
	
	private String apiKey;

	public ImbdApiClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public String getResponse() {

		HttpClient client = HttpClient.newHttpClient();
	
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + apiKey))
				.build();
	
		var responseFuture = client.sendAsync(request, BodyHandlers.ofString());
	
		HttpResponse<String> response = null;
		
		try {
			response = responseFuture.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		return response.body();
	}

}
