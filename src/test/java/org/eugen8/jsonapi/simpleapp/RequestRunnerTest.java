package org.eugen8.jsonapi.simpleapp;

import static org.junit.Assert.*;

import org.eugen8.jsonapi.simpleapp.model.ListOfPhotos;
import org.eugen8.jsonapi.simpleapp.model.Photo;
import org.junit.Test;

public class RequestRunnerTest {

	@Test
	public void getInstanceTest() {
		RequestRunner instance = RequestRunner.getInstance();
		assertNotNull(instance);
	}
	
	@Test
	public void formatPhotoListTest() {
		RequestRunner instance = RequestRunner.getInstance();

		ListOfPhotos l = new ListOfPhotos();
		Photo  p1 = new Photo();
		p1.setId(10);
		p1.setAlbumId(42);
		p1.setTitle("Photo one");
		p1.setUrl("url1");
		l.add(p1);
		
		Photo p2 = new Photo();
		p2.setId(11);
		p2.setAlbumId(42);
		p2.setTitle("Photo two");
		p2.setUrl("url2");
		l.add(p2);
		
		String formatedText = instance.formatPhotoList(l);
		assertEquals("[10] Photo one\n" + 
				"[11] Photo two\n", formatedText);
		
	}
	
	
	@Test
	public void getAlbumPhotosTest() {
		RequestRunner instance = RequestRunner.getInstance();
		ListOfPhotos list = instance.getAlbumPhotos(1);
		assertTrue(list.size()>1);
//		System.out.println(list.size());
//		System.out.println(list.toString());
		
	}
	
	
	

}
