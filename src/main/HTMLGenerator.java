package main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {

	static String head = """
			<html>
			<head>
				<meta charset=\"utf-8\">
				<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
				<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"
					+ "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">
			</head>
			<body>
			""";

	static String divTemplate = """
			<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
				<h4 class=\"card-header\">%s</h4>
				<div class=\"card-body\">
					<img class=\"card-img\" src=\"%s\" alt=\"%s\">
					<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
				</div>
			</div>
			""";

	static String closingTags = """
			</body>
			</html>
			""";

	public static void generate(List<? extends Content> movies) {

		try (PrintWriter writer = new PrintWriter("template.html")) {
			writer.println(String.format(head));
			
			for (Content movie : movies) {
				writer.println(String.format(divTemplate, movie.title(), movie.rating(), movie.title(),
						movie.urlImage(), movie.year()));
			}
			
			writer.println(String.format(closingTags));
			writer.close();
			
			System.out.println("HTML created");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
