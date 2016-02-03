package com.wang.myDB;

public class Album {
	
	/**
	 * Data fields
	 */
	private int uniqueID;
	private String title;
	private String genre;
	private int score;
	private int released;
	private String name;
	private String nation;
	/**
	 * Default constructor 1
	 */
	public Album() {}
	
	/**
	 * Constructor 2
	 * @param title
	 * @param score
	 * @param genre
	 * @param released
	 */
	public Album(String title, int score, String genre, int released) {
		this.title = title;
		this.score = score;
		this.genre = genre;
		this.released = released;
		
		
	}
	/*private List<String> artists;
	// skapa klass Artist och ändra typen till Artist istället för String. 
	//I så fall behöver du inte metoderna nedan: getArtist och setAuthors--> 
	//de ska istället vara i Artist klassen
	

	public String getArtists() {
		
		String result = "";
		for(String artist:artists)
			result += artist + ",";
		return result.substring(0, result.length() - 1);	// Discard the last ","
	}
	
	public void setArtists(String artists){
		this.artists = Arrays.asList(artists.split(","));
		
	}*/
	
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getReleased() {
		return released;
	}
	public void setReleased(int releasedYear) {
		this.released = releasedYear;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	

	@Override
	public String toString() {
		return "Album [ID=" + uniqueID + ";  Title=" + title  + ";  Score=" + score +";  Released=" + released + ";  Genre="
				+ genre + ";  Artist=" + name + ";  Nationality=" + nation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + released;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Album other = (Album) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (released != other.released)
			return false;
		return true;
	}
	
	
	
	 
	
}
