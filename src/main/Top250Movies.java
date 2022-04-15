package main;

import static env.EnvVariables.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.ExecutionException;

import org.json.*;


public class Top250Movies {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException{

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://imdb-api.com/en/API/Top250Movies/" + API_KEY))
				.build();

		var responseFuture = client.sendAsync(request, BodyHandlers.ofString());

		var response = responseFuture.get();

		JSONObject top250 = new JSONObject(response.body());

		JSONArray items = (JSONArray) top250.get("items");



		for (int i = 0; i < items.length(); i++) {
			JSONObject item = (JSONObject) items.get(i);
			System.out.println(i+1 + ": " + item.get("fullTitle"));
		}

	}

}