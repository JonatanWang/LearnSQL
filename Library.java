package com.wang.myDB;

import java.sql.SQLException;
import java.util.List;
/**
 * Define the model
 * @author wang
 *
 */
public class Library {

	private AlbumDAO albumDAO;
	private List<Album> list;
	
	public Library(AlbumDAO albumDAO){
		
		this.albumDAO = albumDAO;
		
		try {
			albumDAO.connect();
			albumDAO.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addNewAlbum(String title,int score,String genre, int year, String[] names,  String[] nations) {
		
		Thread t = new Thread() {
			
			public void run(){
				Album album = new Album();
				album.setTitle(title);
				album.setScore(score);
				album.setGenre(genre);
				album.setReleased(year);
				
				
				albumDAO.insertAlbum(album);
				albumDAO.insertArtist(names, nations);
				try {
					albumDAO.insertCopyright(album, names);
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
				
			}
			
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	};
	
	
	public List<Album> search(AlbumSearchType searchType, String value){
		
		Thread t = new Thread(){
		
		
			public void run(){
				 list = albumDAO.findAlbumByProperty(searchType, value);
				 
			}
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
		
		
		
	}
	
	public List<Album> showAll(){
		Thread t = new Thread(){
			
			public void run(){
				
				list = albumDAO.findAll();
			}
		};
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void close(){
		try {
			albumDAO.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	 
}
