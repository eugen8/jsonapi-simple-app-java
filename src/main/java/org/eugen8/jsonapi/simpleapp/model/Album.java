package org.eugen8.jsonapi.simpleapp.model;

public class Album {
	private Integer userId;
	private Integer id;
	private String title;
	private ListOfPhotos photos;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ListOfPhotos getPhotos() {
		return photos;
	}
	public void setPhotos(ListOfPhotos photos) {
		this.photos = photos;
	}
	@Override
	public String toString() {
		return "Album [userId=" + userId + ", id=" + id + ", title=" + title + ", photos=" + photos + "]";
	}
	
}
