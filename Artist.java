package com.wang.myDB;

public class Artist {
	
	private int id;
	private String name;
	private String nation;
	
	public Artist(){
	}
	
	public Artist(String name, String nation) {
		this.name = name;
		this.nation = nation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		return "Artist [id=" + id + ", name=" + name + ", nation=" + nation
				+ "]";
	}

}
