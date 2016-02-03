package com.wang.myDB;

import java.sql.SQLException;
import java.util.List;
/**
 * Subclass of DAO with more methods
 * @author wang
 *
 */
public interface AlbumDAO extends DAO{

	public long insertAlbum(Album album);
	public void insertArtist(String[] names, String[] nations);
	public void insertCopyright(Album album, String[] names) throws SQLException;
	public boolean updateAlbum(Album album);
/*	public boolean deleteAlbum(Album album);*/
	
	public List<Album> findAlbumByProperty(AlbumSearchType searchType, Object value);
	public List<Album> findAll(); // Generic
	
	
}
