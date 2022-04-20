package main;

record Movie(String title, String year, String rating, String urlImage) implements Content {

	@Override
	public int compareTo(Content content) {
		return this.rating().compareTo(content.rating());
	}

	
}