package com.example.viva;

// class which contains video information
public class Video {

	int id;
	int folder_number;
	String name;
	String url;

	public Video() {

	}

	public Video(int id, int folder_number, String name, String url) {
		this.id = id;
		this.folder_number = folder_number;
		this.name = name;
		this.url = url;
	}

	public Video(int folder_number, String name, String url) {
		this.folder_number = folder_number;
		this.name = name;
		this.url = url;
	}

	public int getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getFolderNumber() {
		return this.folder_number;
	}

	public void setFolderNumber(int folder_number) {
		this.folder_number = folder_number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setPhoneNumber(String url) {
		this.url = url;
	}
}