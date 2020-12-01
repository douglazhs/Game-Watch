package br.com.GameWatch.main;

public class Genre {
	private String name;
	private String description;
	
	public Genre(String name, String description) {
		setDescription(description);
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
