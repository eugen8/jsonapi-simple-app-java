package org.eugen8.jsonapi.simpleapp;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.MediaType;

import org.eugen8.jsonapi.simpleapp.model.ListOfPhotos;
import org.eugen8.jsonapi.simpleapp.model.Photo;

/**
 * RequestRunner is a singleton class, access via RequestRunner.getInstance()
 * 
 * @author Eugen
 *
 */
public class RequestRunner {
	static final String URL = "http://jsonplaceholder.typicode.com/photos";
	private Client client;
	private static RequestRunner INSTANCE;

	private RequestRunner() {

	}

	private void init() {
		this.client = new PAClientBuilder().getClientInstance();
	}

	public synchronized static RequestRunner getInstance() {
		if (INSTANCE == null) {
			RequestRunner runner = new RequestRunner();
			runner.init();
			INSTANCE = runner;
		}
		return INSTANCE;
	}

	public ListOfPhotos getAlbumPhotos(int albumId) {
		return client.target(URL).queryParam("albumId", albumId).request(MediaType.APPLICATION_JSON)
				.get(ListOfPhotos.class);
	}

	public void printAlbumPhotos(int albumId) {
		String photoList = formatPhotoList(getAlbumPhotos(albumId));
		System.out.println("Photo album #" + albumId);
		System.out.println(photoList);
	}

	/**
	 * Returns a formatted text for the list of photos
	 * 
	 * @param albumPhotos of ListOfPhotos type
	 * @return
	 */
	protected String formatPhotoList(ListOfPhotos albumPhotos) {
		StringBuilder sb = new StringBuilder();
		for (Photo photo : albumPhotos) {
			sb.append(String.format("[%s] %s", photo.getId(), photo.getTitle()));
			sb.append("\n");
		}
		return sb.toString();
	}

}
